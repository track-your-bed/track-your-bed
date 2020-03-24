import * as React from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useParams, useHistory } from "react-router-dom";
import { Wardtypes } from "../../datatypes/enums";

const wards = [
  { label: "Normalstation", value: Wardtypes.normal },
  { label: "IMC", value: Wardtypes.imc },
  { label: "ICU", value: Wardtypes.icu },
  { label: "Covid-Station", value: Wardtypes.covid }
];

const EditWard: React.FunctionComponent = () => {
  const [ward, setWard] = React.useState("");

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
      <h1>Station hinzufügen</h1>
      <Form onSubmit={onSubmitForm}>
        <Form.Group controlId="formWardType">
          <Form.Label>Stations Typ</Form.Label>
          <Form.Control
            as="select"
            onChange={event => setWard(event.currentTarget.value)}
          >
            {wards.map(wardItem => (
              <option key={`wardItem-${wardItem.value}`} value={wardItem.value}>
                {wardItem.label}
              </option>
            ))}
          </Form.Control>
          <Form.Text className="text-muted">
            Welche Fachabteilung wollen Sie anlegen?
          </Form.Text>
        </Form.Group>
        <Form.Group controlId="formWardName">
          <Form.Label>Name der Station</Form.Label>
          <Form.Control
            as="input"
            placeholder="Kennzeichnung"
            onChange={event => setWardName(event.currentTarget.value)}
          />
          <Form.Text className="text-muted">
            Geben Sie die Individuelle Kennzeichnung der Station an
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

export default EditWard;
