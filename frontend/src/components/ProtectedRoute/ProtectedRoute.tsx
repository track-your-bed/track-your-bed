import * as React from "react";
import { Route, Redirect } from "react-router-dom";

// Context
import { UserContext } from "../../contexts/UserContext";

interface ProtectedRoute {
  children?: React.ReactChild;
  exact?: boolean;
  path: string;
}

const ProtectedRoute: React.FunctionComponent<ProtectedRoute> = ({
  children,
  exact,
  path
}: ProtectedRoute) => {
  return (
    <UserContext.Consumer>
      {user => (
        <Route
          path={path}
          exact={exact}
          render={() =>
            user.isAuthenticated ? children : <Redirect to="/login" />
          }
        />
      )}
    </UserContext.Consumer>
  );
};

export default ProtectedRoute;
