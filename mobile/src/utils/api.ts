import axios, { AxiosRequestConfig } from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

export const apiRequest = async (props: AxiosRequestConfig) => {
  const token = await AsyncStorage.getItem("token");

  let headers = { ...props.headers };

  if (token) {
    headers = { ...headers, Authorization: `Bearer ${token}` };
  }

  const axiosInstance = axios.create();

  return await axiosInstance.request({
    ...props,
    headers,
  });
};
