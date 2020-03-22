import React from "react";
import {Button} from "primereact/button";

interface OccupiedButton {
    onClick:any;
}

const OccupiedButton: React.FunctionComponent<OccupiedButton> = ({
  onClick
  }:OccupiedButton) => (
    <div>
        <Button id="setToOccupied" type="button" className="p-button-danger" label="Blockieren" onClick={onClick}/>
    </div>
);
export default OccupiedButton;
