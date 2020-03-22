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
    const [beds, setBeds] = React.useState<Bed[]>([]);

    function getAllBeds(data: ListData): Bed[] {
        let beds: Bed[] = [];

        data?.department.forEach(dep => {
            dep?.ward.forEach(ward => {
                if (ward.bed) {
                    beds = beds.concat(ward.bed);
                }
            });
        });

        return beds;
    }

    React.useEffect(() => {
        const beds = getAllBeds(SampleData[0] as ListData);

        setBeds(beds);
    }, []);

    function handleClickTrigger(event: any) {
        console.log(event);
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

    function updateBed(id: string, newData: Bed) {
        updateBedLocal(id, newData);
        updateBedRemote(id, newData);
    }

    function updateBedLocal(id: string, newData: Bed) {
        const newBeds = beds.map(bed => {
            if (bed.id !== id) {
                return bed;
            }

            return newData;
        });

        setBeds(newBeds);
    }

    function updateBedRemote(id: string, newData: Bed) {
        console.log("Ayy, send some request here!");
    }

    function buttonTemplate(rowData: Bed) {
        return (
            <WardActionTemplate
                isOccupied={!isBedFree(rowData)}
                onClick={(newBedState: string) => {
                    const newData = {
                        ...rowData,
                        bed_state: {
                            ...rowData.bed_state,
                            name: newBedState
                        }
                    }
                    updateBed(rowData.id, newData);
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
                    value={beds}
                >
                    <Column header="Betten-Name" field="name"/>
                    <Column header="Betten-Typ" field="bed_type.name"/>
                    <Column field="bed_state" body={buttonTemplate} />
                </DataTable>
            </div>
        </div>
    );
};

export default WardBedManagementTbl;
