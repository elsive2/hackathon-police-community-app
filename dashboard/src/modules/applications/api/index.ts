import { apiRequest } from "@/utils/api";
import { BASE_APPLICATIONS_API } from "@/modules/applications/constants";

export const getApplications = () => apiRequest({ url: BASE_APPLICATIONS_API });
