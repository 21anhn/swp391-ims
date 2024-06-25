package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.TrainingProgramIntern;
import com.swp391.ims_application.payload.TrainingProgramInternFeedbackDTO;
import com.swp391.ims_application.repository.TrainingProgramInternRepository;
import com.swp391.ims_application.service.imp.ITrainingProgramInternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingProgramInternService implements ITrainingProgramInternService {
    @Autowired
    private TrainingProgramInternRepository trainingProgramInternRepository;

    @Override
    public void giveFeedback( TrainingProgramInternFeedbackDTO feedbackDTO) {
        TrainingProgramIntern tpi = trainingProgramInternRepository.findByUserInternUserIdAndTrainingProgramProgramId(
                feedbackDTO.getInternId(), feedbackDTO.getProgramId());

        if (tpi != null) {
            tpi.setFeedback(feedbackDTO.getFeedback());
            trainingProgramInternRepository.save(tpi);
        }
    }

    @Override
    public  TrainingProgramInternFeedbackDTO getFeedback(int internId, int programId) {
        TrainingProgramIntern tpi = trainingProgramInternRepository.findByUserInternUserIdAndTrainingProgramProgramId(internId, programId);

        if (tpi != null) {
            TrainingProgramInternFeedbackDTO feedbackDTO = new  TrainingProgramInternFeedbackDTO();
            feedbackDTO.setInternId(internId);
            feedbackDTO.setProgramId(programId);
            feedbackDTO.setFeedback(tpi.getFeedback());
            return feedbackDTO;
        }
        return null;
    }
}
