

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


const departments = [
  {label: 'Kardiologie', value: 'Kardiologie'},
  {label: 'Urologie', value: 'Urologie'},
  {label: 'Radiologie', value: 'Radiologie'}
];

const EditDepartment: React.FunctionComponent = () => {
  
  const [department, setDepartment] = React.useState(
    null
  )
  const {hospitalId, departmentId}=useParams();
  return (
    <div style={divStyle}>
      <h1>Fachabteilung hinzufügen</h1>
        <form>
          <Dropdown style={fieldStyle} options={departments}  placeholder="Wählen Sie eine Fachabteilung" value={department} onChange={event => setDepartment(event.target.value)}/>
          <br/>
          <br/>
          <span className="p-float-label">
            <InputText id="in" style={fieldStyle}/>
            <label htmlFor="in">Name der Abteilung</label>
          </span>
          <div>
            <Button label="Hinzufügen" />
            <Button style={btnStyle} label="Abbrechen" />
          </div>
        </form>
    </div>
  );
};

export default EditDepartment;