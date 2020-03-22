import * as React from "react"
import WardBedManagementTbl from "../../components/WardBedManagmentTbl/WardBedManagementTbl";

interface WardBedManagementPage {
    hospitalName: string
}

const WardBedManagementPage: React.FunctionComponent<WardBedManagementPage> = ({
hospitalName,}
) => {
    return (
        <div>
            <WardBedManagementTbl hospitalName={hospitalName}/>
        </div>
    )
};
export default WardBedManagementPage;
