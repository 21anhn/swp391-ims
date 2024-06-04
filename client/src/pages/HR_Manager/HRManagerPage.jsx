import React, { useState } from "react";
import { ThemeProvider } from "@emotion/react";
import { CssBaseline } from "@mui/material";
import { Routes, Route } from "react-router-dom";
import theme from "../../theme/theme";
import Topbar from "./Topbar/Topbar";
import {MyProSidebarProvider} from "./Sidebar/sidebarContext";
import DashboardHRM from "./DashboardHRM";
import ManageCampaigns from "./ManageCampaigns";
import ManageApplication from "./ManageApplication";
import ManageIntern from "./ManageIntern";
import CreatePost from "./CreatePost";

function HRManagerPage() {

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <MyProSidebarProvider>
        <div style={{ height: "100%", width: "100%" }}>
          <main>
            <Topbar />
            <Routes>
              <Route index element={<DashboardHRM />} />
              <Route path="/manage_campaigns" element={<ManageCampaigns />} />
              <Route path="/create_post" element={<CreatePost/>}/>
              <Route path="/manage_application" element={<ManageApplication />}/>
              <Route path="/manage_intern" element={<ManageIntern />} />
            </Routes>
          </main>
        </div>
      </MyProSidebarProvider>
    </ThemeProvider>
  );
}

export default HRManagerPage;
