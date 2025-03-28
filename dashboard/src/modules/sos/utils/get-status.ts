import { SosStatusEnum } from "@/modules/sos/interfaces";

export const getStatus = (status: SosStatusEnum): string => {
  let statusText = "";

  switch (status) {
    case SosStatusEnum.new:
      statusText = "Новый";
      break;
    case SosStatusEnum.inProgress:
      statusText = "В обработке";
      break;
    case SosStatusEnum.completed:
    default:
      statusText = "Завершен";
      break;
  }

  return statusText;
};
