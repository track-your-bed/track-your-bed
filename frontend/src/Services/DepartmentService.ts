import * as API from "./APIService";
import { Department } from "../datatypes/ListView.types";

const API_STRING_DEPARTMENTS = "/api/departments/";

export const getDepartment = async (departmentId: string) =>
  API.APIGET(API_STRING_DEPARTMENTS + departmentId);

export const getDepartmentTypes = async () => API.APIGET(`/api/departmentTypes`);

export const addDepartment = async (department: Partial<Department>) =>
  API.APIPOST(API_STRING_DEPARTMENTS, department);

export const deleteDepartment = async (departmentId: string) =>
  API.APIDELETE(API_STRING_DEPARTMENTS + departmentId);
