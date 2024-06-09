import React from 'react';
import CardItem from '../../components/CardItem';
import { Box, Container, Grid, Paper, Stack, Typography, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const programs = [
  { title: 'Program 1', description: 'Description of program 1' },
  { title: 'Program 2', description: 'Description of program 2' },
  // Add more programs as needed
];

function Programs() {
  const navigate = useNavigate();

  const handleSeeMore = () => {
    navigate('/internship_campaigns')
  }

  return (
    <Container sx={{paddingTop:4}}>
        <Grid container>
          <Grid item xs={7}>
            <Typography variant="body1" sx={{color:"#0F70D1", fontWeight: 700}}>List Program</Typography>
            <Typography variant="h4" sx={{marginTop: 2, marginBottom: 2, fontWeight: 700}}>Pilih program sesuai keinginanmu</Typography>
            <Typography variant="body1">Pilhon Program Yug Dopor Anda Mih bunkuk bengabung Dangan Alumi Connect</Typography>
          </Grid>
          <Grid item xs={5} sx={{p:2}}>
            <Paper elevation={3} sx={{p:2}}>
              <Stack direction="row" alignItems="center" spacing={2}>
                <Typography variant="h5" sx={{color:"#0F70D1", fontWeight: 700}}>8000+</Typography>
                <Typography variant="body2">Pilhon Program Yug Dopor Anda Mih bunkuk bengabung Dangan Alumi Connect</Typography>
              </Stack>
            </Paper>
          </Grid>
        </Grid>
        <Grid container sx={{marginTop:2, marginLeft:0, p:"20px 30px 20px 15px"}} spacing={{xs: 2}} >  
          <Grid item xs={4}>
            <CardItem />
          </Grid>
          <Grid item xs={4}>
            <CardItem />
          </Grid>
          <Grid item xs={4}>
            <CardItem />
          </Grid>
          <Grid item xs={4}>
            <CardItem />
          </Grid>
          <Grid item xs={4}>
            <CardItem />
          </Grid>
          <Grid item xs={4}>
            <CardItem />
          </Grid>
          <Stack direction="row" alignContent="center" justifyContent="center" width="100%" marginTop="20px">
            <Button variant="outlined" sx={{fontWeight: "bold", borderRadius: "20px"}} onClick={handleSeeMore}>See more</Button>
          </Stack>
        </Grid>
    </Container>
  );
}

export default Programs;