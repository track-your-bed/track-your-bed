import * as React from "react";
import axios from 'axios';

const AjaxHelper = async (url: string) => {    
    const object = await axios.get(url);
    const json = await object.data;
    return json;
}

export default AjaxHelper;


