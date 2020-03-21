import * as React from "react";

// Components
import InputField from "../InputField/InputField";

// Style
import "./Login.scss";

const Login = () => {
  const changeHandler = (event: React.FormEvent<HTMLInputElement>): void => {
    console.log(
      `Change ${event.currentTarget.id} to ${event.currentTarget.value}`
    );
  };

  return (
    <div>
      <p>Login</p>
      <InputField id="username" label="Benutzername" onChange={changeHandler} />
      <InputField id="password" label="Passwort" onChange={changeHandler} />
    </div>
  );
};

export default Login;
