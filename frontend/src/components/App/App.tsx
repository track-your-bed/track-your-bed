import * as React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

// Components
import Header from "../Header/Header";

// Pages
import Dashboard from "../../pages/Dashboard/Dashboard";
import UserSettings from "../../pages/UserSettings/UserSettings";
import Login from "../../pages/Login/Login";
import EditBed from "../../pages/EditBed/EditBed";
import EditWard from "../../pages/EditWard/EditWard";
import EditDepartment from "../../pages/EditDepartment/EditDepartment";
import ListView from "../../components/ListView/ListView";

// Styles
import "./App.scss";
import WardBedManagementPage from "../../pages/WardBedManagementPage/WardBedManagementPage";

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
          <UserSettings />
        </Route>
        <Route path="/hospital/:hospitalId/:departmentId/:wardId/:bedId/edit">
          <EditBed />
        </Route>
        <Route path="/hospital/:hospitalId/:departmendId/:wardId/edit">
          <EditWard />
        </Route>
        <Route path="/hospital/:hospitalId/:departmentId/edit">
          <EditDepartment />
        </Route>
        <Route path="/wardBedManagement" exact>
          <WardBedManagementPage hospitalName="Berlin Charité" />
        </Route>
      </Switch>
    </Router>
  </div>
);

export default App;
