import * as React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

// Components
import Header from "../Header/Header";

// Pages
import Dashboard from "../../pages/Dashboard/Dashboard";
import Settings from "../../pages/Settings/Settings";
import Login from "../../pages/Login/Login";
import ListView from "../../components/ListView/ListView";

// Styles
import "./App.scss";

const App: React.FunctionComponent = () => (
  <div className="app-container">
    <Router>
      <Header title="Title" />
      <Switch>
        <Route path="/" exact>
          <Login />
        </Route>
        <Route path="/dashboard" exact>
          <Dashboard />
        </Route>
        <Route path="/list" exact>
          <ListView />
        </Route>
        <Route path="/settings" exact>
          <Settings />
        </Route>
      </Switch>
    </Router>
  </div>
);

export default App;
