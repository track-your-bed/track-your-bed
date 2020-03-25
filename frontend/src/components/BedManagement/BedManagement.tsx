import * as React from "react";
import { useParams } from "react-router-dom";
import { Table, Button } from "react-bootstrap";
import {
  getBedsForWard,
  updateBedState
} from "../../Services/BedManagementService";

// Types
import { Ward, Bed, BedState } from "../../datatypes/ListView.types";

// Styles
import "primereact/resources/themes/nova-light/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import "./BedManagement.scss";

const BedManagement: React.FunctionComponent = () => {
  const { wardId } = useParams();
  const [wardData, setWardData] = React.useState<Ward | null>(null);

  React.useEffect(() => {
    (async () => {
      if (!wardId) {
        throw new Error("Expected a ward id");
      }
      const ward = await getBedsForWard(wardId);
      setWardData(ward);
    })();
  }, []);

  function updateBedStateHelper(bedId: string, bedIsFree: boolean) {
    if (bedIsFree) {
      updateBedState(bedId, BedState.occupied);
    } else {
      updateBedState(bedId, BedState.free);
    }
  }

  return (
    <div>
      {wardData && (
        <div className="bed-management">
          <h1>{wardData.name}</h1>

          <Table bordered className="">
            <thead>
              <tr>
                <th>Bettenplatz</th>
                <th>Bettentyp</th>
                <th>Aktion</th>
              </tr>
            </thead>
            <tbody>
              {wardData.beds.map((bed: Bed) => {
                const bedIsFree =
                  (bed.bedState as any) === BedState[BedState.free];

                return (
                  <tr key={bed.id} className={`--${bed.bedState}`}>
                    <td>{bed.name}</td>
                    <td>{bed.bedType}</td>
                    <td>
                      <Button
                        onClick={() => updateBedStateHelper(bed.id, bedIsFree)}
                        variant={bedIsFree ? "success" : "danger"}
                      >
                        {bedIsFree ? "Belegen" : "Freigeben"}
                      </Button>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </Table>
        </div>
      )}
    </div>
  );
};

export default BedManagement;
