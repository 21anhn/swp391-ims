package com.swp391.ims_application.payload;

import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingProgramInternDTO {

    private int programId;
    private int internId;
    private TrainingProgram trainingProgram;
    private User userIntern;

    public TrainingProgramInternDTO() {
    }

    public TrainingProgramInternDTO(int programId, int internId, TrainingProgram trainingProgram, User userIntern) {
        this.programId = programId;
        this.internId = internId;
        this.trainingProgram = trainingProgram;
        this.userIntern = userIntern;
    }

    @Override
    public String toString() {
        return "TrainingProgramInternDTO{" +
                "programId=" + programId +
                ", internId=" + internId +
                ", trainingProgram=" + trainingProgram +
                ", userIntern=" + userIntern +
                '}';
    }
}
