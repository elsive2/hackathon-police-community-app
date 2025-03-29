import axios, { AxiosRequestConfig } from "axios";

export const apiRequest = async (props: AxiosRequestConfig) => {
  const token = localStorage.getItem("token");

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
