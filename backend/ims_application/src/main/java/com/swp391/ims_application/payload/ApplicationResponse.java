    package com.swp391.ims_application.payload;

    import lombok.Getter;
    import lombok.Setter;

    import java.util.Date;

    @Getter
    @Setter
    public class ApplicationResponse {

        private int applicationId;
        private Date applicationDate;
        private String status;
        private String userInternName; // You can map more user details if needed
        private String internshipCampaignName; // You can map more campaign details if needed

        public ApplicationResponse(int applicationId, Date applicationDate, String status, String userInternName, String internshipCampaignName) {
            this.applicationId = applicationId;
            this.applicationDate = applicationDate;
            this.status = status;
            this.userInternName = userInternName;
            this.internshipCampaignName = internshipCampaignName;
        }
    }
