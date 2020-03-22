import * as API from "./APIService";

export const postHospital = async (id: string, dto: any)=>{    
    const data = await API.APIPOST(`api/hospitals/${id}`, dto);
    return data;
}

export const getAllHospitals = async ()=>{
    const data = await API.APIGET("api/hospitals");
    return data;
}

export const getHospital = async (id: string)=>{
    const data = await API.APIGET(`api/hospitals/${id}`);
    return data;
}

export const patchHospital = async (id: string, dto: any)=>{    
    const data = await API.APIPATCH(`api/hospitals/${id}`, dto);
    return data;
}

export const deleteHospital = async (id: string, dto?: any)=>{    
    const data = await API.APIDELETE(`api/hospitals/${id}`, dto);
    return data;
}

