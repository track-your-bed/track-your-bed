import * as React from "react";
// Components
import LoginForm from "../../components/LoginForm/LoginForm";
import PasswordRecovery from "../../components/PasswordRecovery/PasswordRecovery";

const Login: React.FunctionComponent = () => {
    const [recoveryMode, setRecoveryMode] = React.useState(false);


    return (
        <div>
            <div>
                <LoginForm/>
            </div>
            <div>
                <PasswordRecovery/>
            </div>
        </div>
    );
};

export default Login;
