import * as React from "react";
import classNames from "classnames";

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
      const bedsPercentage = Math.round((bedsVacant / bedsMax) * 100);

      setVacant(bedsVacant);
      setMax(bedsMax);
      setPercentage(bedsPercentage);
    }
  }, [stationData]);

  const getColorFromPercentage = (percent: number): string => {
    if (percent > 60) {
      return "#6ab04c";
    }

    if (percent > 40) {
      return "#f9ca24";
    }

    return "#eb4d4b";
  };

  const toggleRow = () => {
    setToggled(!toggled);
  };

  return (
    <>
      <tr
        className={classNames("station-row", {
          "--expanded": toggled
        })}
        onClick={toggleRow}
      >
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
          const bedClass = classNames("station-row__bed", {
            "--occupied": bed.bed_state.name === "belegt",
            "--free": bed.bed_state.name === "frei"
          });

          return (
            <tr key={bed.id} className={bedClass}>
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
