import { StyleSheet, View } from "react-native";
import React from "react";

const Layout = ({ children }: { children: React.ReactNode }) => {
  return <View style={styles.container}>{children}</View>;
};

const styles = StyleSheet.create({
  container: {
    padding: 16,
  },
});

export default Layout;
