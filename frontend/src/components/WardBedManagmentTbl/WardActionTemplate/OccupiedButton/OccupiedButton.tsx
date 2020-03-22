import React from "react";
import {Button} from "primereact/button";


const OccupiedButton: React.FunctionComponent = () => (
    <div>
        <Button id="setToOccupied" type="button" className="p-button-danger" label="Blockieren"/>
    </div>
);
export default OccupiedButton;
