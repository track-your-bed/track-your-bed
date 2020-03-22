import * as React from "react";
import axios from 'axios';
import { ListData, Department } from "../../datatypes/ListData";
import WardBedManagementTbl from "../WardBedManagmentTbl/WardBedManagementTbl";


export class DataItem{
    free: number = 0;
    max: number = 0;    
}
export class DepartmentLight{    
    all: DataItem = new DataItem();
    normal: DataItem = new DataItem();
    imc: DataItem = new DataItem();
    icu: DataItem = new DataItem();
    covid: DataItem = new DataItem();
    covidicu: DataItem = new DataItem();  
}
export class WardLight{
    id: string;
    all: DataItem = new DataItem();
    normal: DataItem = new DataItem();
    imc: DataItem = new DataItem();
    icu: DataItem = new DataItem();
    covid: DataItem = new DataItem();
    covidicu: DataItem = new DataItem();
}

export class MasterData{
    department: DepartmentLight;
    ward: WardLight;
}


class Helper{
    
    getAllBedDatainHospital = (Hospital: ListData) =>{
        var result: MasterData[] = [];
        Hospital.department.forEach(Fachabteilung => {
            let datalist = new MasterData();
            let departData = new DepartmentLight();       
            Fachabteilung.ward.forEach(ward => {
                let wardData = new WardLight();
                wardData.id = ward.id; //oder name klärn
                ward.bed.forEach(bed => {
                    departData.all.max++;
                    wardData.all.max++;
                    if(bed.bed_state == "free")
                    {
                        departData.all.free++;
                        wardData.all.free++;
                    }        
                    switch(bed.bed_type){
                        case "normal":
                            {
                                departData.normal.max++;
                                if(bed.bed_state == "free")
                                {
                                    departData.normal.free++;
                                }
                                break;
                            }
                            case "imc":
                            {
                                departData.imc.max++;
                                if(bed.bed_state == "free")
                                {
                                    departData.imc.free++;
                                }
                                break;
                            }
                            case "icu":
                            {
                                departData.icu.max++;
                                if(bed.bed_state == "free")
                                {
                                    departData.icu.free++;
                                }
                                break;
                            }
                            case "covid":
                            {
                                departData.covid.max++;
                                if(bed.bed_state == "free")
                                {
                                    departData.covid.free++;
                                }
                                break;
                            }
                            case "covid-icu":
                            {
                                departData.covidicu.max++;
                                if(bed.bed_state == "free")
                                {
                                    departData.covidicu.free++;
                                }
                                break;
                            }                                                        
                    }
                });
                datalist.ward = wardData;
            });
           datalist.department = departData;
           result.push(datalist);
        });        
        
        return result;
    }    

}

}


