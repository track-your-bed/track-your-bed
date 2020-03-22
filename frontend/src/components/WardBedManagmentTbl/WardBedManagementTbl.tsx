import * as React from "react";
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import SampleData from "../SampleData/Data.json";
import "primereact/resources/themes/nova-light/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import {ListData} from "../../datatypes/ListData";
import {Button} from "primereact/button";
import OccupiedButton from "./WardActionTemplate/OccupiedButton/OccupiedButton";
import FreeButton from "./WardActionTemplate/FreeButton/FreeButton";

interface WardBedManagementTbl {
    hospitalName: string
}

const WardBedManagementTbl: React.FunctionComponent<WardBedManagementTbl> = ({
                                                                                 hospitalName,
                                                                             }: WardBedManagementTbl) => {
    const [data, setData] = React.useState<null | ListData>(null);
    const [occupied, setOccupied] = React.useState(null);
    const [selectedBeds, setSelectedBeds] = React.useState(null);

    React.useEffect(() => {
        console.log(SampleData[0]);
        setData(SampleData[0] as ListData);
    }, []);

    function handleClickTrigger(event: any) {
        console.log(event)
    }

    function getButtons(rowData: any, column: any) {
        return <div>
            <Button id="freeBtn" type="button" className="p-button-success" label="Freigegeben"
                    onClick={handleClickTrigger}/>
        </div>
    }

    return (
        <div>
            <div className="content-section introduction">
                <div className="feature-intro">
                    <h1>{hospitalName}</h1>
                </div>
            </div>

            <div className="content-section implementation">
                <DataTable value={data?.department[0].ward[0].bed}
                           selection={selectedBeds} onSelectionChange={e => setSelectedBeds(e.value)}>
                    <Column selectionMode="multiple" style={{width: '3em'}}/>
                    <Column header="Betten-Name" field="name"/>
                    <Column header="Betten-Typ" field="bed_type.name"/>
                    <Column header="Bett-Status" field="bed_state.name"/>
                </DataTable>

                <div style={{marginTop:'10px'}}>
                    <FreeButton />
                </div>
                <div style={{marginTop:'10px'}}>
                   <OccupiedButton onClick={handleClickTrigger}/>
                </div>
            </div>
        </div>
    );
};


export default WardBedManagementTbl;
