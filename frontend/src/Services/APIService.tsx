import axios from "axios";

export const APIGET = async (url: string) => {
  const response = await fetch(url);
  const json = await response.json();
  return json;
};

export const APIPOST = async (url: string, data: any) => {
  const response = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });
  const json = await response.json();
  return json;
};

export const APIPUT = async (url: string, data: any) => {
  const response = await fetch(url, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });
  const json = await response.json();
  return json;
};

export const APIPATCH = async (url: string, data: any) => {
  const object = await axios.patch(url, data);
  const response = await object;
  return response;
};

export const APIDELETE = async (url: string, data?: any) => {
  const response = await fetch(url, {
    method: "DELETE"
  });

  return { status: response.status, statusText: response.statusText };
};
