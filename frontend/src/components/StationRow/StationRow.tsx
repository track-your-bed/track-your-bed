import * as React from "react";
import { Link } from "react-router-dom";

// Types
import { Ward } from "../../datatypes/ListView.types";

// Styles
import "./StationRow.scss";

interface CapacityInfo {
  maxCapacity: number;
  freeCapacity: number;
}
interface StationRow {
  stationData: {
    name: string;
    all: CapacityInfo;
    normal: CapacityInfo;
    imc: CapacityInfo;
    icu: CapacityInfo;
    covid: CapacityInfo;
    covidIcu: CapacityInfo;
  };
}

const StationRow: React.FunctionComponent<StationRow> = ({
  stationData
}: StationRow) => {
  console.log(stationData);
  // const [vacant, setVacant] = React.useState(0);
  // const [max, setMax] = React.useState(0);
  const [percentage, setPercentage] = React.useState(100);

  React.useEffect(() => {
    if (stationData) {
      const bedsPercentage = Math.round(
        (stationData.all.freeCapacity / stationData.all.freeCapacity) * 100
      );

      setPercentage(bedsPercentage);
    }
  }, [stationData]);

  const bedTypes = ["all", "normal", "imc", "icu", "covid", "covidIcu"];

  const getColorFromPercentage = (percent: number): string => {
    if (percent >= 25) {
      return "#6ab04c";
    }

    if (percent > 0) {
      return "#f9ca24";
    }

    return "#eb4d4b";
  };

  return (
    <>
      <tr className="station-row">
        <td>
          <div
            className="station-row__status"
            style={{
              backgroundColor: getColorFromPercentage(percentage)
            }}
          />
        </td>
        <td>
          <p className="station-row__title">{stationData.name}</p>
          <Link to={`/wardBedManagement/${stationData.name}`}>Details</Link>
        </td>
        {bedTypes.map((bedType: string) => (
          <td key={Math.random() * 9999}>
            <p className="station-row__vacancy">{`${
              (stationData as any)[bedType].freeCapacity
            } / ${(stationData as any)[bedType].maxCapacity}`}</p>
          </td>
        ))}
      </tr>
    </>
  );
};

export default StationRow;
