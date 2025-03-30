import { apiRequest } from "../../../utils/api";
import { ApplicationInterface } from "../interfaces";
import { BASE_APPLICATIONS_API } from "../constants";

export const createApplication = (data: ApplicationInterface) =>
  apiRequest({ url: BASE_APPLICATIONS_API, data, method: "POST" });
