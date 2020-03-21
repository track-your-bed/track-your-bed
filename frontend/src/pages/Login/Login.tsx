import * as React from "react";
// Components
import LoginForm from "../../components/LoginForm/LoginForm";
import PasswordRecovery from "../../components/PasswordRecovery/PasswordRecovery";
import Button from "../../components/Button/Button";

const Login: React.FunctionComponent = () => {
    const [recoveryMode, setRecoveryMode] = React.useState(false);

    function login() {
        setRecoveryMode(true);
    }

    function abort(event: React.MouseEvent<HTMLButtonElement, MouseEvent>) {
        setRecoveryMode(false)
    }

    return (
        <div>
            {recoveryMode ? <PasswordRecovery abortFuntion={abort}/> : <LoginForm />}
            <Button id="resetPasswordButton" text="Login" onClick={(): void =>login()} />
        </div>
    );
};

export default Login;
