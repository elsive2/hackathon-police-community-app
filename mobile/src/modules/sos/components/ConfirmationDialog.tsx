import { Button, Dialog, Portal, Text, TextInput } from "react-native-paper";
import { Alert, StyleSheet } from "react-native";
import { useEffect, useState } from "react";
import * as Location from "expo-location";
import * as Notifications from "expo-notifications";
import { sendSOSSignal } from "../api";

Notifications.setNotificationHandler({
  handleNotification: async () => ({
    shouldShowAlert: true,
    shouldPlaySound: false,
    shouldSetBadge: false,
  }),
});

interface ConfirmationDialogProps {
  visible: boolean;
  hideDialog: () => void;
}

const ConfirmationDialog = ({
  visible,
  hideDialog,
}: ConfirmationDialogProps) => {
  const [isLoading, setIsLoading] = useState(false);
  const [comment, setComment] = useState("");

  useEffect(() => {
    (async () => {
      const { status: existingStatus } =
        await Notifications.getPermissionsAsync();
      let finalStatus = existingStatus;

      if (existingStatus !== "granted") {
        const { status } = await Notifications.requestPermissionsAsync();
        finalStatus = status;
      }

      if (finalStatus !== "granted") {
        Alert.alert("Ошибка", "Необходимо разрешение на отправку уведомлений!");
        return;
      }
    })();
  }, []);

  const onPressCancel = () => {
    hideDialog();
  };

  const onPressApply = async () => {
    try {
      setIsLoading(true);
      let { status } = await Location.requestForegroundPermissionsAsync();
      if (status !== "granted") {
        console.error("Permission to access location was denied");
        return;
      }

      const {
        coords: { latitude, longitude },
      } = await Location.getCurrentPositionAsync({});

      await sendSOSSignal({ latitude, longitude, comment });

      await Notifications.scheduleNotificationAsync({
        content: {
          title: "Вызов SOS",
          body: "Ваше обращение отправлено. Ожидайте!",
        },
        trigger: null,
      });
    } catch (e: any) {
      console.error(e);
    } finally {
      setIsLoading(false);
      hideDialog();
    }
  };

  return (
    <Portal>
      <Dialog visible={visible} onDismiss={hideDialog}>
        <Dialog.Title>Вызов SOS</Dialog.Title>
        <Dialog.Content style={styles.content}>
          <Text variant="bodyMedium">Вы подтверждаете вызов?</Text>
          <Text variant="bodyMedium">
            В службу экстренного реагирования будет отправлена Ваша текущая
            геопозиция
          </Text>
          <TextInput
            onChangeText={(text) => setComment(text)}
            value={comment}
            label={"Детали вызова (опционально)"}
          />
        </Dialog.Content>
        <Dialog.Actions>
          <Button disabled={isLoading} onPress={onPressCancel}>
            Отмена
          </Button>
          <Button disabled={isLoading} onPress={onPressApply}>
            Да
          </Button>
        </Dialog.Actions>
      </Dialog>
    </Portal>
  );
};

const styles = StyleSheet.create({
  content: {
    display: "flex",
    flexDirection: "column",
    gap: 20,
  },
});

export default ConfirmationDialog;
