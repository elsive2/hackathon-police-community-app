"use client";

import { useEffect, useState } from "react";
import { Button, Typography } from "@mui/material";
import { format } from "date-fns";
import {
  NewsEditInterface,
  NewsErrorsInterface,
  NewsInterface,
} from "@/modules/news/interfaces";
import NewsEditor from "@/modules/news/components/NewsEditor";
import NewsEditorSkeleton from "@/modules/news/components/NewsEditorSkeleton";
import { useParams, useRouter } from "next/navigation";

const NewsPage = () => {
  const { id } = useParams();

  const router = useRouter();

  const [isLoading, setIsLoading] = useState(true);
  const [initialState, setInitialState] = useState<NewsEditInterface>({
    title: "",
    date: "",
    content: "",
    id: null,
  });

  // TODO: Add validation
  const [errors, setErrors] = useState<NewsErrorsInterface>({
    title: "",
    content: "",
  });

  // TODO: Remove after mocks remove
  const newsStorage = localStorage.getItem("news");

  const news: NewsInterface[] = [];

  if (newsStorage) {
    news.push(...JSON.parse(newsStorage));
  }

  useEffect(() => {
    setTimeout(() => {
      setIsLoading(false);

      if (news.length > 0) {
        const currentNewsItem = news.find((el) => el.id === Number(id));

        currentNewsItem && setInitialState(currentNewsItem);
      }

      //   TODO: Переделать после удаления моков
    }, 150);
  }, []);

  const onSave = () => {
    /**
     * После того как API будет готова, убрать:
     * setIsLoading
     * setTimeout
     * const now = new Date();
     * const formattedDate = format(now, "dd.MM.yyyy HH:mm:ss");
     * const id = new Date().getTime();
     * newsStorage
     * localStorage.setItem
     */
    setIsLoading(true);
    setTimeout(() => {
      const now = new Date();
      const formattedDate = format(now, "dd.MM.yyyy HH:mm:ss");

      // TODO: Remove

      const newNewsArray = news.map((item) => {
        if (item.id === Number(id)) {
          return { ...initialState, date: formattedDate };
        } else {
          return item;
        }
      });

      localStorage.setItem("news", JSON.stringify(newNewsArray));

      setIsLoading(false);
    }, 500);

    router.push("/dashboard/news");
  };

  return (
    <div className={"flex flex-col gap-4"}>
      <div className={"flex justify-between"}>
        <Typography variant={"h5"} className={"flex"}>
          Редактировать новость
        </Typography>
        <Button loading={isLoading} onClick={onSave} variant={"contained"}>
          Сохранить
        </Button>
      </div>
      {isLoading ? (
        <NewsEditorSkeleton />
      ) : (
        <NewsEditor
          title={initialState.title}
          onChangeTitle={(value: string) =>
            setInitialState({ ...initialState, title: value })
          }
          content={initialState.content}
          onChangeContent={(value: string) =>
            setInitialState((prevState) => ({
              ...prevState,
              content: value,
            }))
          }
        />
      )}
    </div>
  );
};

export default NewsPage;
