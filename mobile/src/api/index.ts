import { apiRequest } from "../utils/api";
import { BASE_CONTEXT_API_URL } from "../constants";

export const getCategories = () =>
  apiRequest({ url: `${BASE_CONTEXT_API_URL}/query-category` });
