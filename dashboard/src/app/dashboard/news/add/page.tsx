"use client";

import { useEffect, useState } from "react";
import { Button, Typography } from "@mui/material";
import {
  NewsCreateInterface,
  NewsErrorsInterface,
} from "@/modules/news/interfaces";
import NewsEditor from "@/modules/news/components/NewsEditor";
import NewsEditorSkeleton from "@/modules/news/components/NewsEditorSkeleton";
import { useRouter } from "next/navigation";
import { createNews } from "@/modules/news/api";

const NewsPage = () => {
  const router = useRouter();

  const [isLoading, setIsLoading] = useState(true);
  const [initialState, setInitialState] = useState<NewsCreateInterface>({
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
    setTimeout(() => {
      setIsLoading(false);
    }, 150);
  }, []);

  const onSave = () => {
    try {
      setIsLoading(true);

      createNews(initialState).then(() => {
        router.push("/dashboard/news");
      });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className={"flex flex-col gap-4"}>
      <div className={"flex justify-between"}>
        <Typography variant={"h5"} className={"flex"}>
          Добавить новость
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
