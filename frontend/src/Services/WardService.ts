import * as API from "./APIService";
import { Ward } from "../datatypes/ListView.types";

const API_STRING_WARD = "/api/wards";

export const getWardTypes = async () => API.APIGET(`/api/wardTypes`);

export const addWard = async (ward: Partial<Ward>) => {
  return API.APIPOST(API_STRING_WARD, ward);
};

export const deleteWard = async (wardId: string) => {
  return API.APIDELETE(API_STRING_WARD + wardId);
};
