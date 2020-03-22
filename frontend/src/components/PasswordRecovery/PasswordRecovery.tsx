import * as React from "react";
import {Button} from "primereact/button";
import {InputText} from "primereact/inputtext";

import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';

import Label from "../Label/Label";
import InputField from "../InputField/InputField";



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
      <InputField
        id="eMailAddress"
        label="E-Mail Adresse"
        onChange={(event: React.FormEvent<HTMLInputElement>): void =>
          setEmailAddress(event.currentTarget.value)
        }
      />
      <div className="buttons">
        <Button
          id="resetRecovery"
          text="Passwort zurücksetzen"
          onClick={handleClick}
        />
      </div>
      <Button id="changeToLogin" text="Abbrechen" onClick={abortFunction} />
    </div>
  );
};

export default PasswordRecovery;
