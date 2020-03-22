import * as React from "react";
import {Button} from "primereact/button";
import {InputText} from "primereact/inputtext";
import Label from "../Label/Label";
interface PasswordRecovery {
    abortFunction: (
        event: React.MouseEvent<HTMLButtonElement, MouseEvent>
    ) => void;
}

const PasswordRecovery: React.FunctionComponent<PasswordRecovery> = ({
                                                                         abortFunction
                                                                     }: PasswordRecovery) => {
    const [emailAddress, setEmailAddress] = React.useState("");

    const alertMessage = `E-Mail Address: ${emailAddress}`;

    function handleClick(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        console.log("Clicked");

        alert(alertMessage);
    }

    return (
        <div>
            <span className="p-float-label" style={{marginBottom: '10px'}}>
                <InputText id="in" value={emailAddress} onChange={(e) => setEmailAddress(e.currentTarget.value)}/>
                <Label id="in" label="E-Mail Adresse" />
            </span>

            <div className="buttons">
                <Button
                    id="resetRecovery"
                    label="Passwort zurücksetzen"
                    className="p-button-warning button"
                    onClick={handleClick}
                    style={{marginRight:'10px'}}
                />
                <Button id="changeToLogin" label="Abbrechen" className="button p-button-secondary" onClick={abortFunction} />
            </div>
        </div>
    );
};

export default PasswordRecovery;
