import * as React from "react";
import InputField from "../InputField/InputField";
import Button from "../Button/Button";

interface PasswordRecovery {
    abort: ((event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void)
}

const PasswordRecovery: React.FunctionComponent<PasswordRecovery> = (abort,) => {
    const [emailAddress, setEmailAddress] = React.useState("");

    const alertMessage = `E-Mail Address: ${emailAddress}`;

    function handleClick(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        console.log("Clicked");
        alert(alertMessage);
    }

    return (
        <div>
            <InputField id="eMailAddress" label="E-Mail Adresse"
                        onChange={(event: React.FormEvent<HTMLInputElement>): void =>
                            setEmailAddress(event.currentTarget.value)
                        }/>
            <div className="buttons">
                <Button id="resetRecovery" text="Passwort zurücksetzen" onClick={handleClick}/>
            </div>
            <Button id="changeToLogin" text="Abbrechen" onClick={(event:React.MouseEvent) => abort(event)}/>
        </div>
    )
};

export default PasswordRecovery;
