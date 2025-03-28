"use client";

import { useEffect, useState } from "react";
import { ChatInterface } from "@/modules/chats/interfaces";
import { chatsData } from "@/modules/chats/mock";
import ChatsTable from "@/modules/chats/componets/ChatsTable";
import { SkeletonTable } from "@/modules/chats/componets/SkeletonTable";

const ChatsPage = () => {
  const [data, setData] = useState<ChatInterface[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);
    setTimeout(() => {
      setData(chatsData);
      setLoading(false);
    }, 1500);
  }, []);

  if (loading) return <div><SkeletonTable /></div>;

  return (
    <ChatsTable data={data}/>
  );
};

export default ChatsPage;
