import React from 'react'
import {Container,Grid,Box} from "@mui/material";
import { fetchPostDetail, fetchPosts } from "../../services/apiServices";
import CardItem from "./CardItem";
import { useNavigate } from "react-router-dom";

function ICPage() {
    const [post, setPost] = React.useState(null);
    const [postDetails, setPostDetails] = React.useState(null);

  const navigate = useNavigate();

  const fetchData = async () => {
    try {
      const data = await fetchPosts(); // Call your API function to fetch card data
      setPost(data);
    } catch (error) {
      console.error('Error fetching card data:', error);
    }
  };

  React.useEffect(() => {
    fetchData();
  }, []);

  const handleDetail = (id) => {
    navigate(`/internship_campaigns/detail/${id}`);
  }

  const handleApply = () => {

  }

  return (
    <Box
        sx={{
          bgcolor: "#F6FCFF",
        }}
      >
        <Container sx={{marginTop: "20px"}}>
          <Grid container spacing={2}>
            {post &&
              post.map((post) => (
                <Grid item xs={4} key={post.id}>
                  <CardItem id={post.id} image={post.image} title={post.campaignName} description={post.jobDescription} onDetailButtonClick={handleDetail} onApplyButtonClick={handleApply}/>
                </Grid>
              ))}
          </Grid>
        </Container>
    </Box>
  )
}

export default ICPage