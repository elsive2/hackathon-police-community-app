import { REQUEST_CODE_API, VERIFY_CODE_API } from "@/modules/auth/constants";
import axios from "axios";

export const requestCode = (phone: string) =>
  axios.post(REQUEST_CODE_API, { phone });

export const verifyCode = ({ phone, code }: { phone: string; code: string }) =>
  axios.post(VERIFY_CODE_API, { phone, code });
