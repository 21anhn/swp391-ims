import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/Login/Login.jsx'
import LandingPage from './pages/LandingPage/LandingPage.jsx'
import InternshipCampaigns from './pages/LandingPage/InternshipCampaigns.jsx'

function App() {
  return (
    <div>
      {/* <Header/> */}
      <BrowserRouter>
        <Routes>    
          <Route path="/login" element={<Login/>}/>
          <Route path="/" element={<LandingPage/>} />
          <Route path="/internship_campaigns" element={<InternshipCampaigns/>}/>
        </Routes>
      </BrowserRouter>
    </div> 
  )
}

export default App