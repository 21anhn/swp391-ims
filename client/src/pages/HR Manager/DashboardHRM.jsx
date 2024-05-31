import React from 'react';
import { Box, Toolbar, Container, Grid, Paper, Typography } from '@mui/material';

function DashboardHRM() {
  return (
    <Box sx={{ display: 'flex' }}>
      <Box
        component="main"
        sx={{ flexGrow: 1, bgcolor: 'background.default', p: 3 }}
      >
        <Toolbar />
        <Container>
          <Typography variant="h4" gutterBottom>
            HR Management Dashboard
          </Typography>
          <Grid container spacing={3}>
            <Grid item xs={12} md={4}>
              <Paper elevation={3}>
                
              </Paper>
            </Grid>
            <Grid item xs={12} md={4}>
              <Paper elevation={3}>
                
              </Paper>
            </Grid>
            <Grid item xs={12} md={4}>
              <Paper elevation={3}>
                
              </Paper>
            </Grid>
          </Grid>
        </Container>
      </Box>
    </Box>
  );
}

export default DashboardHRM;