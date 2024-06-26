package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.Task;
import com.swp391.ims_application.entity.TrainingProgram;
import com.swp391.ims_application.payload.TaskDTO;
import com.swp391.ims_application.repository.TaskRepository;
import com.swp391.ims_application.repository.TrainingProgramRepository;
import com.swp391.ims_application.service.imp.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Override
    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTaskName(taskDTO.getTaskName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setStartTime(taskDTO.getStartTime());
        task.setEndTime(taskDTO.getEndTime());
        task.setAvailable(true); // Set isAvailable to true on creation
        TrainingProgram trainingProgram = trainingProgramRepository.findByProgramId(taskDTO.getProgramId());
        if (trainingProgram != null) {
            task.setTrainingProgram(trainingProgram);
            return taskRepository.save(task);
        }
        return null;
    }

    @Override
    public Task updateTask(int taskId, TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTaskName(taskDTO.getTaskName());
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setStartTime(taskDTO.getStartTime());
        task.setEndTime(taskDTO.getEndTime());
        // isAvailable field can be modified based on business logic if needed
        return taskRepository.save(task);
    }

    @Override
    public void checkAndUpdateTaskAvailability(int taskId) {

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        // Check if the end time is after the start time
        if (task.getEndTime() != null && task.getEndTime().after(task.getStartTime())) {
            task.setAvailable(false);
            taskRepository.save(task);
        }
    }

    @Override
    public boolean removeTaskFromTrainingProgram(int taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setAvailable(false);
        taskRepository.save(task);
        return true;
    }

}
