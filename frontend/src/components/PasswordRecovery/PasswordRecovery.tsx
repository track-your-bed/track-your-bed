import * as React from "react";
import InputField from "../InputField/InputField";
import Button from "../Button/Button";

interface PasswordRecovery {

}

const PasswordRecovery: React.FunctionComponent<PasswordRecovery> = () => {
    const [emailAddress, setEmailAddress] = React.useState("");

    const alertMessage = `E-Mail Address: ${emailAddress}`;

    function handleClick(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        console.log("Clicked");
        alert(alertMessage);
    }

    return (
        <div>
            <InputField id="eMailAddress" label="E-Mail Adresse" onChange={(event: React.FormEvent<HTMLInputElement>): void =>
                setEmailAddress(event.currentTarget.value)
            }/>
            <Button id="resetRecovery" text="Passwort zurücksetzen" onClick={handleClick}/>
        </div>
    )
};

export default PasswordRecovery;
