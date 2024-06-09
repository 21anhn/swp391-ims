import {
  ThemeProvider,
  createTheme,
} from "@mui/material";
import React, { useEffect, useState } from "react";
//import component
import Header from "./Header";
import ICPage from "./ICPage";
import DetailCampaign from "./DetailCampaign";
//import library
import { Routes, Route } from "react-router-dom";

const theme = createTheme();

function InternshipCampaigns() {
  return (
    <ThemeProvider theme={theme}>
      <Header/>
      <Routes>
        <Route index element={<ICPage/>}/>
        <Route path="/:id" element={<DetailCampaign/>}/>
      </Routes>
    </ThemeProvider>
  );
}

export default InternshipCampaigns;
