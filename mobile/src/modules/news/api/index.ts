import { BASE_NEWS_API } from "../constants";
import { apiRequest } from "../../../utils/api";

export const getNews = () => apiRequest({ url: `${BASE_NEWS_API}?size=100` });

export const getNewsById = (id: number) =>
  apiRequest({ url: `${BASE_NEWS_API}/${id}` });
