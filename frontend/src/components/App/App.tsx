import * as React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

//components
import Header from "../Header/Header"

// Pages
import Dashboard from "../../pages/Dashboard/Dashboard";
import Settings from "../../pages/Settings/Settings";
import Login from "../../pages/Login/Login";
import HospitalList from "../../pages/HospitalList/HospitalList";
import EditBed from "../../pages/EditBed/EditBed";
import EditWard from "../../pages/EditWard/EditWard"
import EditDepartment from "../../pages/EditDepartment/EditDepartment"

// Styles
import "./App.scss";
import WardBedManagementPage from "../../pages/WardBedManagementPage/WardBedManagementPage";

const App: React.FunctionComponent = () => (
  <div className="app-container">
    <Router>
      <Header title="Title"/>
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
        <Route path="/hospital/:hospitalId/:departmentID/:wardId/:bedId/edit">
          <EditBed/>
        </Route>
        <Route path="/hospital/:hospitalId/:departmendID/:wardId/edit">
          <EditWard/>
        </Route>
        <Route path="/hospital/:hospitalId/:departmentID/edit">
          <EditDepartment/>
        </Route>
        <Route path="/wardBedManagement" exact>
          <WardBedManagementPage hospitalName="Berlin Charité"/>
        </Route>
      </Switch>
    </Router>
  </div>
);

//TODO hospitalName is a fixed string

export default App;
