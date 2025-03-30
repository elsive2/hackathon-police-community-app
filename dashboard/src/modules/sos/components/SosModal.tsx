"use client";

import Dialog from "@mui/material/Dialog";
import { Button, Typography } from "@mui/material";
import { YMaps, Map, Placemark } from "@pbe/react-yandex-maps";
import { SOSInterface } from "@/modules/sos/interfaces";

interface SosModalProps {
  open: boolean;
  onClose: () => void;
  data: SOSInterface | null;
}

const SosModal = ({ open, onClose, data }: SosModalProps) => {
  if (!data) return null;

  const defaultState = {
    center: [data.latitude, data.longitude],
    zoom: 12,
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <div className={"flex flex-col gap-8 w-[500px] h-[400px] bg-white p-4"}>
        <div className={"flex justify-between"}>
          <Typography
            sx={{ fontSize: 24, fontWeight: 500 }}
            className={"text-center"}
          >
            Информация об обращении
          </Typography>
          <Button className={"ml-auto"} variant={"outlined"} color={"primary"}>
            X
          </Button>
        </div>
        <YMaps>
          <Map
            style={{ width: "100%", height: "100%" }}
            defaultState={defaultState}
          >
            <Placemark geometry={[data.latitude, data.longitude]} />
          </Map>
        </YMaps>
        <Typography>{data.phoneNumber}</Typography>
      </div>
    </Dialog>
  );
};

export default SosModal;
