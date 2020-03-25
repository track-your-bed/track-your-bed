import * as React from "react";

// Components
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

// Context
import { UserContext } from "../../contexts/UserContext";

// Style
import "./LoginForm.scss";

const Login: React.FunctionComponent = () => {
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");

  return (
    <UserContext.Consumer>
      {user => (
        <div className="login-box">
          <p className="login-box__headline">Login</p>
          <Form onSubmit={() => (user as any).login(username)}>
            <Form.Group controlId="formRecoveryEmail">
              <Form.Label srOnly>Benutzername</Form.Label>
              <Form.Control
                as="input"
                value={username}
                onChange={e => setUsername(e.currentTarget.value)}
                placeholder="Benutzername"
              />
            </Form.Group>
            <Form.Group controlId="formRecoveryEmail">
              <Form.Label srOnly>Passwort</Form.Label>
              <Form.Control
                as="input"
                type="password"
                value={password}
                onChange={e => setPassword(e.currentTarget.value)}
                placeholder="Passwort"
              />
            </Form.Group>
            <Form.Row>
              <Button variant="primary" type="submit">
                Login
              </Button>
              <Button variant="secondary">Reset Password</Button>
            </Form.Row>
          </Form>
        </div>
      )}
    </UserContext.Consumer>
  );
};

export default Login;
