import * as React from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

import { useParams, useHistory } from "react-router-dom";
import { BedType } from "../../datatypes/ListView.types";

// TODO: Get these from API
const beds = [
  { id: 1, label: "Standardbett", value: BedType.normal },
  { id: 2, label: "IMC", value: BedType.imc },
  { id: 3, label: "ICU (includiert Beatmung)", value: BedType.icu },
  { id: 4, label: "Covid Standardbett", value: BedType.covid },
  { id: 5, label: "Covid Intensivbett", value: BedType.covidIcu }
];
interface EditBed {
  hospitalId: string;
  departmendId: string;
  wardId: string;
  bedId: string;
}

const EditBed: React.FunctionComponent = () => {
  const [bed, setBed] = React.useState(null);

  const [bedType, setBedType] = React.useState(null);

  const history = useHistory();

  const { hospitalId, departmentId, wardId, bedId } = useParams();

  function handleSubmit(event: any) {
    let json = {
      name: bed,
      departmentTypeId: bedType
    };
    console.log(`JSON: Name: ${json.name} Type ID: ${json.departmentTypeId}`);
  }

  return (
    <div>
      <h1>Bett hinzufügen</h1>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formBedType">
          <Form.Label>Bett Typ</Form.Label>
          <Form.Control as="select">
            {beds.map(bedItem => (
              <option key={bedItem.id} value={bedItem.value}>
                {bedItem.label}
              </option>
            ))}
          </Form.Control>
          <Form.Text className="text-muted">Welchen Bett Typen möchten Sie anlegen?</Form.Text>
        </Form.Group>
        <Form.Group controlId="formBedName">
          <Form.Label>Bett Name</Form.Label>
          <Form.Control as="input" placeholder="Kennzeichnung" />
          <Form.Text className="text-muted">
            Geben Sie die Individuelle Kennzeichnung des Bettes an
          </Form.Text>
        </Form.Group>
        <Form.Row>
          <Button variant="primary" type="submit">
            Hinzufügen
          </Button>
          <Button
            variant="secondary"
            onClick={() => {
              history.goBack();
            }}
          >
            Abbrechen
          </Button>
        </Form.Row>
      </Form>
    </div>
  );
};

export default EditBed;
