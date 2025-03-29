import {
  NewsCreateInterface,
  NewsEditInterface,
} from "@/modules/news/interfaces";
import { BASE_NEWS_API } from "@/modules/news/constants";
import { apiRequest } from "@/utils/api";

export const createNews = (data: NewsCreateInterface) =>
  apiRequest({ url: BASE_NEWS_API, data, method: "POST" });

export const getNews = () => apiRequest({ url: `${BASE_NEWS_API}?size=100` });

export const getNewsById = (id: number) =>
  apiRequest({ url: `${BASE_NEWS_API}/${id}` });

export const updateNews = (id: number, data: NewsEditInterface) =>
  apiRequest({ url: `${BASE_NEWS_API}/${id}`, data, method: "PUT" });
