import { Alert, StyleSheet, View } from "react-native";
import { Button, Text, TextInput } from "react-native-paper";
import Layout from "../components/Layout";
import { useEffect, useState } from "react";
import { ApplicationInterface } from "../modules/applications/interfaces";
import { getCategories } from "../api";
import { CategoryInterface } from "../interfaces/category";
import { Dropdown } from "react-native-paper-dropdown";
import * as Location from "expo-location";
import { createApplication } from "../modules/applications/api";
import * as Notifications from "expo-notifications";

Notifications.setNotificationHandler({
  handleNotification: async () => ({
    shouldShowAlert: true,
    shouldPlaySound: false,
    shouldSetBadge: false,
  }),
});

const initialState: ApplicationInterface = {
  title: "",
  category: "",
  content: "",
  latitude: 0,
  longitude: 0,
  photos: [],
};

const ApplicationTab = () => {
  const [categories, setCategories] = useState<
    { label: string; value: string }[]
  >([]);

  const [isLoading, setIsLoading] = useState(false);

  const [formData, setFormData] = useState<ApplicationInterface>(initialState);

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

  useEffect(() => {
    getCategories().then((res) => {
      const categories = res.data.values as CategoryInterface[];

      setCategories(
        categories.map((category) => ({
          label: category.displayName,
          value: category.name,
        })),
      );
    });
  }, []);

  const onPressButton = async () => {
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

      await createApplication({ ...formData, latitude, longitude });

      await Notifications.scheduleNotificationAsync({
        content: {
          title: "ЧП ",
          body: "Ваше обращение отправлено",
        },
        trigger: null,
      });

      setFormData(initialState);
    } catch (e) {
      console.log(e);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <Layout>
      <View style={styles.view}>
        <Text style={styles.title}>Сообщить о происшествии</Text>
        <TextInput
          label={"Заголовок"}
          value={formData.title}
          onChangeText={(text) =>
            setFormData((prevState) => ({ ...prevState, title: text }))
          }
          disabled={isLoading}
        />
        <Dropdown
          label={"Категория происшествия"}
          onSelect={(value) =>
            value &&
            setFormData((prevState) => ({ ...prevState, category: value }))
          }
          value={formData.category}
          options={categories}
          disabled={isLoading}
        />
        <TextInput
          label={"Описание происшествия"}
          onChangeText={(text) =>
            setFormData((prevState) => ({ ...prevState, content: text }))
          }
          numberOfLines={10}
          multiline={true}
          disabled={isLoading}
          value={formData.content}
        />
        <Button
          mode={"contained-tonal"}
          disabled={isLoading}
          onPress={onPressButton}
        >
          Отправить
        </Button>
      </View>
    </Layout>
  );
};

const styles = StyleSheet.create({
  view: {
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    gap: 40,
    height: "100%",
  },
  title: {
    textAlign: "center",
    fontSize: 26,
    fontWeight: 500,
  },
});

export default ApplicationTab;
