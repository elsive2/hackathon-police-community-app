"use client";

import { SOSInterface } from "@/modules/sos/interfaces";
import { useEffect, useState } from "react";
import { sosData } from "@/modules/sos/mock";
import SosTable from "@/modules/sos/components/SosTable";
import SkeletonTable from "@/modules/sos/components/SkeletonTable";

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

  if (loading) return <div><SkeletonTable /></div>;

  return (
    <SosTable data={data}/>
  );
};

export default SosPage;
