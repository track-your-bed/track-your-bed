import * as React from "react";

// Components
import LoginForm from "../../components/LoginForm/LoginForm";
import PasswordRecovery from "../../components/PasswordRecovery/PasswordRecovery";
import Button from "../../components/Button/Button";

// COntext
import { UserContext } from "../../contexts/UserContext";

// Styles
import "./Login.scss";

const Login: React.FunctionComponent = () => (
  <UserContext.Consumer>
    {user => (
      <div className="BackgroundImg">
        <LoginForm />
      </div>
    )}
  </UserContext.Consumer>
);

export default Login;
