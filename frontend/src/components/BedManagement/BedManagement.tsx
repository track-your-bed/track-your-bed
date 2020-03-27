import * as React from "react";
import { useParams } from "react-router-dom";
import { Table, Button } from "react-bootstrap";
import update from "immutability-helper";
import { getBedsForWard, updateBedState } from "../../Services/BedManagementService";

// Types
import { Ward, Bed, BedState } from "../../datatypes/ListView.types";

// Styles
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

  const updateBedStateHelper = (bedId: string, bedStateIsFree: boolean) => {
    const bedIndex: number = (wardData as Ward).beds.map(bed => bed.id).indexOf(bedId);
    let updatedBedState: any;

    if (bedStateIsFree) {
      updatedBedState = BedState[BedState.occupied];
      updateBedState(bedId, BedState.occupied);
    } else {
      updatedBedState = BedState[BedState.free];
      updateBedState(bedId, BedState.free);
    }

    const newState = update(wardData, {
      beds: {
        [bedIndex]: {
          bedState: {
            $set: updatedBedState
          }
        }
      }
    });

    setWardData(newState);
  };

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
                const bedStateIsFree = (bed.bedState as any) === BedState[BedState.free];

                return (
                  <tr key={bed.id} className={`--${bed.bedState}`}>
                    <td>{bed.name}</td>
                    <td>{bed.bedType}</td>
                    <td>
                      <Button
                        onClick={() => updateBedStateHelper(bed.id, bedStateIsFree)}
                        variant={bedStateIsFree ? "success" : "danger"}
                      >
                        {bedStateIsFree ? "Belegen" : "Freigeben"}
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
