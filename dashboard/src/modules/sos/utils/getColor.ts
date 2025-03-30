import { SosStatusEnum } from "@/modules/sos/interfaces";

export const getColor = (status: SosStatusEnum) => {
  switch (status) {
    case SosStatusEnum.new:
      return {
        background: "yellow",
        color: "black",
      };
    case SosStatusEnum.inProgress:
      return {
        background: "aqua",
        color: "white",
      };
    case SosStatusEnum.completed:
    default:
      return {
        background: "gray",
        color: "black",
      };
  }
};
