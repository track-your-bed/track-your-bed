import * as React from "react";
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import {Component} from "react";

interface WardBedManagementTbl {
    hospitalName: string;
}

const WardBedManagementTbl: React.FunctionComponent = (
    hospitalName
) => {
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
                        <Column header="" />
                    </DataTable>
                </div>
            </div>
        );
};

export default WardBedManagementTbl;
