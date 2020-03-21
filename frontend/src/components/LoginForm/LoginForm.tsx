import * as React from "react";

// Components
import InputField from "../InputField/InputField";

// Style
import "./LoginForm.scss";

const Login: React.FunctionComponent = () => {
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");

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
      <button type="button">Login</button>
      <p>
        Current Username: <span>{username}</span>
      </p>
      <p>
        Current Password: <span>{password}</span>
      </p>
    </div>
  );
};

export default Login;
