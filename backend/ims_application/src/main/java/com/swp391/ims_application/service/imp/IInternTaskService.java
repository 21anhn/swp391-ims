package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.InternTask;

public interface IInternTaskService {

    InternTask saveInternTask(InternTask internTask);
    InternTask findInternTaskById(int id);
    String getMentorFeedbackByTaskId(int taskId);
}
