import React from "react";
import { List } from "@phosphor-icons/react";
import useResponsive from "../../hooks/useResponsive.js";
import { Button, Grid, IconButton, Stack, Typography, Box} from "@mui/material";
import { useNavigate } from "react-router-dom";


function Header() {
  const isDesktop = useResponsive("up", "md");
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/internship_campaigns")
  }
  return (
    <Box
      sx={{
        p: 4,
        bgcolor: "#F6FCFF",
        display: "flex",
        flexDirection: "column",
        rowGap: 4,
      }}
    >
      <Grid container spacing={2}>
        <Grid item md={4} xs={6}>
          <Stack direction="row" alignItems="center" spacing={2}>
            {!isDesktop && (
              <IconButton>
                <List />
              </IconButton>
            )}
            <Typography variant="h5" sx={{color:"#0F70D1", fontWeight: 700}}>Intern MS</Typography>
          </Stack>
        </Grid>
        {isDesktop && (
          <Grid item md={4} container justifyContent="center">
            <Stack direction="row" alignItems="center">
              <Button sx={{ color: "black", fontWeight: 700 }}>Home</Button>
              <Button sx={{ color: "black", fontWeight: 700 }} onClick={handleClick}>
                Internship Campaigns
              </Button>
              <Button sx={{ color: "black", fontWeight: 700 }}>About us</Button>
              <Button sx={{ color: "black", fontWeight: 700 }}>
                Contact Us
              </Button>
            </Stack>
          </Grid>
        )}
        <Grid item md={4} xs={6} container justifyContent="flex-end">
          <Stack direction="row" alignItems="center" spacing={2}>
            <Button variant="contained" sx={{ fontWeight: 700 }}>
              Sign in
            </Button>
          </Stack>
        </Grid>
      </Grid>
    </Box>
  );
}

export default Header;
