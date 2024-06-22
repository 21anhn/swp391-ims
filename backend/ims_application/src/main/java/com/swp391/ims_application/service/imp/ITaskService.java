package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.Task;
import com.swp391.ims_application.payload.TaskDTO;

public interface ITaskService {
    Task createTask(TaskDTO taskDTO);
    Task updateTask(int taskId, TaskDTO taskDTO);
    void checkAndUpdateTaskAvailability(int taskId);
}
