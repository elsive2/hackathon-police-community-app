import { BASE_SOS_API } from "../constants";
import { SOSInterface } from "../interfaces";
import { apiRequest } from "../../../utils/api";

export const sendSOSSignal = (body: SOSInterface) =>
  apiRequest({ url: BASE_SOS_API, method: "POST", data: body });
