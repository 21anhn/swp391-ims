package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.payload.TrainingProgramInternFeedbackDTO;

public interface ITrainingProgramInternService {
    void giveFeedback(TrainingProgramInternFeedbackDTO feedbackDTO);
    TrainingProgramInternFeedbackDTO getFeedback(int internId, int programId);
}
