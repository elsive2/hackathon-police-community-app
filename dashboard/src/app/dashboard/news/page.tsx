"use client";

import { Button } from "@mui/material";
import Link from "next/link";
import { useEffect, useState } from "react";

import { NewsInterface } from "@/modules/news/interfaces";
import { getNews } from "@/modules/news/api";
import NewsCard from "@/modules/news/components/NewsCard";

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
        {data.map(({ id, title, content, creationDate })=> (
          <NewsCard key={id} id={id} title={title} content={content} creationDate={creationDate}/>
        ))}
      </div>
    </>
  );
};

export default NewsPage;
