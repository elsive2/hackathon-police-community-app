import { StyleSheet, View } from "react-native";
import { Button, Divider, Text, TextInput } from "react-native-paper";
import { useState } from "react";
import { AuthForm, AuthFormState } from "../modules/auth/interface";
import { requestCode, verifyCode } from "../modules/auth/api";
import AsyncStorage from "@react-native-async-storage/async-storage";
import { CommonActions, useNavigation } from "@react-navigation/native";

const AuthScreen = () => {
  const navigation = useNavigation();

  const [formState, setFormState] = useState<AuthForm>({
    phone: "",
    code: "",
    state: AuthFormState.RequestCode,
  });

  const onPressButton = () => {
    if (formState.state === AuthFormState.RequestCode) {
      requestCode(formState.phone).then(() => {
        setFormState((prevState) => ({
          ...prevState,
          state: AuthFormState.VerifyCode,
        }));
      });
    } else {
      const { state, ...data } = formState;

      verifyCode(data).then(async (res) => {
        await AsyncStorage.setItem("token", res.data.token);

        navigation.dispatch(
          CommonActions.reset({
            index: 0,
            routes: [{ name: "index" }],
          }),
        );
      });
    }
  };

  const onPressSOSButton = () => {
    console.log("sos");
  };

  return (
    <View style={styles.card}>
      <Text style={styles.header}>Введите номер</Text>
      <TextInput
        label={"Номер телефона"}
        value={formState.phone}
        onChangeText={(text) =>
          setFormState((prevState) => ({ ...prevState, phone: text }))
        }
      />
      {formState.state === AuthFormState.VerifyCode && (
        <TextInput
          label={"Одноразовый код"}
          value={formState.code}
          onChangeText={(text) =>
            setFormState((prevState) => ({ ...prevState, code: text }))
          }
        />
      )}
      <Button onPress={onPressButton} mode={"contained"} style={styles.button}>
        {formState.state === AuthFormState.RequestCode
          ? "Запросить код"
          : "Подтвердить код"}
      </Button>
      <Divider />
      <Button
        buttonColor={"red"}
        onPress={onPressSOSButton}
        textColor={"#fff"}
        style={styles.buttonSOS}
        mode={"contained-tonal"}
      >
        SOS
      </Button>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    backgroundColor: "white",
    padding: 16,
    height: "100%",
    paddingTop: "50%",
    display: "flex",
    flexDirection: "column",
    gap: 40,
  },
  header: {
    fontSize: 20,
    fontWeight: 400,
    textAlign: "center",
  },
  button: {
    borderRadius: 8,
  },
  buttonSOS: {
    display: "flex",
    justifyContent: "center",
    color: "#fff",
    height: 50,
  },
});

export default AuthScreen;
