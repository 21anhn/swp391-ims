import React from 'react'
import { Container, Typography, Button, Box } from '@mui/material';

function Hero() {
  return (
    <Box sx={{ backgroundImage: 'url(https://eduwork.id/v2/images/contributor/lp/hero-img.jpg)', backgroundSize: 'cover', padding: '50px 0', textAlign: 'center', color: '#fff', minHeight: '600px' }}>
      <Container>
        <Typography variant="h2" gutterBottom paddingTop="120px">
          Eduwork Internship Telah Dibuka
        </Typography>
        <Button variant="contained" color="primary" size="large" sx={{fontWeight: "bold"}}>Join Now</Button>
      </Container>
    </Box>
  )
}

export default Hero