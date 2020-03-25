import * as API from "./APIService";
import { BedState } from "../datatypes/ListView.types";

export const getBedsForWard = async (wardId: string): Promise<any> => {
  const data = await API.APIGET(`/api/wards/${wardId}`);
  return data;
};

export const updateBedState = async (bedId: string, bedState: BedState) => {
  return API.APIPUT(`/api/beds/${bedId}/bedState`, {
    name: BedState[bedState]
  });
};
