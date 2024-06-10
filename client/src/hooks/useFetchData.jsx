import React from 'react';
import { fetchPosts } from '../services/apiServices';

export default function CampaignData({children}) {
  const [campaigns, setCampaigns] = React.useState([]);

  const fetchData = async () => {
    try {
      const response = await fetchPosts();
      setCampaigns(response);
    } catch (error) {
      console.error('Error fetching data: ', error);
    }
  }
  
  React.useEffect(() => {
    fetchData();
  }, [])

  return children(campaigns);
}
