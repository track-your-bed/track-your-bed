import * as React from "react";
import { DataTable } from "primereact/datatable";
import { useParams } from "react-router-dom";
import { Column } from "primereact/column";
import WardActionTemplate from "./WardActionTemplate/WardActionTemplate";
import {
  fetchWard,
  updateBedStateOnServer
} from "../../Services/WardBedManagementService";

// Types
import { Bed, BedState } from "../../datatypes/ListView.types";

// Styles
import "primereact/resources/themes/nova-light/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import "./WardBedManagementTbl.scss";

const WardBedManagementTbl: React.FunctionComponent = () => {
  const { wardId } = useParams();

  const [wardName, setWardName] = React.useState(null);
  const [beds, setBeds] = React.useState<Bed[]>([]);

  React.useEffect(() => {
    const action = async () => {
      if (!wardId) {
        throw new Error("Expected a ward id");
      }
      const ward = await fetchWard(wardId);

      setWardName(ward.name);
      setBeds(ward.beds);
    };

    action();
  }, []);

  function isBedFree(rowData: Bed): boolean {
    const { bedState } = rowData;

    return bedState === BedState.free;
  }

  function getRowClassName(rowData: Bed): object {
    const occupied = !isBedFree(rowData);

    return {
      free: !occupied,
      occupied
    };
  }

  function updateBedState(id: string, newState: string) {
    // const newBeds = beds.map(bed => {
    //   if (bed.id !== id) {
    //     return bed;
    //   }

    //   const newData = {
    //     ...bed,
    //     bedState: newState
    //   };

    //   return newData;
    // });

    // setBeds(newBeds);

    updateBedStateOnServer(id, newState);
  }

  function buttonTemplate(rowData: Bed) {
    return (
      <WardActionTemplate
        isOccupied={!isBedFree(rowData)}
        onClick={(newBedState: string) => {
          updateBedState(rowData.id, newBedState);
        }}
      />
    );
  }

  return (
    <div>
      <div className="content-section introduction">
        <div className="feature-intro">
          <h1>{wardName}</h1>
        </div>
      </div>

      <div className="content-section implementation">
        <DataTable rowClassName={getRowClassName} value={beds}>
          <Column header="Betten-Name" field="name" />
          <Column header="Betten-Typ" field="bedType" />
          <Column field="bedState" body={buttonTemplate} />
        </DataTable>
      </div>
    </div>
  );
};

export default WardBedManagementTbl;
