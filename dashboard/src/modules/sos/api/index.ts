import { apiRequest } from "@/utils/api";
import { BASE_SOS_ALERTS_API } from "@/modules/sos/constants";

export const getSosAlerts = () =>
  apiRequest({ url: `${BASE_SOS_ALERTS_API}?size=100` });
