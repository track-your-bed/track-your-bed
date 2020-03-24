import * as React from "react";
import { Dropdown } from "primereact/dropdown";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useParams, useHistory } from "react-router-dom";
import { Wardtypes } from "../../datatypes/enums.types";
import {
  fetchWard,
  updateBedStateOnServer
} from "../../Services/WardBedManagementService";
import "primereact/resources/themes/nova-light/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";

const divStyle = {
  margin: "50px"
};

const btnStyle = {
  margin: "20px"
};

const fieldStyle = {
  width: "250px"
};

const wards = [
  { label: "Normalstation", value: Wardtypes.normal },
  { label: "IMC", value: Wardtypes.imc },
  { label: "ICU", value: Wardtypes.icu },
  { label: "Covid-Station", value: Wardtypes.covid }
];
let wardName: string;

const EditWard: React.FunctionComponent = () => {
  const [ward, setWard] = React.useState(null);

  const [wardName, setWardName] = React.useState("");

  function onSubmitForm() {
    const json = {
      name: wardName,
      type: ward
    };
  }

  const history = useHistory();

  const { hospitalId, departmentId, wardId } = useParams();
  return (
    <div>
      <div style={divStyle}>
        <h1>Station hinzufügen</h1>
        <form onSubmit={onSubmitForm}>
          <Dropdown
            style={fieldStyle}
            options={wards}
            placeholder="Wählen Sie einen Stationstyp"
            value={ward}
            onChange={event => setWard(event.target.value)}
          />
          <br />
          <br />
          <span className="p-float-label">
            <InputText
              id="in"
              style={fieldStyle}
              name="wardName"
              value={wardName}
              onChange={event => setWardName(event.currentTarget.value)}
            />
            <label htmlFor="in">Name der Station</label>
          </span>
          <div>
            <Button label="Hinzufügen" type="submit" />
            <Button
              style={btnStyle}
              label="Abbrechen"
              onClick={() => {
                history.goBack();
              }}
            />
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditWard;
