import * as React from "react";
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {Button} from "primereact/button";
import WardActionTemplate from "./WardActionTemplate/WardActionTemplate";
import SampleData from "../SampleData/Data.json";
import "primereact/resources/themes/nova-light/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import {ListData} from "../../datatypes/ListData";

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

    return (
        <div>
            <div className="content-section introduction">
                <div className="feature-intro">
                    <h1>{hospitalName}</h1>
                </div>
            </div>

            <div className="content-section implementation">
                <DataTable>
                    <Column header="Betten-ID" />
                    <Column header="Bettentyp" />
                    <Column header="Actions" body={<WardActionTemplate isOccupied={true} />} />
                </DataTable>
            </div>
        </div>
    );
};



export default WardBedManagementTbl;
