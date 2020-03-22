
import * as React from "react";
import {Dropdown} from 'primereact/dropdown';
import {InputText} from 'primereact/inputtext';
import {Button} from 'primereact/button';

import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import { useParams } from "react-router-dom";
import { Bedtypes } from "../../datatypes/enums"

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
    {label: 'Standardbett', value: Bedtypes.normal},
    {label: 'IMC', value: Bedtypes.imc},
    {label: 'ICU (includiert Beatmung)', value: Bedtypes.icu},
    {label: 'Covid Standardbett', value: Bedtypes.covid},
    {label: 'Covid Intensivbett', value: Bedtypes.covidIcu}
];
interface IEditBed {
  hospitalId: string,
  departmendId: string,
  wardId: string
  bedId: string
}

const EditBed: React.FunctionComponent= () => {

  const [bed, setBed] = React.useState(
    null
  )
  const {hospitalId, departmentId, wardId, bedId}=useParams();
  return (
    <div style={divStyle}>
      <h1>Bett hinzufügen</h1>
        <form>
          <Dropdown style={fieldStyle} options={beds}  placeholder="Bettart" value={bed} onChange={event => setBed(event.target.value)}/>
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