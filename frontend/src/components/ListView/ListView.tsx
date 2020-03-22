import * as React from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";

// Types
import { ListData } from "./ListView.types";

// Components
import StationTable from "../StationTable/StationTable";
import InputField from "../InputField/InputField";

// Styles
import "primereact/resources/themes/nova-light/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import "./ListView.scss";

import SampleData from "../SampleData/Data.json";

const ListView: React.FunctionComponent = () => {
  const [data, setData] = React.useState<null | ListData>(null);
  const [dataFilter, setDataFilter] = React.useState("");
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
        <p>{total} / 20</p>
      </div>
    );
  };

  return (
    <div className="list-view">
      {data && (
        <div>
          <h1>{data.name}</h1>
          <div className="list-view__search">
            <InputField
              id="search"
              label="Abteilungssuche"
              onChange={(event: any) =>
                setDataFilter(event.currentTarget.value)
              }
            />
          </div>
          <DataTable
            value={data.department}
            expandedRows={expandedRow}
            onRowToggle={handleRowToggle}
            rowExpansionTemplate={rowExpansionTemplate}
            tableClassName="list-view__table"
            globalFilter={dataFilter}
          >
            <Column expander style={{ width: "50px" }} />
            <Column header="Fachabteilung" field="name" />
            <Column header="Freie Betten" body={actionTemplate} />
            <Column header="Standard" field="icu" />
            <Column header="IMC" field="icu" />
            <Column header="ICU" field="icu" />
            <Column header="Covid Normal" field="icu" />
            <Column header="Covid Intensiv" field="icu" />
          </DataTable>
        </div>
      )}
    </div>
  );
};

export default ListView;
