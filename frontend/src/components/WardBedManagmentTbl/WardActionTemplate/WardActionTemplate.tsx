import React from "react";
import {Button} from "primereact/button";
import FreeButton from "./FreeButton/FreeButton";
import OccupiedButton from "./OccupiedButton/OccupiedButton";

interface WardActionTemplate {
    isOccupied: boolean
}

function handleClickTrigger(event: any) {
    console.log(event)
}

const WardActionTemplate: React.FunctionComponent<WardActionTemplate> = ({
    isOccupied,
}) => {
    return (
        <div>

        </div>
    );
};
export default WardActionTemplate;
