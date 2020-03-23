import * as React from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Link } from "react-router-dom";

// Components
import { InputText } from "primereact/inputtext";
import StationTable from "../StationTable/StationTable";
import InputField from "../InputField/InputField";

// Types
import { ListData } from "../../datatypes/ListView.types";

// Styles
import "primereact/resources/themes/nova-light/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import "./ListView.scss";

import SampleData from "../SampleData/Data.json";
import CapacityData from "../SampleData/ListView.json";

const ListView: React.FunctionComponent = () => {
  const [data, setData] = React.useState<null | ListData>(null);
  const [listData, setListData] = React.useState<any>(null);
  const [dataFilter, setDataFilter] = React.useState("");
  const [expandedRow, setExpandedRow] = React.useState<null | any>(null);

  React.useEffect(() => {
    setListData(CapacityData);
    setData(SampleData[0] as ListData);
  }, []);

  const handleRowToggle = (event: any) => {
    setExpandedRow(event.data);
  };

  const rowExpansionTemplate = (data: any) => {
    return <StationTable data={data} />;
  };

  const actionTemplate = (rowData: any, column: any) => (
    <div>
      <p>
        {rowData.all.freeCapacity} / {rowData.all.maxCapacity}
      </p>
    </div>
  );

  return (
    <div className="list-view">
      {data && (
        <div>
          <h1>{listData.name}</h1>
          <h2>
            {listData.all.freeCapacity} / {listData.all.maxCapacity}
          </h2>
          <div className="list-view__search">
            <span className="p-float-label">
              <InputText
                id="in"
                value={dataFilter}
                onChange={e => setDataFilter(e.currentTarget.value)}
              />
              <label htmlFor="in">Fachabteilung Suche</label>
            </span>
          </div>
          <div className="list-view__edit">
            <Link to="/hospital/:hospitalId/:departmentId/edit">
              Fachabteilung hinzufügen
            </Link>
          </div>
          <DataTable
            value={listData.departmentCapacities}
            expandedRows={expandedRow}
            onRowToggle={handleRowToggle}
            rowExpansionTemplate={rowExpansionTemplate}
            tableClassName="list-view__table"
            globalFilter={dataFilter}
          >
            <Column expander style={{ width: "50px" }} />
            <Column header="Fachabteilung" field="name" />
            <Column header="Freie Betten" body={actionTemplate} />
            <Column header="Standard" field="normal.maxCapacity" />
            <Column header="IMC" field="imc.maxCapacity" />
            <Column header="ICU" field="icu.maxCapacity" />
            <Column header="Covid Normal" field="covid.maxCapacity" />
            <Column header="Covid Intensiv" field="covidIcu.maxCapacity" />
          </DataTable>
        </div>
      )}
    </div>
  );
};

export default ListView;
