
import * as React from "react";
import {Dropdown} from 'primereact/dropdown';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';
import { useParams } from "react-router-dom";
import { Wardtypes } from "../../datatypes/enums"

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


const wards = [
  {label: 'Normalstation', value: 'normal'},
  {label: 'IMC', value: 'imc'},
  {label: 'ICU', value: 'icu'},
  {label: 'Covid-Station', value: 'covid'}
];

const EditWard: React.FunctionComponent = () => {
  const [ward, setWard] = React.useState(
    null
  )
  const {hospitalId, departmentId, wardId}=useParams();
  return (
    <div>
     <div style={divStyle}>
      <h1>Station hinzufügen</h1>
        <form>
          <Dropdown style={fieldStyle} options={wards}  placeholder="Wählen Sie einen Stationstyp" value={ward} onChange={event => setWard(event.target.value)}/>
          <br/>
          <br/>
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