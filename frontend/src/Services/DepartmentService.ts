import * as API from "./APIService";
import { Department } from "../datatypes/ListView.types";

export const getDepartmentTypes = async () => {
  const data = await API.APIGET(`/api/departmentTypes`);
  return data;
};

export const addDepartment = async (department: Department) => {
  return API.APIPOST(`/api/departments`, department);
};

export const deleteDepartment = async (departmentId: string) => {
  return API.APIDELETE(`/api/departments/${departmentId}`);
};

/*
export const updateBedState = async (bedId: string, bedState: BedState) => {
  return API.APIPUT(`/api/beds/${bedId}/bedState`, {
    name: BedState[bedState]
  });
  http://track-your-bed.org/api/departments/
};
*/

// export const postHospital = async (id: string, dto: any) => {
//   const data = await API.APIPOST(`api/hospitals/${id}`, dto);
//   return data;
// };

// export const getAllHospitals = async () => {
//   const data = await API.APIGET("/api/hospitals");
//   return data;
// };

// export const getHospitalCapacity = async (hospitalId: string) => {
//   const data = await API.APIGET(`/api/hospitals/${hospitalId}/capacity`);
//   return data;
// };

// export const getHospital = async (hospitalId: string) => {
//   const data = await API.APIGET(`/api/hospitals/${hospitalId}`);
//   return data;
// };

// export const patchHospital = async (id: string, dto: any) => {
//   const data = await API.APIPATCH(`/api/hospitals/${id}`, dto);
//   return data;
// };

// export const deleteHospital = async (id: string, dto?: any) => {
//   const data = await API.APIDELETE(`/api/hospitals/${id}`, dto);
//   return data;
// };
