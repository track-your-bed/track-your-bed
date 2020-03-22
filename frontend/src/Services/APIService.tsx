import * as React from "react";
import axios from 'axios';

export const APIGET = async (url: string) => {    
    const object = await axios.get(url);    
    const json = await object.data;
    return json;
}

export const APIPOST = async (url: string, data: any) => {    
    const object = await axios.post(url, data);    
    const response = await object;
    return response;
}

export const APIPUT = async (url: string, data: any) => {    
    const object = await axios.put(url, data);    
    const response = await object;
    return response;
}

export const APIPATCH = async (url: string, data: any) => {    
    const object = await axios.patch(url, data);    
    const response = await object;
    return response;
}

export const APIDELETE = async (url: string, data?: any) => {    
    const object = await axios.delete(url, data);    
    const response = await object;
    return response;
}



