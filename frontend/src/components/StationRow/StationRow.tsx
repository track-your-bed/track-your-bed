import * as React from "react";

// Types
import { Ward, Bed } from "../ListView/ListView.types";

// Styles
import "./StationRow.scss";

interface StationRow {
  stationData: Ward;
}

const StationRow: React.FunctionComponent<StationRow> = ({
  stationData
}: StationRow) => {
  const [vacant, setVacant] = React.useState(0);
  const [max, setMax] = React.useState(0);
  const [percentage, setPercentage] = React.useState(100);
  const [toggled, setToggled] = React.useState(false);

  React.useEffect(() => {
    if (stationData) {
      console.log(stationData);
      const bedsVacant = stationData.bed.filter(bed => {
        return bed.bed_state.name === "frei";
      }).length;

      const bedsMax = stationData.bed.length;
      const bedsPercentage = parseFloat(
        ((bedsVacant / bedsMax) * 100).toFixed(2)
      );

      setVacant(bedsVacant);
      setMax(bedsMax);
      setPercentage(bedsPercentage);
    }
  }, [stationData]);

  const getColorFromPercentage = (
    percent: number,
    start: number,
    end: number
  ): string => {
    let a = percent / 150;
    if (a <= 0.33) {
      a = 0;
    }
    const b = (end - start) * a;
    const c = Math.floor(b + start);

    return `hsl(${c}, 100%, 50%)`;
  };

  const toggleRow = () => {
    setToggled(!toggled);
  };

  return (
    <>
      <tr className="station-row" onClick={toggleRow}>
        <td>
          <div
            className="station-row__status"
            style={{
              backgroundColor: getColorFromPercentage(percentage, 0, 170)
            }}
          />
        </td>
        <td>
          <p className="station-row__title">
            {stationData.name} <span>({stationData.stationtype.name})</span>
          </p>
        </td>
        <td>
          <p className="station-row__vacancy">
            {vacant} / {max}
          </p>
        </td>
        <td>
          <p className="station-row__vacancy">
            {vacant} / {max}
          </p>
        </td>
      </tr>
      {toggled &&
        stationData.bed.map((bed: Bed) => {
          return (
            <tr key={bed.id}>
              <td />
              <td>{bed.name}</td>
              <td>{bed.bed_type.name}</td>
              <td>{bed.bed_state.name}</td>
            </tr>
          );
        })}
    </>
  );
};

export default StationRow;
