import * as API from "./APIService";

export const postHospital = async (id: string, dto: any) => {
  const data = await API.APIPOST(`/api/hospitals/${id}`, dto);
  return data;
};

export const getAllHospitals = async () => API.APIGET("/api/hospitals");

export const getHospitalCapacity = async (hospitalId: string) =>
  API.APIGET(`/api/hospitals/${hospitalId}/capacity`);

export const getHospital = async (hospitalId: string) => API.APIGET(`/api/hospitals/${hospitalId}`);

export const patchHospital = async (id: string, dto: any) => {
  const data = await API.APIPATCH(`/api/hospitals/${id}`, dto);
  return data;
};

export const deleteHospital = async (id: string, dto?: any) => {
  const data = await API.APIDELETE(`/api/hospitals/${id}`, dto);
  return data;
};
