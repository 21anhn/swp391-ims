import React from 'react';
import { Box, Toolbar, Container, Typography, Paper } from '@mui/material';

function ManageIntern() {
  return (
    <Box sx={{ display: 'flex' }}>
      <Box
        component="main"
        sx={{ flexGrow: 1, bgcolor: 'background.default', p: 3 }}
      >
        <Toolbar />
        <Container>
          <Typography variant="h4" gutterBottom>
            Manage Interns
          </Typography>
          <Paper elevation={3} sx={{ p: 2 }}>
            {/* Add your intern management UI here */}
          </Paper>
        </Container>
      </Box>
    </Box>
  );
}

export default ManageIntern;