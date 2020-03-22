import * as React from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";

// Types
import { ListData } from "./ListView.types";

// Components
import StationTable from "../StationTable/StationTable";

// Styles
import "primereact/resources/themes/nova-light/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import "./ListView.scss";

import SampleData from "../SampleData/Data.json";

const ListView: React.FunctionComponent = () => {
  const [data, setData] = React.useState<null | ListData>(null);
  const [expandedRow, setExpandedRow] = React.useState<null | any>(null);

  React.useEffect(() => {
    console.log(SampleData[0]);
    setData(SampleData[0] as ListData);
  }, []);

  const handleRowToggle = (event: any) => {
    setExpandedRow(event.data);
  };

  const rowExpansionTemplate = (data: any) => {
    return <StationTable data={data} />;
  };

  const actionTemplate = (rowData: any, column: any) => {
    const occupation = {
      free: 0,
      occupied: 0
    };

    const total = rowData.ward.reduce(
      (a: any, b: any) => a.bed.length + b.bed.length
    );

    // rowData.ward.map((ward: any) => {
    //   console.log(ward);
    // });

    return (
      <div>
        <p>{total}</p>
      </div>
    );
  };

  return (
    <div className="list-view">
      {data && (
        <div>
          <h1>{data.name}</h1>
          <DataTable
            value={data.department}
            expandedRows={expandedRow}
            onRowToggle={handleRowToggle}
            rowExpansionTemplate={rowExpansionTemplate}
          >
            <Column expander style={{ width: "40px" }} />
            <Column header="Fachabteilung" field="name" />
            <Column header="Freie Betten" body={actionTemplate} />
            <Column header="ICU" field="icu" />
            {/* {data.department.map(department => {
              console.log(department);
              return <Column key={department.id} header="name" field="name" />;
            })} */}
          </DataTable>
        </div>
      )}
    </div>
  );
};

export default ListView;
