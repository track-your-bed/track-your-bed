import * as React from "react";
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import {Component} from "react";

export class WarnBedManagementTbl extends Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <div className="content-section introduction">
                    <div className="feature-intro">
                        <h1>{Klinik}</h1>
                    </div>
                </div>

                <div className="content-section implementation">
                    <DataTable value={}>
                        <Column header="Betten-ID" />
                        <Column header="Bettentyp" />
                        <Column header="" />
                    </DataTable>
                </div>
            </div>
        )
    }
}
