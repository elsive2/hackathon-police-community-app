import { Tabs } from "expo-router";
import React from "react";

export default function TabLayout() {
  return (
    <Tabs
      screenOptions={{
        headerShown: false,
      }}
    >
      <Tabs.Screen
        name="index"
        options={{
          title: "Новости",
        }}
      />
      <Tabs.Screen
        name="sos"
        options={{
          title: "SOS",
        }}
      />
      <Tabs.Screen
        name="chat"
        options={{
          title: "Чат",
        }}
      />
      <Tabs.Screen
        name="applications"
        options={{
          title: "ЧП",
        }}
      />
    </Tabs>
  );
}
