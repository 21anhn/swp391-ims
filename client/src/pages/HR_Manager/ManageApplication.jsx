import React from "react";
//mui
import { Grid } from "@mui/material";
//component
import CampaignsList from "../../components/CampaignsList";
import CampaignData from "../../hooks/useFetchData";
//api
import { useNavigate } from "react-router-dom";


export default function ManageApplication() {
  const navigate = useNavigate();
  const viewApplication = (id) => {
    //navigate to schedule page
    navigate(`/hrmanager/manage_application/${id}`);
  }
  return (
    <CampaignData>
      {(campaigns) => <CampaignsList campaigns={campaigns} view={viewApplication} />}
    </CampaignData>
  );
}
