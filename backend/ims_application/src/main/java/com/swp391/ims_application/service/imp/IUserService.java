package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.payload.AccountDTO;
import com.swp391.ims_application.payload.TrainingProgramDTO;
import com.swp391.ims_application.payload.UserDTO;

import java.util.List;

public interface IUserService {
    User login(String username, String password);

    User createAccount(AccountDTO accountDTO);

    List<User> getAllUsers();

    AccountDTO getUserByUsername(String username);

    boolean resetPassword(String username);

    boolean lockAccount(String username, boolean isActive);

    List<UserDTO> viewAllProfileInterns(int campaignId);

    UserDTO searchProfileIntern(String username, int campaignId);

    boolean editProfile(UserDTO userDTO, int campaignId);

    List<UserDTO> getMentorList();

    boolean specifyMentorToProgram(int mentorId, int programId);

    List<TrainingProgramDTO> getTrainingProgramByMentor(int mentorId);
}
