import * as React from "react";

// Components
import StationRow from "../StationRow/StationRow";

interface StationTable {
  data: any;
}

const StationTable: React.FunctionComponent<StationTable> = ({
  data
}: StationTable) => {
  return (
    <table className="list-view__station-table">
      <tbody>
        {data.wardCapacities.map((ward: any) => (
          // TODO: Fix key to be Unique ID from DB
          <StationRow key={Math.random() * 1000} stationData={ward} />
        ))}
      </tbody>
    </table>
  );
};

export default StationTable;
