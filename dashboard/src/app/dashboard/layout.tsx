"use client";

import React, { useEffect } from "react";
import {
  Box,
  CssBaseline,
  AppBar,
  Toolbar,
  Typography,
  Drawer,
  Divider,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
} from "@mui/material";
import { usePathname, useRouter } from "next/navigation";
import Link from "next/link";

const drawerWidth = 240;

const DashboardLayout = ({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) => {
  const router = useRouter();
  const pathname = usePathname();

  const navigation = [
    {
      label: "SOS",
      value: "/sos",
    },
    {
      label: "Чаты",
      value: "/chats",
    },
    {
      label: "Заявки",
      value: "/applications",
    },
    {
      label: "Новости",
      value: "/news",
    },
  ];

  useEffect(() => {
    const token = localStorage?.getItem("token");

    if (!token) {
      router.push("/auth");
    }
  }, []);

  const currentTitle = navigation.find(
    (item) => `/dashboard${item.value}` === pathname,
  )?.label;

  return (
    <Box sx={{ display: "flex" }}>
      <CssBaseline />
      <AppBar
        position="fixed"
        sx={{ width: `calc(100% - ${drawerWidth}px)`, ml: `${drawerWidth}px` }}
      >
        <Toolbar>
          <Typography variant="h6" noWrap component="div">
            {currentTitle}
          </Typography>
        </Toolbar>
      </AppBar>
      <Drawer
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          "& .MuiDrawer-paper": {
            width: drawerWidth,
            boxSizing: "border-box",
          },
        }}
        variant="permanent"
        anchor="left"
      >
        <Toolbar />
        <Divider />
        <List>
          {navigation.map(({ label, value }) => (
            <ListItem key={value} disablePadding>
              <Link
                href={`/dashboard${value}`}
                passHref
                style={{
                  textDecoration: "none",
                  width: "100%",
                  color: "inherit",
                }}
              >
                <ListItemButton>
                  <ListItemIcon></ListItemIcon>
                  <ListItemText primary={label} />
                </ListItemButton>
              </Link>
            </ListItem>
          ))}
        </List>
      </Drawer>
      <Box
        component="main"
        sx={{ flexGrow: 1, bgcolor: "background.default", p: 3 }}
      >
        <Toolbar />
        {children}
      </Box>
    </Box>
  );
};

export default DashboardLayout;
