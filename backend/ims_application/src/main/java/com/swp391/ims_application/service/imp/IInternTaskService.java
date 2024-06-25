package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.entity.InternTask;
import com.swp391.ims_application.payload.FeedbackDTO;

public interface IInternTaskService {

    InternTask saveInternTask(InternTask internTask);
    InternTask findInternTaskById(int id);
    FeedbackDTO getMentorFeedbackByTaskId(int taskId);
}
