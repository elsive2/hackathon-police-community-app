"use client";

import { useEffect, useState } from "react";
import { Button, Typography } from "@mui/material";
import {
  NewsEditInterface,
  NewsErrorsInterface,
  NewsInterface,
} from "@/modules/news/interfaces";
import NewsEditor from "@/modules/news/components/NewsEditor";
import NewsEditorSkeleton from "@/modules/news/components/NewsEditorSkeleton";
import { useParams, useRouter } from "next/navigation";
import { getNewsById, updateNews } from "@/modules/news/api";

const NewsPage = () => {
  const { id } = useParams();

  const router = useRouter();

  const [isLoading, setIsLoading] = useState(true);
  const [initialState, setInitialState] = useState<NewsEditInterface>({
    title: "",
    content: "",
    editable: true,
  });

  // TODO: Add validation
  const [errors, setErrors] = useState<NewsErrorsInterface>({
    title: "",
    content: "",
  });

  useEffect(() => {
    setIsLoading(true);
    try {
      getNewsById(Number(id))
        .then((res) => {
          const newsData = res.data as NewsInterface;

          setInitialState((prevState) => ({
            ...prevState,
            title: newsData.title,
            content: newsData.content,
          }));
        })
        .finally(() => {
          setIsLoading(false);
        });
    } finally {
    }
  }, []);

  const onSave = () => {
    try {
      updateNews(Number(id), initialState)
        .then(() => {
          router.push("/dashboard/news");
        })
        .finally(() => setIsLoading(false));
    } catch (e) {
      console.error(e);
    }
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
