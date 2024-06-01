package com.swp391.ims_application.controller;

import com.swp391.ims_application.entity.InternshipCampaign;
import com.swp391.ims_application.entity.User;
import com.swp391.ims_application.payload.CustomResponse;
import com.swp391.ims_application.payload.ICampaignResponse;
import com.swp391.ims_application.payload.InternshipCampaignRequest;
import com.swp391.ims_application.repository.InternshipCampaignRepository;
import com.swp391.ims_application.repository.UserRepository;
import com.swp391.ims_application.service.IntershipCampaignService;
import com.swp391.ims_application.service.imp.IIternshipCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/hr")
@CrossOrigin("*")
public class InternshipCampaignController {

    @Autowired
    private IIternshipCampaignService intershipCampaignService;
    @Autowired
    private UserRepository userRepository; // Inject UserRepository


    @GetMapping
    public ResponseEntity<?> getInternship() {
        List<InternshipCampaign> internshipCampaignList = intershipCampaignService.getAll();
        CustomResponse customResponse = new CustomResponse();
        HttpStatus statusCode;
        if (internshipCampaignList.isEmpty()) {
            customResponse.setMessage("No internship campaigns found");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            List<ICampaignResponse> campaignResponseList = new ArrayList<>();
            for (InternshipCampaign i : internshipCampaignList) {
                campaignResponseList.add(new ICampaignResponse(i.getCampaignId(), i.getCampaignName(), i.getJobDescription(), i.getRequirements(), i.getPostedDate(), i.getDeadline(), ""/*i.getUserHR().getFullName()*/));
            }
            customResponse.setMessage("Internship campaigns found");
            customResponse.setData(campaignResponseList);
            customResponse.setSuccess(true);
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(customResponse, statusCode);
    }

/*    @GetMapping("/{id}")
    public ResponseEntity<?> getInternshipById(@PathVariable int id) {
        CustomResponse customResponse = new CustomResponse();
        HttpStatus statusCode;
        InternshipCampaign i = intershipCampaignService.getById(id);
        if (i != null) {
            customResponse.setSuccess(true);
            customResponse.setStatus(HttpStatus.OK.value());
            customResponse.setMessage("Campaign found!");
            ICampaignResponse iCampaignResponse = new ICampaignResponse(i.getCampaignId(), i.getCampaignName(), i.getJobDescription(), i.getRequirements(), i.getPostedDate(), i.getDeadline(), i.getUserHR().getFullName());
            customResponse.setData(iCampaignResponse);
            statusCode = HttpStatus.OK;
        } else {
            customResponse.setMessage("Not found!");
            customResponse.setStatus(HttpStatus.NOT_FOUND.value());
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(customResponse, statusCode);
    }*/

    @GetMapping("/details")
    public ResponseEntity<?> getInternshipById(@RequestParam int id) {
        CustomResponse customResponse = new CustomResponse();
        HttpStatus statusCode;
        InternshipCampaign i = intershipCampaignService.getById(id);
        if (i != null) {
            customResponse.setSuccess(true);
            customResponse.setStatus(HttpStatus.OK.value());
            customResponse.setMessage("Campaign found!");
            ICampaignResponse iCampaignResponse = new ICampaignResponse(
                    i.getCampaignId(),
                    i.getCampaignName(),
                    i.getJobDescription(),
                    i.getRequirements(),
                    i.getPostedDate(),
                    i.getDeadline(),
                    i.getUserHR().getFullName()
            );
            customResponse.setData(iCampaignResponse);
            statusCode = HttpStatus.OK;
        } else {
            customResponse.setMessage("Not found!");
            customResponse.setStatus(HttpStatus.NOT_FOUND.value());
            statusCode = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(customResponse, statusCode);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getInternship(@PathVariable String name) {
        CustomResponse customResponse = new CustomResponse();
        HttpStatus statusCode;
        List<InternshipCampaign> internshipCampaignList = intershipCampaignService.getByContainName(name);
        if (internshipCampaignList.isEmpty()) {
            customResponse.setMessage("No internship campaigns found");
            statusCode = HttpStatus.NOT_FOUND;
        } else {
            List<ICampaignResponse> campaignResponseList = new ArrayList<>();
            for (InternshipCampaign i : internshipCampaignList) {
                campaignResponseList.add(new ICampaignResponse(i.getCampaignId(), i.getCampaignName(), i.getJobDescription(), i.getRequirements(), i.getPostedDate(), i.getDeadline(), i.getUserHR().getFullName()));
            }
            customResponse.setMessage("Internship campaigns found");
            customResponse.setData(campaignResponseList);
            customResponse.setSuccess(true);
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(customResponse, statusCode);
    }


    @PostMapping
    public ResponseEntity<?> createCampaign(@RequestBody InternshipCampaignRequest campaignRequest) {
/*        User userHR = userRepository.findById(campaignRequest.getHrId())
                .orElseThrow(() -> new RuntimeException("HR not found"));*/

        InternshipCampaign campaign = new InternshipCampaign();
        campaign.setCampaignName(campaignRequest.getCampaignName());
        campaign.setJobDescription(campaignRequest.getJobDescription());
        campaign.setRequirements(campaignRequest.getRequirements());
        campaign.setPostedDate(new Date());
        campaign.setDeadline(campaignRequest.getDeadline());
        //campaign.setUserHR(userHR);

        boolean check = intershipCampaignService.updateById(campaign);
        CustomResponse customResponse = new CustomResponse();
        HttpStatus statusCode;
        if(check) {
            statusCode = HttpStatus.CREATED;
            customResponse.setSuccess(true);
            customResponse.setStatus(HttpStatus.CREATED.value());
            customResponse.setMessage("Campaign rceated successfully!");
        } else {
            statusCode = HttpStatus.BAD_REQUEST;
            customResponse.setSuccess(false);
            customResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            customResponse.setMessage("Campaign could not be created!");
        }


        return new ResponseEntity<>(customResponse, statusCode);
    }



}
