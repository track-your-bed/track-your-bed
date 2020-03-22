
import * as React from "react";
import {Dropdown} from 'primereact/dropdown';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';
import { useParams } from "react-router-dom";

import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const divStyle = {
  margin: '50px'
};

const btnStyle = {
  margin: '20px'
};

const fieldStyle = {
  width: '250px'
}


const EditWard: React.FunctionComponent = () => {
  const {hospitalId, departmentId, wardId}=useParams();
  return (
    <div>
     <div style={divStyle}>
      <h1>Station hinzufügen</h1>
        <form>
          <span className="p-float-label">
            <InputText id="in" style={fieldStyle}/>
            <label htmlFor="in">Name der Station</label>
          </span>
          <div>
            <Button label="Hinzufügen" />
            <Button style={btnStyle} label="Abbrechen" />
          </div>
        </form>
    </div>
    </div>
  );
};

export default EditWard;