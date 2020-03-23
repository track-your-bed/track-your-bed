import * as React from "react";

// Components
import { Button } from "primereact/button";
import { InputText } from "primereact/inputtext";
import Label from "../Label/Label";

// Context
import { UserContext } from "../../contexts/UserContext";

// Style
import "./LoginForm.scss";
import PasswordRecovery from "../PasswordRecovery/PasswordRecovery";
import * as API from "../../Services/APIService";

const Login: React.FunctionComponent = () => {
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");

  return (
    <UserContext.Consumer>
      {user => (
        <div className="login-box">
          <p>{user && user.user.name}</p>
          <p className="login-box__headline">Login</p>
          <span className="p-float-label" style={{ marginBottom: "10px" }}>
            <InputText
              id="username"
              value={username}
              onChange={e => setUsername(e.currentTarget.value)}
            />
            <Label id="username" label="Benutzername" />
          </span>
          <span className="p-float-label" style={{ marginBottom: "10px" }}>
            <InputText
              id="password"
              value={password}
              onChange={e => setPassword(e.currentTarget.value)}
            />
            <Label id="password" label="Passwort" />
          </span>
          <div className="buttons">
            <Button
              id="loginButton"
              className="p-button-info"
              label="Login"
              onClick={() => (user as any).login(username)}
            />
          </div>
        </div>
      )}
    </UserContext.Consumer>
  );
};

export default Login;
