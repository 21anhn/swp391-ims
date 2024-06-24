package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.InternTask;
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
}
