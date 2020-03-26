import * as React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

// Pages
import Dashboard from "../../pages/Dashboard/Dashboard";
import UserSettings from "../../pages/UserSettings/UserSettings";
import Login from "../../pages/Login/Login";
import EditHospital from "../../pages/EditHospital/EditHospital";
import EditDepartment from "../../pages/EditDepartment/EditDepartment";
import EditWard from "../../pages/EditWard/EditWard";
import EditBed from "../../pages/EditBed/EditBed";
import WardBedManagementPage from "../../pages/WardBedManagementPage/WardBedManagementPage";

// Components
import Header from "../Header/Header";
import ListView from "../../components/ListView/ListView";
import ProtectedRoute from "../ProtectedRoute/ProtectedRoute";

// Context
import UserContextProvider from "../../contexts/UserContext";

// Styles
import "./App.scss";

const App: React.FunctionComponent = () => {
  return (
    <UserContextProvider>
      <div className="app-container">
        <Router>
          <Header title="Title" />
          <Switch>
            <ProtectedRoute path="/" exact>
              <Dashboard />
            </ProtectedRoute>
            <Route path="/login">
              <Login />
            </Route>
            <ProtectedRoute path="/list" exact>
              <ListView />
            </ProtectedRoute>
            <ProtectedRoute path="/settings" exact>
              <UserSettings />
            </ProtectedRoute>
            <ProtectedRoute path="/wardBedManagement/:wardId">
              <WardBedManagementPage />
            </ProtectedRoute>
            <ProtectedRoute path="/hospital/:hospitalId/:departmentId/:wardId/:bedId/edit">
              <EditBed />
            </ProtectedRoute>
            <ProtectedRoute path="/hospital/:hospitalId/:departmendId/:wardId/edit">
              <EditWard />
            </ProtectedRoute>
            <ProtectedRoute path="/hospital/:hospitalId/:departmentId/edit">
              <EditDepartment />
            </ProtectedRoute>
            <ProtectedRoute path="/hospital/:hospitalId/edit">
              <EditHospital />
            </ProtectedRoute>
          </Switch>
        </Router>
      </div>
    </UserContextProvider>
  );
};

export default App;
