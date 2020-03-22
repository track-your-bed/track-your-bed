import * as React from "react";
import {Dialog} from 'primereact/dialog';
import Label from "../../components/Label/Label";
import PasswordRecovery from "../../components/PasswordRecovery/PasswordRecovery";
import LoginForm from "../../components/LoginForm/LoginForm";
import {Button} from "primereact/button";


const UserSettings: React.FunctionComponent = () => {
    const [recoveryMode, setRecoveryMode] = React.useState(false);

    function abort(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        setRecoveryMode(false);
    }

    return (
        <div>
            {recoveryMode ? (
                <PasswordRecovery abortFunction={abort} />
            ) : (
                <Button id="setToRecoveryMode" className="p-button-info" label="API-Token zurücksetzen" onClick={() => setRecoveryMode(true)} />
            )}
        </div>
    );
};

export default UserSettings;
