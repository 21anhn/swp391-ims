package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.*;
import com.swp391.ims_application.payload.AccountDTO;
import com.swp391.ims_application.payload.UserDTO;
import com.swp391.ims_application.repository.ApplicationRepository;
import com.swp391.ims_application.repository.InternshipCampaignRepository;
import com.swp391.ims_application.repository.TrainingProgramRepository;
import com.swp391.ims_application.repository.UserRepository;
import com.swp391.ims_application.service.imp.IRoleService;
import com.swp391.ims_application.service.imp.IUserService;
import com.swp391.ims_application.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InternshipCampaignRepository internshipCampaignRepository;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    IRoleService roleService;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    TrainingProgramRepository trainingProgramRepository;

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public User createAccount(AccountDTO accountDTO) {
        String password = Helper.generatePassword(); //Auto generate password
        accountDTO.setPassword(password);
        User user = new User();
        user.setUsername(accountDTO.getUsername());
        user.setPassword(password);
        user.setEmail(accountDTO.getEmail());
        user.setPhoneNumber(accountDTO.getPhoneNumber());
        user.setActive(true);
        Role role = roleService.getRoleByName(accountDTO.getRoleName());
        if (role == null) {
            return null;
        }
        user.setRole(role);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AccountDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setRoleName(user.getRole().getRoleName());
            accountDTO.setUsername(user.getUsername());
            accountDTO.setPassword(user.getPassword());
            accountDTO.setEmail(user.getEmail());
            accountDTO.setPhoneNumber(user.getPhoneNumber());
            return accountDTO;
        }
        return null;
    }

    @Override
    public boolean resetPassword(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            String password = Helper.generatePassword();
            user.setPassword(password);
            userRepository.save(user);
            sendMailService.sendMail(user.getEmail(), "Reset password", "New password: " + user.getPassword() + "\nEnter this url to login: " + "https://tinyurl.com/tn4e64wm");
            return true;
        }
        return false;
    }

    @Override
    public boolean lockAccount(String username, boolean isActive) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setActive(isActive);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDTO> viewAllProfileInterns(int campaignId) {
        List<Application> applicationList = applicationRepository.findByInternshipCampaignCampaignIdAndStatus(campaignId, "Approved");
        List<UserDTO> userDTOList = null;
        if (applicationList != null && !applicationList.isEmpty()) {
            userDTOList = new ArrayList<>();
            User user;
            UserDTO userDTO;
            for (Application application : applicationList) {
                user = userRepository.findByApplicationApplicationId(application.getApplicationId());
                userDTO = new UserDTO();
                if (user != null) {
                    userDTO.setId(user.getUserId());
                    userDTO.setUsername(user.getUsername());
                    userDTO.setEmail(user.getEmail());
                    userDTO.setPhoneNumber(user.getPhoneNumber());
                    userDTO.setApplicationId(application.getApplicationId());
                    userDTO.setFullName(user.getFullName());
                    userDTO.setGender(user.getGender());
                    userDTO.setDob(user.getDob());
                    userDTOList.add(userDTO);
                }
            }
            return userDTOList;
        }
        return userDTOList;
    }

    @Override
    public UserDTO searchProfileIntern(String username, int campaignId) {
        User user = userRepository.findByUsername(username);
        InternshipCampaign ic = internshipCampaignRepository.findByCampaignId(campaignId);

        if (user == null || ic == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        for (Application a : ic.getApplications()) {
            if (user.getApplication() != null) {
                if (a.getApplicationId() == user.getApplication().getApplicationId()) {
                    userDTO.setId(user.getUserId());
                    userDTO.setUsername(user.getUsername());
                    userDTO.setEmail(user.getEmail());
                    userDTO.setPhoneNumber(user.getPhoneNumber());
                    userDTO.setApplicationId(a.getApplicationId());
                    userDTO.setFullName(user.getFullName());
                    userDTO.setGender(user.getGender());
                    userDTO.setDob(user.getDob());
                    return userDTO;
                }
            }
        }
        return null;
    }

    @Override
    public boolean editProfile(UserDTO userDTO, int campaignId) {
        User user = userRepository.findByUsername(userDTO.getUsername());
        InternshipCampaign ic = internshipCampaignRepository.findByCampaignId(campaignId);

        if (user == null || ic == null) {
            return false;
        }
        for (Application a : ic.getApplications()) {
            if (user.getApplication() != null) {
                if (a.getApplicationId() == user.getApplication().getApplicationId()) {
                    if (userDTO.getEmail() != null)
                        user.setEmail(userDTO.getEmail());
                    if (userDTO.getPhoneNumber() != null)
                        user.setPhoneNumber(userDTO.getPhoneNumber());
                    if (userDTO.getFullName() != null)
                        user.setFullName(userDTO.getFullName());
                    if (userDTO.getGender() != null)
                        user.setGender(userDTO.getGender());
                    if (userDTO.getDob() != null)
                        user.setDob(userDTO.getDob());
                    userRepository.save(user);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<UserDTO> getMentorList() {
        List<User> mentorList = userRepository.findByRoleRoleName("ROLE_MENTOR");
        List<UserDTO> res = null;
        if (mentorList == null) {
            return null;
        }
        res = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        for (User u : mentorList) {
            userDTO = new UserDTO();
            userDTO.setId(u.getUserId());
            userDTO.setUsername(u.getUsername());
            userDTO.setEmail(u.getEmail());
            userDTO.setPhoneNumber(u.getPhoneNumber());
            userDTO.setFullName(u.getFullName());
            userDTO.setGender(u.getGender());
            userDTO.setDob(u.getDob());
            res.add(userDTO);
        }
        return res;
    }

    @Override
    public boolean specifyMentorToProgram(int mentorId, int programId) {
        User user = userRepository.findById(mentorId).get();
        TrainingProgram trainingProgram = trainingProgramRepository.findById(programId).get();
        trainingProgram.setUserInternCoordinator(user);
        trainingProgramRepository.save(trainingProgram);
        return true;
    }
}
