"use client";

import { SOSInterface } from "@/modules/sos/interfaces";
import { useEffect, useState } from "react";
import { sosData } from "@/modules/sos/mock";
import { getStatus } from "@/modules/sos/utils/get-status";

const SosPage = () => {
  const [data, setData] = useState<SOSInterface[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);
    setTimeout(() => {
      setData(sosData);
      setLoading(false);
    }, 1500);
  }, []);

  if (loading) return <div>Loading...</div>;

  return (
    <div>
      {data.map(({ id, phone, status }) => (
        <div key={id}>
          <div>{phone}</div>
          <div>{getStatus(status)}</div>
        </div>
      ))}
    </div>
  );
};

export default SosPage;
