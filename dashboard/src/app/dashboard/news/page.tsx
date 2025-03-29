"use client";

import { Button } from "@mui/material";
import Link from "next/link";
import { useEffect, useState } from "react";

import { NewsInterface } from "@/modules/news/interfaces";
import { getNews } from "@/modules/news/api";

const NewsPage = () => {
  const [data, setData] = useState<NewsInterface[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    try {
      setLoading(true);
      getNews()
        .then((response) => {
          setData(response.data.content as NewsInterface[]);
        })
        .finally(() => setLoading(false));
    } catch (e) {
      console.error(e);
    }
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
        {data.map(({ id, title, content }) => (
          <div className={"flex gap-1 justify-between"} key={id}>
            <div>{title}</div>
            <div dangerouslySetInnerHTML={{ __html: content }} />
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
