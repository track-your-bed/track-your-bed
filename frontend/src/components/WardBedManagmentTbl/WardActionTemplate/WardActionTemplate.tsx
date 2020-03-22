import React from "react";
import {Button} from "primereact/button";
import FreeButton from "./FreeButton/FreeButton";
import OccupiedButton from "./OccupiedButton/OccupiedButton";

interface WardActionTemplate {
    isOccupied: boolean;
    onClick?: any;
}

function handleClickTrigger(event: any) {
    console.log(event)
}

const WardActionTemplate: React.FunctionComponent<WardActionTemplate> = ({
    isOccupied,
    onClick
}) => {

    const handleClick = (newBedState: string) => () => onClick(newBedState);

    if (isOccupied) {
        return <FreeButton onClick={handleClick('free')} />;
    } else {
        return <OccupiedButton onClick={handleClick('occupied')} />;
    }
};
export default WardActionTemplate;
