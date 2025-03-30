import { apiRequest } from "@/utils/api";
import { BASE_FILES_API } from "@/constants";

export const uploadFile = (data: FormData) =>
  apiRequest({
    url: BASE_FILES_API,
    data,
    method: "POST",
    headers: { "Content-Type": "multipart/form-data" },
  });
