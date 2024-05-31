import React from "react";
import { Box,Button,IconButton, Typography,useTheme,useMediaQuery } from "@mui/material";
import Grid from "@mui/material/Unstable_Grid2";
import Header from "../../components/Header";
import BasicLineChart from "../../components/LineChart";

function Dashboard() {

  return (
    <Box m="20px">
      {/* HEADER */}
      <Box
        m="10px 0"
      >
        <Header title="DASHBOARD" subtitle="Welcome to your dashboard" /> 
      </Box>

      {/* GRID & CHARTS */}
      <Grid container spacing={2}>
        <Grid item xs={12} sm={12} md={4} lg={4} xl={3}>
          <Box sx={{width: "100%", height: "215px", bgcolor: "#F6FCFF"}}>
            <Typography variant="h6" fontWeight="700" color="#000000" p="5px">
              User with role HR Manager
            </Typography>
          </Box>
        </Grid>
        <Grid item xs={12} sm={12} md={4} lg={4} xl={3}>
          <Box sx={{width: "100%", height: "215px", display: "flex", alignItems: "center", justifyContent: "center"}} bgcolor="#F6FCFF">
            <Typography variant="h6" fontWeight="700">
              User with role Internship Coordinators
            </Typography>
          </Box>
        </Grid>
        <Grid item xs={12} sm={12} md={4} lg={4} xl={3}>
          <Box sx={{width: "100%", height: "215px", display: "flex", alignItems: "center", justifyContent: "center"}} bgcolor="#F6FCFF">
            <Typography variant="h6" fontWeight="700">
              User with role Mentors
            </Typography>
          </Box>
        </Grid>
        <Grid item xs={12} sm={12} md={4} lg={4} xl={3}>
          <Box sx={{width: "100%", height: "215px", display: "flex", alignItems: "center", justifyContent: "center"}} bgcolor="#F6FCFF">
            <Typography variant="h6" fontWeight="700">
              User with role Interns  
            </Typography>
          </Box>
        </Grid>

        <Grid container xs={12} sm={12} md={8} lg={8} rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
          <Grid xs={12}>
            <Box>
              <Box sx={{marginTop: "25px", padding: "0 30px", display: "flex", justifyContent: "space-between", alignItems: "center", bgcolor:"#F6FCFF"}}>
                <Box>
                  <Typography variant="h5" fontWeight="600" p={2}>
                    Revenue Generated
                  </Typography>
                  <BasicLineChart/>
                </Box>
              </Box>
            </Box>
          </Grid>
        </Grid>
        <Grid xs={12} sm={12} md={4} lg={4} xl={4}>
          <Box sx={{ maxHeight: "100vh", overflow: "auto", m: "25px 0 0 0", bgcolor: "#F6FCFF"}}>
                <Box
                  display="flex"
                  justifyContent="space-between"
                  alignItems="center"
                  p="15px"
                >
                  <Typography variant="h5">New user</Typography>
                </Box>
          </Box>
        </Grid>
      </Grid>
    </Box>

  );
};

export default Dashboard;
