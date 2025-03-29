import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import IndexScreen from "./src/screens/IndexScreen";
import AuthScreen from "./src/screens/AuthScreen";
import { PaperProvider } from "react-native-paper";

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <PaperProvider theme={{ dark: false }}>
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen
            name="index"
            component={IndexScreen}
            options={{ title: "Главная" }}
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
