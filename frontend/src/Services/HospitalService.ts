import * as API from "./APIService";

const API_STRING_HOSPITALS = "/api/hospitals/";

export const postHospital = async (hospitalId: string, dto: any) => {
  const data = await API.APIPOST(API_STRING_HOSPITALS + hospitalId, dto);
  return data;
};

export const getAllHospitals = async () => API.APIGET(API_STRING_HOSPITALS);

export const getHospitalCapacity = async (hospitalId: string) =>
  API.APIGET(`${API_STRING_HOSPITALS}${hospitalId}/capacity`);

export const getHospital = async (hospitalId: string) =>
  API.APIGET(API_STRING_HOSPITALS + hospitalId);

export const patchHospital = async (hospitalId: string, dto: any) => {
  const data = await API.APIPATCH(API_STRING_HOSPITALS + hospitalId, dto);
  return data;
};

export const deleteHospital = async (hospitalId: string, dto?: any) => {
  const data = await API.APIDELETE(API_STRING_HOSPITALS + hospitalId, dto);
  return data;
};
