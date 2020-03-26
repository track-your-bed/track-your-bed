import * as API from "./APIService";
import { Ward } from "../datatypes/ListView.types";

export const getWardTypes = async () => API.APIGET(`/api/wardTypes`);

export const addWard = async (ward: Partial<Ward>) => {
  return API.APIPOST(`/api/wards`, ward);
};

export const deleteWard = async (wardId: string) => {
  return API.APIDELETE(`/api/wards/${wardId}`);
};
