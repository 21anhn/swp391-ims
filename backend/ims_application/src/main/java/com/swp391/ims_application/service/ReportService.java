package com.swp391.ims_application.service;

import com.swp391.ims_application.payload.ReportByAverageScoreDTO;
import com.swp391.ims_application.repository.InternTaskRepository;
import com.swp391.ims_application.repository.TrainingProgramInternRepository;
import com.swp391.ims_application.service.imp.IReportService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService implements IReportService {

    @Autowired
    private InternTaskRepository internTaskRepository;

    @Autowired
    private TrainingProgramInternRepository trainingProgramInternRepository;

    @Override
    public List<ReportByAverageScoreDTO> generateAverageScoreReport(int programId) {
        List<Object[]> result = internTaskRepository.calculateAverageScoreByProgramId(programId);
        List<ReportByAverageScoreDTO> reportResponses = new ArrayList<>();

        for (Object[] row : result) {
            int internId = (Integer) row[0];
            int trainingProgramId = (Integer) row[1];
            float averageScore = ((Number) row[2]).floatValue();

            ReportByAverageScoreDTO response = new ReportByAverageScoreDTO(internId, trainingProgramId, averageScore);
            reportResponses.add(response);
        }

        return reportResponses;
    }
}
