package com.swp391.ims_application.service;

import com.swp391.ims_application.payload.InternTaskDTO;
import com.swp391.ims_application.entity.InternTask;
import com.swp391.ims_application.entity.Task;
import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.repository.InternTaskRepository;
import com.swp391.ims_application.repository.TaskRepository;
import com.swp391.ims_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class InternTaskService {

    @Autowired
    private InternTaskRepository internTaskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public InternTaskDTO getInternTaskById(int itId) {
        InternTask internTask = internTaskRepository.findById(itId)
                .orElseThrow(() -> new IllegalArgumentException("InternTask not found with id: " + itId));


        InternTaskDTO internTaskDTO = new InternTaskDTO();
        internTaskDTO.setItId(internTask.getItId());
        internTaskDTO.setUserInternId(internTask.getUserIntern().getUserId());
        internTaskDTO.setTaskId(internTask.getTask().getTaskId());
        internTaskDTO.setScore(internTask.getScore());
        internTaskDTO.setComment(internTask.getComment());
        internTaskDTO.setFilePath(internTask.getFilePath());

        return internTaskDTO;
    }

    @Transactional
    public InternTaskDTO saveInternTask(InternTaskDTO internTaskDTO, MultipartFile file) throws IOException {
        User userIntern = userRepository.findById(internTaskDTO.getUserInternId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + internTaskDTO.getUserInternId()));
        Task task = taskRepository.findById(internTaskDTO.getTaskId())
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + internTaskDTO.getTaskId()));

        InternTask internTask = new InternTask();
        internTask.setUserIntern(userIntern);
        internTask.setTask(task);
        internTask.setScore(internTaskDTO.getScore());
        internTask.setComment(internTaskDTO.getComment());


        if (!file.isEmpty()) {
            String filePath = "path/to/save/" + file.getOriginalFilename();
            File dest = new File(filePath);
            file.transferTo(dest);
            internTask.setFilePath(filePath);
        }

        InternTask savedInternTask = internTaskRepository.save(internTask);


        InternTaskDTO savedInternTaskDTO = new InternTaskDTO();
        savedInternTaskDTO.setItId(savedInternTask.getItId());
        savedInternTaskDTO.setUserInternId(savedInternTask.getUserIntern().getUserId());
        savedInternTaskDTO.setTaskId(savedInternTask.getTask().getTaskId());
        savedInternTaskDTO.setScore(savedInternTask.getScore());
        savedInternTaskDTO.setComment(savedInternTask.getComment());
        savedInternTaskDTO.setFilePath(savedInternTask.getFilePath());

        return savedInternTaskDTO;
    }

    @Transactional(readOnly = true)
    public String viewTaskFeedback(int itId) {
        InternTask internTask = internTaskRepository.findById(itId)
                .orElseThrow(() -> new IllegalArgumentException("InternTask not found with id: " + itId));

        return internTask.getComment();
    }

    @Transactional
    public InternTaskDTO updateInternTaskFeedback(int itId, float score, String comment) {
        InternTask internTask = internTaskRepository.findById(itId)
                .orElseThrow(() -> new IllegalArgumentException("InternTask not found with id: " + itId));
        internTask.setScore(score);
        internTask.setComment(comment);

        InternTask updatedInternTask = internTaskRepository.save(internTask);


        InternTaskDTO updatedInternTaskDTO = new InternTaskDTO();
        updatedInternTaskDTO.setItId(updatedInternTask.getItId());
        updatedInternTaskDTO.setUserInternId(updatedInternTask.getUserIntern().getUserId());
        updatedInternTaskDTO.setTaskId(updatedInternTask.getTask().getTaskId());
        updatedInternTaskDTO.setScore(updatedInternTask.getScore());
        updatedInternTaskDTO.setComment(updatedInternTask.getComment());
        updatedInternTaskDTO.setFilePath(updatedInternTask.getFilePath());

        return updatedInternTaskDTO;
    }
}
