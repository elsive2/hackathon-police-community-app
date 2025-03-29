import { Button, Text, View } from "react-native";
import { CommonActions, useNavigation } from "@react-navigation/native";
import { useEffect } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";

const NewsScreen = () => {
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

  return (
    <View>
      <Text>News</Text>
      <Button
        title={"Auth"}
        onPress={() =>
          navigation.dispatch(
            CommonActions.reset({
              index: 0,
              routes: [{ name: "auth" }],
            }),
          )
        }
      ></Button>
    </View>
  );
};

export default NewsScreen;
