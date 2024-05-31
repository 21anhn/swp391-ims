import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/Login/Login.jsx'
import LandingPage from './pages/LandingPage/LandingPage.jsx'
import InternshipCampaigns from './pages/LandingPage/InternshipCampaigns.jsx'
import AdminPage from './pages/Admin/AdminPage.jsx';
import HRManagerPage from './pages/HR Manager/HRManagerPage.jsx';
import Auth from './services/Auth.jsx';

export default function App() {
  return (
    <div>
      {/* <Header/> */}
        <Routes>    
          <Route path="*" element={<LandingPage/>} />
          <Route path="/login" element={<Login/>}/>
          <Route path="/internship_campaigns" element={<InternshipCampaigns/>}/>
          <Route path="/admin/*" element={<Auth requiredRole="ROLE_ADMIN"><AdminPage/></Auth>}/>
          <Route path="/hrmanager/*" element={<Auth requiredRole="ROLE_HR_MANAGER"><HRManagerPage/></Auth>}/>
        </Routes>
    </div> 
  )
}