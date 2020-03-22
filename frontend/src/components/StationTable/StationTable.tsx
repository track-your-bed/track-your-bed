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
      <thead>
        <tr>
          <th>-</th>
          <th>Station</th>
          <th>Freie Betten</th>
          <th>Standard</th>
          <th>IMC</th>
          <th>ICU</th>
          <th>Covid Normal</th>
          <th>Covid Intensiv</th>
        </tr>
      </thead>
      <tbody>
        {data.ward.map((ward: any) => (
          <StationRow key={ward.id} stationData={ward} />
        ))}
      </tbody>
    </table>
  );
};

export default StationTable;
