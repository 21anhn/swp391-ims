package com.swp391.ims_application.service;

import com.swp391.ims_application.entity.Application;
import com.swp391.ims_application.entity.InternshipCampaign;
import com.swp391.ims_application.payload.CampaignReportDTO;
import com.swp391.ims_application.repository.InternshipCampaignRepository;
import com.swp391.ims_application.service.imp.IIternshipCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternshipCampaignService implements IIternshipCampaignService {

    @Autowired
    InternshipCampaignRepository internshipCampaignRepository;

    @Override
    public List<InternshipCampaign> getAll() {
        return internshipCampaignRepository.findAll();
    }

    @Override
    public InternshipCampaign getById(int id) {
        return internshipCampaignRepository.findByCampaignId(id);
    }

    @Override
    public List<InternshipCampaign> getByContainName(String name) {
        return internshipCampaignRepository.findByCampaignNameContaining(name);
    }

    @Override
    public boolean updateById(InternshipCampaign internshipCampaign) {
        if (internshipCampaign != null) {
            internshipCampaignRepository.save(internshipCampaign);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        if (internshipCampaignRepository.existsById(id)) {
            internshipCampaignRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<CampaignReportDTO> getInternsCountByCampaign() {
        return internshipCampaignRepository.countInternsByCampaign();
    }
}

