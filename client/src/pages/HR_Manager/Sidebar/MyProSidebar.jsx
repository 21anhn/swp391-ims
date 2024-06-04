import { useState } from "react";
import { Menu, Sidebar, MenuItem } from "react-pro-sidebar";
import { useProSidebar } from "react-pro-sidebar";

import { useSidebarContext } from "./sidebarContext";

import { NavLink } from "react-router-dom";
import { useTheme, Box, Typography, IconButton } from "@mui/material";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import { AssignmentOutlined } from "@mui/icons-material";
import { CampaignOutlined } from "@mui/icons-material";
import HelpOutlineOutlinedIcon from "@mui/icons-material/HelpOutlineOutlined";

import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";

const Item = ({ title, to, icon, selected, setSelected }) => {

  return (
    <MenuItem
      active={selected === title}
      style={{ color: "white" }}
      onClick={() => setSelected(title)}
      icon={icon}
      routerLink={<NavLink to={to} />}
    >
      <Typography>{title}</Typography>
    </MenuItem>
  );
};

const MyProSidebar = () => {
  const [selected, setSelected] = useState("Home");
  const { sidebarImage } = useSidebarContext();
  const { collapseSidebar, toggleSidebar, collapsed, broken } = useProSidebar();
  return (
    <Box
      sx={{
        position: "sticky",
        display: "flex",
        height: "100vh",
        top: 0,
        bottom: 0,
        zIndex: 10000,
        "& .sidebar": {
          border: "none",
        },
        "& .menu-icon": {
          backgroundColor: "transparent !important",
        },
        "& .menu-item": {
          // padding: "5px 35px 5px 20px !important",
          backgroundColor: "transparent !important",
          color: "white"
        },
        "& .menu-anchor": {
          color: "inherit !important",
          backgroundColor: "transparent !important",
        },
        "& .menu-item:hover": {
          backgroundColor: "transparent !important",
          color: "#2D77FF"
        },
        "& .menu-item.active": {
          backgroundColor: "#2D77FF !important",
          borderRadius: "10px"
        },
        "& .menu-item.active:hover": {
          color: "white"
        }
      }}
    >
      <Sidebar
        breakPoint="md"
        image={sidebarImage}
        backgroundColor="#031529"
      >
        <Menu iconshape="square">
          <MenuItem
            icon={
              collapsed && (
                <MenuOutlinedIcon onClick={() => collapseSidebar()} />
              )}
            style={{
              margin: "10px 0 20px 0",
            }}
          >
            {!collapsed && (
              <Box
                sx={{
                  display: "flex",
                  justifyContent: "end",
                  alignItems: "center"
                }}
              >
                <IconButton
                  onClick={
                    broken ? () => toggleSidebar() : () => collapseSidebar()
                  }
                >
                  <ChevronLeftIcon /> 
                </IconButton>
              </Box>
            )}
          </MenuItem>
          <Box paddingLeft={collapsed ? undefined : "2%"}>
            <Item
              title="Home"
              to="/hrmanager"
              icon={<HomeOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />
            <Item
              title="Manage campaigns"
              to="/hrmanager/manage_campaigns"
              icon={<CampaignOutlined />}
              selected={selected}
              setSelected={setSelected}
            />
            <Item
              title="Manage application"
              to="/hrmanager/manage_application"
              icon={<AssignmentOutlined />}
              selected={selected}
              setSelected={setSelected}
            />
            <Item
              title="FAQ Page"
              to="/hrmanager/manage_intern"
              icon={<HelpOutlineOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />
          </Box>
        </Menu>
      </Sidebar>
    </Box>
  );
};

export default MyProSidebar;
