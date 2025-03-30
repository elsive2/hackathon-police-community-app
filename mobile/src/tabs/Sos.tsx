import { StyleSheet, View } from "react-native";
import { Button, Text } from "react-native-paper";
import { useState } from "react";
import ConfirmationDialog from "../modules/sos/components/ConfirmationDialog";

const SosTab = () => {
  const [showDialog, setShowDialog] = useState(false);

  const onPressButton = () => {
    setShowDialog(true);
  };

  return (
    <>
      <View style={styles.view}>
        <Text style={styles.title}>
          Для отправки экстренного сигнала нажмите кнопку
        </Text>
        <Button
          onPress={onPressButton}
          contentStyle={styles.buttonContent}
          textColor={"#fff"}
          style={styles.button}
          mode={"contained-tonal"}
        >
          SOS
        </Button>
        <Text style={styles.description}>
          Если вы редиска, и просто нажмете экстренную кнопку - понесете
          ответственность
        </Text>
      </View>
      <ConfirmationDialog
        visible={showDialog}
        hideDialog={() => setShowDialog(false)}
      />
    </>
  );
};

const styles = StyleSheet.create({
  view: {
    minHeight: "100%",
    display: "flex",
    justifyContent: "center",
    gap: 30,
    padding: 16,
  },
  title: {
    textAlign: "center",
    fontSize: 26,
    fontWeight: 500,
  },
  button: {
    backgroundColor: "red",
  },
  buttonContent: {
    height: 60,
  },
  description: {
    textAlign: "center",
    fontSize: 18,
  },
});

export default SosTab;
