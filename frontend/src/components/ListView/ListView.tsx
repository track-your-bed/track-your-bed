import * as React from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";

// Types
import { ListData } from "./ListView.types";

// Styles
import "primereact/resources/themes/nova-light/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";

import SampleData from "../SampleData/Data.json";

const ListView: React.FunctionComponent = () => {
  const [data, setData] = React.useState<null | ListData>(null);
  const [expandedRow, setExpandedRow] = React.useState<null | any>(null);
  const cols = [{ field: "name", header: "Vin" }];

  React.useEffect(() => {
    console.log(SampleData[0]);
    setData(SampleData[0] as ListData);
  }, []);

  const handleRowToggle = (event: any) => {
    console.log(event);
    setExpandedRow(event.data);
  };

  const rowExpansionTemplate = (data: any) => {
    console.log(data);

    return (
      <div>
        {data.ward.map((ward: any) => (
          <div key={ward.id}>
            <p>{ward.name}</p>
            <p>Betten: {ward.bed.length}</p>
            <hr />
          </div>
        ))}
      </div>
    );
  };

  const actionTemplate = (rowData: any, column: any) => {
    console.log({ rowData, column });
    const occupation = {
      free: 0,
      occupied: 0
    };

    const total = rowData.ward.reduce(
      (a: any, b: any) => a.bed.length + b.bed.length
    );

    console.log(total);

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
            rowGroupMode="rowspan"
            groupField="name"
          >
            <Column expander style={{ width: "50px" }} />
            <Column header="Fachabteilung" field="name" />
            <Column header="Betten" body={actionTemplate} />
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
