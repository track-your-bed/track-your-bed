import React from "react";
import {Button} from "primereact/button";
import FreeButton from "./FreeButton/FreeButton";
import OccupiedButton from "./OccupiedButton/OccupiedButton";

interface WardActionTemplate {
    isOccupied: boolean
}

const WardActionTemplate: React.FunctionComponent<WardActionTemplate> = ({
    isOccupied,
}) => {
    return (
        <div>
            {isOccupied ? <FreeButton /> : <OccupiedButton />}
        </div>
    );
};
export default WardActionTemplate;
