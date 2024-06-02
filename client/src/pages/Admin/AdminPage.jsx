import { ThemeProvider } from "@emotion/react";
import { CssBaseline } from "@mui/material";
import React from "react";
import { Routes, Route } from "react-router-dom";
import theme from "../../theme/theme";
import {MyProSidebarProvider} from "./Sidebar/sidebarContext";
import Topbar from "./Topbar/Topbar";
import Dashboard from "./Dashboard";
import Team from "./Team";
import Form from "./Form";
import FAQ from "./Faq";


function AdminPage() {
  return (
      <ThemeProvider theme={theme}>
        <CssBaseline/>
        <MyProSidebarProvider>
          <div style={{ height: "100%", width: "100%" }}>
            <main>
              <Topbar />
              <Routes>
                <Route index element={<Dashboard />} />
                <Route path="/team" element={<Team />} />
                <Route path="/faq" element={<FAQ />} />
              </Routes>
            </main>
          </div>
        </MyProSidebarProvider>
      </ThemeProvider>
  );
}

export default AdminPage;
