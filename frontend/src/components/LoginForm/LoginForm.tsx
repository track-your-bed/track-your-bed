import * as React from "react";

// Components
import InputField from "../InputField/InputField";
import Button from "../Button/Button";

// Style
import "./LoginForm.scss";
import PasswordRecovery from "../PasswordRecovery/PasswordRecovery";

import AjaxHelper from "../../components/WardBedManagmentTbl/WardBedManagmentService";

const Login: React.FunctionComponent = () => {
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");

  function login() {
    const text = `{"username": "${username}", "password": "${password}"}`;
    console.log(JSON.parse(text));
  }
  async function getData(){
    const test = await AjaxHelper('https://jsonplaceholder.typicode.com/todos/1');    
    console.log(test);
      }

  return (
    <div>
      <p>Login</p>
      <InputField
        id="username"
        label="Benutzername"
        onChange={(event: React.FormEvent<HTMLInputElement>): void =>
          setUsername(event.currentTarget.value)
        }
      />
      <InputField
        id="password"
        label="Passwort"
        type="password"
        onChange={(event: React.FormEvent<HTMLInputElement>): void =>
          setPassword(event.currentTarget.value)
        }
      />
      <div className="buttons">
        <Button
          id="loginButton"
          className="button"
          text="Login"
          onClick={(): Promise<void> => getData()}
        />
      </div>
      <br />
    </div>
  );
};

export default Login;