"use client";

import { useEffect, useState } from "react";
import { ChatInterface } from "@/modules/chats/interfaces";
import { chatsData } from "@/modules/chats/mock";

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

  if (loading) return <div>Loading...</div>;

  return (
    <div>
      {data.map(({ id, phone, isNew }) => (
        <div className={"flex gap-1 justify-between"} key={id}>
          <div>{phone}</div>
          {/*TODO: этот флаг необходим для отображения кнопки начала чата*/}
          <div>{isNew && "Принять запрос на переписку"}</div>
        </div>
      ))}
    </div>
  );
};

export default ChatsPage;
