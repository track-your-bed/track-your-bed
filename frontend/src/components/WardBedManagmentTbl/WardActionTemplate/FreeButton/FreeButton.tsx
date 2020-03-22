import React from "react";
import {Button} from "primereact/button";

interface FreeButton {
    onClick?:any;
}

const FreeButton: React.FunctionComponent<FreeButton> = ({ onClick }) => (
    <div>
        <Button
            id="setToFree"
            type="button"
            className="p-button-success p-button-rounded"
            label="Freigegeben"
            onClick={onClick}
        />
    </div>
);
export default FreeButton;
