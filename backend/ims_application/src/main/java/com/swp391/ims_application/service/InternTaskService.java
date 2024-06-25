package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.InternTask;
import com.swp391.ims_application.payload.FeedbackDTO;
import com.swp391.ims_application.repository.InternTaskRepository;
import com.swp391.ims_application.service.imp.IInternTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InternTaskService implements IInternTaskService {
    @Autowired
    private InternTaskRepository internTaskRepository;

    @Override
    public InternTask saveInternTask(InternTask internTask) {
        return internTaskRepository.save(internTask);
    }

    @Override
    public InternTask findInternTaskById(int id) {
        return internTaskRepository.findById(id).orElse(null);
    }

    @Override
    public FeedbackDTO getMentorFeedbackByTaskId(int taskId) {
        InternTask internTask = internTaskRepository.findById(taskId).orElse(null);
        if (internTask != null) {
            String feedback = internTask.getComment();
            float score = internTask.getScore(); // Assuming there is a getScore() method in InternTask
            return new FeedbackDTO(feedback, score);
        }
        return null; // or throw an exception if the task is not found
    }

}
