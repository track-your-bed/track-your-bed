import * as React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

// Pages
import Dashboard from "../../pages/Dashboard/Dashboard";
import Settings from "../../pages/Settings/Settings";
import Login from "../../pages/Login/Login";

// Styles
import "./App.scss";

const App: React.FunctionComponent = () => (
  <div className="app-container">
    <Router>
      <Switch>
        <Route path="/" exact>
          <Login />
        </Route>
        <Route path="/dashboard" exact>
          <Dashboard />
        </Route>
        <Route path="/settings" exact>
          <Settings />
        </Route>
      </Switch>
    </Router>
  </div>
);

export default App;
