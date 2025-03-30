import { CommonActions, useNavigation } from "@react-navigation/native";
import { useEffect, useLayoutEffect, useState } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { BottomNavigation, Text } from "react-native-paper";
import NewsTab from "../tabs/News";
import SosTab from "../tabs/Sos";

const SosRoute = () => <Text>Sos</Text>;

const ChatRoute = () => <Text>Chat</Text>;

const ApplicationsRoute = () => <Text>Notifications</Text>;

const IndexScreen = () => {
  const navigation = useNavigation();

  useEffect(() => {
    const checkAuth = async () => {
      const token = await AsyncStorage.getItem("token");

      if (!token) {
        navigation.dispatch(
          CommonActions.reset({
            index: 0,
            routes: [{ name: "auth" }],
          }),
        );
      }
    };

    checkAuth();
  }, []);

  const [index, setIndex] = useState(0);
  const [routes] = useState([
    {
      key: "news",
      title: "Новости",
      focusedIcon: "newspaper",
      unfocusedIcon: "newspaper-variant-outline",
    },
    {
      key: "sos",
      title: "SOS",
      focusedIcon: "alert",
      unfocusedIcon: "alert-outline",
    },
    {
      key: "chat",
      title: "Чат",
      focusedIcon: "chat",
      unfocusedIcon: "chat-outline",
    },
    {
      key: "application",
      title: "ЧП",
      focusedIcon: "information",
      unfocusedIcon: "information-outline",
    },
  ]);

  useLayoutEffect(() => {
    const currentTabTitle = routes[index].title;
    navigation.setOptions({
      title: currentTabTitle,
    });
  }, [index, routes, navigation]);

  const renderScene = BottomNavigation.SceneMap({
    news: NewsTab,
    sos: SosTab,
    chat: ChatRoute,
    application: ApplicationsRoute,
  });

  return (
    <BottomNavigation
      navigationState={{ index, routes }}
      onIndexChange={setIndex}
      renderScene={renderScene}
    />
  );
};

export default IndexScreen;
