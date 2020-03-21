import * as React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

//components
import Header from "../Header/Header"

// Pages
import Dashboard from "../../pages/Dashboard/Dashboard";
import Settings from "../../pages/Settings/Settings";
import Login from "../../pages/Login/Login";
import HospitalList from "../../pages/HospitalList/HospitalList";

// Styles
import "./App.scss";

const App: React.FunctionComponent = () => (
  <div className="app-container">
    <Router>
      <Header title="Title"/>{//Has to done}
      <Switch>
        <Route path="/" exact>
          <Login />
        </Route>
        <Route path="/dashboard" exact>
          <Dashboard />
        </Route>
        <Route path="/list" exact>
          <HospitalList />
        </Route>
        <Route path="/settings" exact>
          <Settings />
        </Route>
      </Switch>
    </Router>
  </div>
);

export default App;
