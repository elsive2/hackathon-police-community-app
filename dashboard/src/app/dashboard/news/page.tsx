"use client";

import { Button } from "@mui/material";
import Link from "next/link";
import { useEffect, useState } from "react";

import { NewsInterface } from "@/modules/news/interfaces";

const NewsPage = () => {
  const [data, setData] = useState<NewsInterface[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);
    setTimeout(() => {
      const newsStorage = localStorage.getItem("news");

      const news: NewsInterface[] = [];

      if (newsStorage) {
        news.push(...JSON.parse(newsStorage));
      }

      setData(news);
      setLoading(false);
    }, 1500);
  }, []);

  if (loading) return <div>Loading...</div>;

  return (
    <>
      <div className={"flex flex-col gap-4"}>
        <div className={"ml-auto"}>
          <Link href={"news/add"}>
            <Button variant={"contained"}>Добавить новость</Button>
          </Link>
        </div>
        {data.map(({ id, title, content, date }) => (
          <div className={"flex gap-1 justify-between"} key={id}>
            <div>{title}</div>
            <div dangerouslySetInnerHTML={{ __html: content }} />
            <div>{date}</div>
            <Link href={"/dashboard/news/edit/" + id}>
              <Button className={"h-[36.5px]"} variant={"contained"}>
                Редактировать
              </Button>
            </Link>
          </div>
        ))}
      </div>
    </>
  );
};

export default NewsPage;
