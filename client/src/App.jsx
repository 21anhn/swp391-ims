import React from 'react'
import { Routes, Route } from 'react-router-dom';
import Login from './pages/Login/Login.jsx'
import LandingPage from './pages/LandingPage/LandingPage.jsx'
import InternshipCampaigns from './pages/LandingPage/InternshipCampaigns.jsx'
import AdminPage from './pages/Admin/AdminPage.jsx';
import HRManagerPage from './pages/HR_Manager/HRManagerPage.jsx';
import Auth from './services/Auth.jsx';
import ICoordinatorPage from './pages/ICoordinator/ICoordinatorPage.jsx';

export default function App() {
  return (
    <div>
      {/* <Header/> */}
        <Routes>    
          <Route path="*" element={<div>404 Not Found</div>}/>
          <Route path="/" element={<LandingPage/>} />
          <Route path="/login" element={<Login/>}/>
          <Route path="/campaigns/*" element={<InternshipCampaigns/>}/>
          {/* <Route path="/admin/*" element={<Auth requiredRole="ROLE_ADMIN"><AdminPage/></Auth>}/>
          <Route path="/hrmanager/*" element={<Auth requiredRole="ROLE_HR_MANAGER"><HRManagerPage/></Auth>}/> */}
          <Route path="/hrmanager/*" element={<HRManagerPage/>}/>
          <Route path="/admin/*" element={<AdminPage/>}/>
          <Route path="/icoordinator/*" element={<ICoordinatorPage/>} />
        </Routes>
    </div> 
  )
}