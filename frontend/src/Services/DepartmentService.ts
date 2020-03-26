import * as API from "./APIService";
import { Department } from "../datatypes/ListView.types";

export const getDepartment = async (departmentId: string) =>
  API.APIGET(`/api/departments/${departmentId}`);

export const getDepartmentTypes = async () => API.APIGET(`/api/departmentTypes`);

export const addDepartment = async (department: Partial<Department>) =>
  API.APIPOST(`/api/departments`, department);

export const deleteDepartment = async (departmentId: string) =>
  API.APIDELETE(`/api/departments/${departmentId}`);
