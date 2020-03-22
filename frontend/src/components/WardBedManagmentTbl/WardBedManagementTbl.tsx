import * as React from "react";
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import SampleData from "../SampleData/Data.json";
import "primereact/resources/themes/nova-light/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import {ListData, Bed} from "../../datatypes/ListData";
import "./WardBedManagementTbl.scss";
import WardActionTemplate from "./WardActionTemplate/WardActionTemplate";


interface WardBedManagementTbl {
    hospitalName: string
}

const WardBedManagementTbl: React.FunctionComponent<WardBedManagementTbl> = ({
                                                                                 hospitalName,
                                                                             }: WardBedManagementTbl) => {
    const [data, setData] = React.useState<null | ListData>(null);

    React.useEffect(() => {
        console.log(SampleData[0]);
        setData(SampleData[0] as ListData);
    }, []);

    function handleClickTrigger(event: any) {
        console.log(event)
    }

    function isBedFree(rowData: Bed) {
        const bedState = rowData.bed_state.name;

        switch (bedState) {
            case 'free':
                return true;

            case 'frei':
                return true;

            default:
                return false;
        }
    }

    function getRowClassName(rowData: Bed): object {

        const occupied = !isBedFree(rowData);

        return {
            free: !occupied,
            occupied: occupied
        };
    }

    function buttonTemplate(rowData: Bed) {
        return (
            <WardActionTemplate
            isOccupied={!isBedFree(rowData)}
            onClick={(newBedState: string) => {
                console.log(newBedState);
            }}
            />
        );
    }

    return (
        <div>
            <div className="content-section introduction">
                <div className="feature-intro">
                    <h1>{hospitalName}</h1>
                </div>
            </div>

            <div className="content-section implementation">
                <DataTable
                    rowClassName={getRowClassName}
                    value={data?.department[0].ward[0].bed}
                >
                    <Column header="Betten-Name" field="name"/>
                    <Column header="Betten-Typ" field="bed_type.name"/>
                    <Column field="bed_type.bed_state" body={buttonTemplate} />
                </DataTable>
            </div>
        </div>
    );
};


export default WardBedManagementTbl;
