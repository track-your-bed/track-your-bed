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
        {data.ward.map((ward: any) => (
          <StationRow key={ward.id} stationData={ward} />
        ))}
      </tbody>
    </table>
  );
};

export default StationTable;
