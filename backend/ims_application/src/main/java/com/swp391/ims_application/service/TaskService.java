package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.InternTask;
import com.swp391.ims_application.payload.TaskCompletionDTO;
import com.swp391.ims_application.repository.InternTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private InternTaskRepository internTaskRepository;

    public TaskCompletionDTO getTaskCompletionForIntern(int programId, int internId) {
        List<InternTask> internTasks = internTaskRepository.findInternTasksByTrainingProgramAndIntern(programId, internId);
        long totalTasks = internTasks.size();
        long completedTasks = internTasks.stream().filter(task -> task.getScore() > 0).count();

        return new TaskCompletionDTO(internId, programId, completedTasks, totalTasks);
    }
}
