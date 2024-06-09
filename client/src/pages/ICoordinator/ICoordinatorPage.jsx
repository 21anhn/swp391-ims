import React from "react";
//mui
import { ThemeProvider, CssBaseline } from "@mui/material";
//component
import DoashboardIC from "./DoashboardIC";
import Schedule from "./ManageSchedule";
import TranningProgram from "./TranningProgram";
import Topbar from "../../components/Topbar";
import {MyProSidebarProvider} from "./Sidebar/sidebarContext";
import ManageSchedule from "./ManageSchedule";
//file
import theme from "../../theme/theme";
//router-dom 
import { Routes, Route } from "react-router-dom";


export default function ICoordinatorPage() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <MyProSidebarProvider>
        <div style={{ height: "100%", width: "100%" }}>
          <main>
            <Topbar />
            <Routes>
              <Route index element={<DoashboardIC />} />
              <Route path="/schedule" element={<ManageSchedule />} />
              <Route path="/schedule/:id" element={<Schedule />} />
              <Route path="/tranning_program" element={<TranningProgram />} />
            </Routes> 
          </main>
        </div>
      </MyProSidebarProvider>
    </ThemeProvider>
  );
}
