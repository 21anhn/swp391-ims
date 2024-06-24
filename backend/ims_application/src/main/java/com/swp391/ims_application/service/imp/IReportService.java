package com.swp391.ims_application.service.imp;

import com.swp391.ims_application.payload.ReportByAverageScoreDTO;

import java.util.List;

public interface IReportService {
    List<ReportByAverageScoreDTO> generateAverageScoreReport(int programId);
}
