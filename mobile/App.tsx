import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import NewsScreen from "./src/screens/NewsScreen";
import AuthScreen from "./src/screens/AuthScreen";
import { PaperProvider } from "react-native-paper";

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <PaperProvider>
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen
            name="news"
            component={NewsScreen}
            options={{ title: "Новости" }}
          />
          <Stack.Screen
            name="auth"
            component={AuthScreen}
            options={{ title: "Авторизация" }}
          />
        </Stack.Navigator>
      </NavigationContainer>
    </PaperProvider>
  );
}
