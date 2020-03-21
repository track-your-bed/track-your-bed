import * as React from "react";

// Components
import ListView from "../../components/ListView/ListView";

// Styles
import "./HospitalList.scss";

const HospitalList: React.FunctionComponent = () => {
  return (
    <div className="hospital-list">
      <h1>Hospital List View</h1>
      <ListView />
    </div>
  );
};

export default HospitalList;
