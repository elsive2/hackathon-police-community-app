"use client";

import {
  Button,
  Card,
  FormControl,
  Input,
  InputLabel,
  Typography,
} from "@mui/material";
import phoneInput from "@/components/phoneInput";
import { useEffect, useState } from "react";
import { AuthForm, AuthFormState } from "@/modules/auth/interface";
import { requestCode, verifyCode } from "@/modules/auth/api";
import { useRouter } from "next/navigation";

const AuthPage = () => {
  const router = useRouter();
  const [isLoading, setIsLoading] = useState(false);

  const [formState, setFormState] = useState<AuthForm>({
    phone: "",
    code: "",
    state: AuthFormState.RequestCode,
  });

  useEffect(() => {
    const token = localStorage?.getItem("token");

    if (token) {
      router.push("/dashboard");
    }
  }, []);

  const onClickButton = () => {
    setIsLoading(true);

    try {
      if (formState.state === AuthFormState.RequestCode) {
        requestCode(formState.phone).then(() => {
          setFormState((prevState) => ({
            ...prevState,
            state: AuthFormState.VerifyCode,
          }));
        });
      } else {
        const { state, ...data } = formState;

        verifyCode(data).then((res) => {
          localStorage.setItem("token", res.data.token);
          router.push("/dashboard");
        });
      }
    } catch (e) {
      console.error(e);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className={"min-h-[100vh] flex justify-center items-center"}>
      <Card
        className={
          "min-h-[300px] min-w-[320px] max-w-[360px] p-4 flex flex-col justify-between gap-8"
        }
      >
        <Typography className={"text-center"} variant={"h6"}>
          Авторизация
        </Typography>
        <FormControl variant="standard">
          <InputLabel htmlFor="formatted-text-mask-input">
            Номер телефона
          </InputLabel>
          <Input
            disabled={isLoading}
            autoFocus={true}
            value={formState.phone}
            onChange={(e) =>
              setFormState((prevState) => ({
                ...prevState,
                phone: e.target.value,
              }))
            }
            inputComponent={phoneInput as any}
          />
        </FormControl>
        {formState.state === AuthFormState.VerifyCode && (
          <FormControl variant="standard">
            <InputLabel>Одноразовый код</InputLabel>
            <Input
              disabled={isLoading}
              value={formState.code}
              onChange={(e) =>
                setFormState((prevState) => ({
                  ...prevState,
                  code: e.target.value,
                }))
              }
            />
          </FormControl>
        )}
        <Button onClick={onClickButton} disabled={isLoading}>
          {formState.state === AuthFormState.RequestCode
            ? "Запросить код"
            : "Войти"}
        </Button>
      </Card>
    </div>
  );
};

export default AuthPage;
