import { apiRequest } from "@/utils/api";
import { BASE_CATEGORIES_API, BASE_FILES_API } from "@/constants";

export const uploadFile = (data: FormData) =>
  apiRequest({
    url: BASE_FILES_API,
    data,
    method: "POST",
    headers: { "Content-Type": "multipart/form-data" },
  });

export const getCategories = () =>
  apiRequest({
    url: BASE_CATEGORIES_API,
  });
