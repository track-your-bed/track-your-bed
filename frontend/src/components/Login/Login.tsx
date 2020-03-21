import * as React from "react";

// Components
import InputField from "../InputField/InputField";

// Style
import "./Login.scss";

const Login = (): React.ReactElement => {
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
        onChange={(event: React.FormEvent<HTMLInputElement>): void =>
          setPassword(event.currentTarget.value)
        }
      />
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
