import { Box, ThemeProvider, createTheme } from "@mui/material";
import Header from "./Header";
import React from "react";
import Hero from "./Hero";
import Programs from "./Program";
import Feature from "./Feature";
import Footer from "./Footer";
import FAQ from "./FAQ";

const theme = createTheme();

const LandingPage = () => {
  return (
    <ThemeProvider theme={theme}>
      <Box
        sx={{
          bgcolor: "#F6FCFF",
        }}
      >
        {/* Header */}
        <Header />
        {/* Hero */}
        <Hero />
        <Programs />
        <Feature />
        <FAQ/>
        <Footer />
      </Box>
    </ThemeProvider>
  );
};

export default LandingPage;
