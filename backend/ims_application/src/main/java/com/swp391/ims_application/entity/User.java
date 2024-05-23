package com.swp391.ims_application.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    //MQH 1-N với Role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "userHR")
<<<<<<< HEAD
    private List<InternshipCampaign> internshipCampaignList;

    @OneToMany(mappedBy = "userIntern")
    private List<Application> applicationList;

    @OneToMany(mappedBy = "user")
    private List<EducationalBackground> educationalBackgroundList;

    @OneToMany(mappedBy = "user")
    private List<WorkHistory> workHistoryList;

    @OneToMany(mappedBy = "userCoordinator")
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy = "userCoordinator")
    private List<TrainingProgram> trainingProgramList;

    @OneToMany(mappedBy = "userIntern")
    private List<TrainingProgramIntern> trainingProgramInternList;

    @OneToMany(mappedBy = "userMentor")
    private List<Task> taskList;

    @OneToMany(mappedBy = "userIntern")
    private List<Assessment> assessmentList_intern;
=======
    private List<InternshipCampaign> internshipCampaigns;
>>>>>>> 7c0dbb50cda7abecd19f9a08546f10d8c542ff15

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

<<<<<<< HEAD
    public List<InternshipCampaign> getInternshipCampaignList() {
        return internshipCampaignList;
    }

    public void setInternshipCampaignList(List<InternshipCampaign> internshipCampaignList) {
        this.internshipCampaignList = internshipCampaignList;
    }

    public List<Application> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    public List<EducationalBackground> getEducationalBackgroundList() {
        return educationalBackgroundList;
    }

    public void setEducationalBackgroundList(List<EducationalBackground> educationalBackgroundList) {
        this.educationalBackgroundList = educationalBackgroundList;
    }

    public List<WorkHistory> getWorkHistoryList() {
        return workHistoryList;
    }

    public void setWorkHistoryList(List<WorkHistory> workHistoryList) {
        this.workHistoryList = workHistoryList;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public List<TrainingProgram> getTrainingProgramList() {
        return trainingProgramList;
    }

    public void setTrainingProgramList(List<TrainingProgram> trainingProgramList) {
        this.trainingProgramList = trainingProgramList;
    }

    public List<TrainingProgramIntern> getTrainingProgramInternList() {
        return trainingProgramInternList;
    }

    public void setTrainingProgramInternList(List<TrainingProgramIntern> trainingProgramInternList) {
        this.trainingProgramInternList = trainingProgramInternList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Assessment> getAssessmentList_intern() {
        return assessmentList_intern;
    }

    public void setAssessmentList_intern(List<Assessment> assessmentList_intern) {
        this.assessmentList_intern = assessmentList_intern;
=======
    public List<InternshipCampaign> getInternshipCampaigns() {
        return internshipCampaigns;
    }

    public void setInternshipCampaigns(List<InternshipCampaign> internshipCampaigns) {
        this.internshipCampaigns = internshipCampaigns;
>>>>>>> 7c0dbb50cda7abecd19f9a08546f10d8c542ff15
    }
}
