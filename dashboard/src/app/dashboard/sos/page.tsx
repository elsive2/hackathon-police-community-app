"use client";

import { SOSInterface } from "@/modules/sos/interfaces";
import { useEffect, useState } from "react";
import { sosData } from "@/modules/sos/mock";
import SosTable from "@/modules/sos/components/SosTable";
import SkeletonTable from "@/modules/sos/components/SkeletonTable";
import { getSosAlerts } from "@/modules/sos/api";

const SosPage = () => {
  const [data, setData] = useState<SOSInterface[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
      try {
        setLoading(true);
        getSosAlerts()
          .then((response) => {
            setData(response.data.content as SOSInterface[]);
          })
          .finally(() => setLoading(false));
      } catch (e) {
        console.error(e);
      }
    }, []);

  useEffect(() => {
    getSosAlerts().then((res) => {
      console.log(res);
    });
  }, []);

  if (loading) return <SkeletonTable />;

  return <SosTable data={data} />;
};

export default SosPage;
