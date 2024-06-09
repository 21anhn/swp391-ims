import React from 'react'
import {Container,Grid,Box} from "@mui/material";
import { useNavigate } from "react-router-dom";
import CampaignData from '../../hooks/useFetchData';
import CampaignsList from '../../components/CampaignsList';

function ICPage() {
  const navigate = useNavigate();

  const handleDetail = (id) => {
    navigate(`/campaigns/${id}`);
  }

  const handleApply = () => {

  }

  return (
    <CampaignData>
      {(campaigns) => <CampaignsList campaigns={campaigns} view={handleDetail} apply={handleApply}/>}
    </CampaignData>
  )
} 

export default ICPage