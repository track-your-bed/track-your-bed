
import * as React from "react";
import {Dropdown} from 'primereact/dropdown';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';

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


const beds = [
    {label: 'Intensivbett', value: 'Intensivbett'},
    {label: 'Normalbett', value: 'Normalbett'}
];

const EditBed: React.FunctionComponent = () => {
  return (
    <div style={divStyle}>
      <h1>Bett hinzufügen</h1>
        <form>
          <Dropdown style={fieldStyle} options={beds}  placeholder="Bettart"/>
          <br/>
          <br/>
          <span className="p-float-label">
            <InputText id="in" style={fieldStyle}/>
            <label htmlFor="in">Bettname</label>
          </span>
          <div>
            <Button label="Hinzufügen" />
            <Button style={btnStyle} label="Abbrechen" />
          </div>
        </form>
    </div>
  );
};

export default EditBed;