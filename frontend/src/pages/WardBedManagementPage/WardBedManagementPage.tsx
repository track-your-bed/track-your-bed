import * as React from "react";

// Components
import WardBedManagementTbl from "../../components/WardBedManagmentTbl/WardBedManagementTbl";

interface WardBedManagementPage {
  hospitalName: string;
}

const WardBedManagementPage: React.FunctionComponent<WardBedManagementPage> = ({
  hospitalName
}: WardBedManagementPage) => {
  return (
    <div>
      <WardBedManagementTbl hospitalName={hospitalName} />
    </div>
  );
};
export default WardBedManagementPage;
