import React from 'react';
import { Container, Typography, Box } from '@mui/material';

function Footer() {
  return (
    <Box sx={{ backgroundColor: '#1976d2', color: '#fff', padding: '20px 0' }}>
      <Container>
        <Typography variant="body1" align="center">
          &copy; 2023 Eduwork. All rights reserved.
        </Typography>
      </Container>
    </Box>
  );
}

export default Footer;