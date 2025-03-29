export interface AuthForm {
  phone: string;
  code: string;
  state: AuthFormState;
}

export enum AuthFormState {
  RequestCode,
  VerifyCode,
}
