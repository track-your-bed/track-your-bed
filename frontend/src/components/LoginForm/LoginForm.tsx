import * as React from "react";

// Components
import {Button} from "primereact/button";
import {InputText} from "primereact/inputtext";
import {Dialog} from 'primereact/dialog';
import Label from "../Label/Label";

// Style
import "./LoginForm.scss";
import PasswordRecovery from "../PasswordRecovery/PasswordRecovery";
import AjaxHelper from "../../components/WardBedManagmentTbl/WardBedManagmentService";
import Background from '../LoginForm/hospital-ward.jpg';
import * as API from "../../Services/APIService";

const Login: React.FunctionComponent = () => {
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");

  function login() {
    const text = `{"username": "${username}", "password": "${password}"}`;
    console.log(JSON.parse(text));
  }
  async function getData(){
    const test = await API.APIGET('https://jsonplaceholder.typicode.com/todos/1');
    console.log(test);
      }

  

  return (
    <div className="login_box">
      <p className="login_headline">Login</p>
      <span className="p-float-label" style={{marginBottom: '10px'}}>
                <InputText id="username" value={username} onChange={(e) => setUsername(e.currentTarget.value)}/>
                <Label id="username" label="Benutzername" />
      </span>
      <span className="p-float-label" style={{marginBottom: '10px'}}>
          <InputText id="password" value={password} onChange={(e) => setPassword(e.currentTarget.value)}/>
          <Label id="password" label="Passwort" />
      </span>
      <p></p>
      <p></p>
      <div className="buttons">
        <Button
          id="loginButton"
          className="p-button-info"
          label="Login"
          onClick={(): Promise<void> => getData()}
        />
      </div>
      <br />
    </div>
  );
};

export default Login;
