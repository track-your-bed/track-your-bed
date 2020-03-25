import * as React from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useParams, useHistory } from "react-router-dom";
import FormControl, { FormControlProps } from "react-bootstrap/FormControl";

const departmentTypes = [
  //  {label: 'Kardiologie', value: 'Kardiologie'},
  //  {label: 'Urologie', value: 'Urologie'},
  //  {label: 'Radiologie', value: 'Radiologie'}
  {
    label: "Akutgeriatrie/Remobilisation ",
    value: "Akutgeriatrie/Remobilisation"
  },
  { label: "Allergologie", value: "Allergologie" },
  { label: "Anästhesiologie", value: "Anästhesiologie" },
  { label: "Angiologie", value: "Angiologie" },
  { label: "Augenheilkunde", value: "Augenheilkunde" },
  { label: "Chirurgie", value: "Chirurgie" },
  { label: "--Allgemeine Chirurgie", value: "Allgemeine Chirurgie" },
  { label: "--Gefäßchirurgie", value: "Gefäßchirurgie" },
  { label: "--Herzchirurgie", value: "Herzchirurgie" },
  { label: "--Kinderchirurgie", value: "Kinderchirurgie" },
  {
    label: "Mund-, Kiefer- und Gesichtschirurgie",
    value: "Mund-, Kiefer- und Gesichtschirurgie"
  },
  { label: "Orthopädische Chirurgie", value: "Orthopädische Chirurgie" },
  { label: "--Unfallchirurgie", value: "Unfallchirurgie" },
  {
    label: "Plastische, rekonstruktive, ästhetische Chirurgie",
    value: "Plastische, rekonstruktive, ästhetische Chirurgie"
  },
  { label: "Thoraxchirurgie", value: "Thoraxchirurgie" },
  { label: "Viszeralchirurgie", value: "Viszeralchirurgie" },
  { label: "Dermatologie", value: "Dermatologie" },
  { label: "Endokrinologie", value: "Endokrinologie" },
  { label: "Gastroenterologie ", value: "Gastroenterologie" },
  {
    label: "Gynäkologie und Geburtshilfe",
    value: "Gynäkologie und Geburtshilfe"
  },
  {
    label: "Hals-, Nasen- und Ohrenheilkunde",
    value: "Hals-, Nasen- und Ohrenheilkunde"
  },
  { label: "Hämatologie", value: "Hämatologie" },
  { label: "Infektionsepidemiologie", value: "Infektionsepidemiologie" },
  { label: "Intensivmedizin ", value: "Intensivmedizin" },
  { label: "Kardiologie", value: "Kardiologie" },
  {
    label: "Kinder- und Jugendchirurgie",
    value: "Kinder- und Jugendchirurgie"
  },
  {
    label: "Kinder- und Jugendheilkunde",
    value: "Kinder- und Jugendheilkunde"
  },
  {
    label: "Kinder- und Jugendpsychiatrie",
    value: "Kinder- und Jugendpsychiatrie"
  },
  { label: "Nephrologie", value: "Nephrologie" },
  { label: "Neurochirurgie ", value: "Neurochirurgie" },
  { label: "Neurologie", value: "Neurologie" },
  { label: "Notfallambulanz", value: "Notfallambulanz" },
  { label: "Nuklearmedizin ", value: "Nuklearmedizin" },
  { label: "Onkologie", value: "Onkologie" },
  { label: "Orthopädie", value: "Orthopädie" },
  { label: "Palliativmedizin ", value: "Palliativmedizin" },
  { label: "Pneumologie", value: "Pneumologie" },
  { label: "Psychosomatik ", value: "Psychosomatik" },
  { label: "Psychiatrie", value: "Psychiatrie" },
  { label: "Rheumatologie", value: "Rheumatologie" },
  {
    label: "Strahlentherapie-Radioonkologie",
    value: "Strahlentherapie-Radioonkologie"
  },
  { label: "Unfallchirurgie", value: "Unfallchirurgie" },
  { label: "Urologie ", value: "Urologie" },
  { label: "Virologie", value: "Virologie" },
  {
    label: "Zahn-, Mund- und Kieferheilkunde",
    value: "Zahn-, Mund- und Kieferheilkunde"
  }
];

const EditDepartment: React.FunctionComponent = () => {
  const [departmentType, setDepartmentType] = React.useState("");
  const [departmentName, setDepartmentName] = React.useState("");

  const history = useHistory();

  const { hospitalId, departmentId } = useParams();

  function handleSubmit(event: any) {
    const json = {
      name: departmentName,
      departmentTypeId: departmentType,
      hospitalID: hospitalId
    };
    console.log(
      `JSON: Name: ${json.name} Type ID: ${json.departmentTypeId} HospitalID: ${json.hospitalID}`
    );
  }

  return (
    <div>
      <h1>Fachabteilung hinzufügen</h1>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formDapartmentType">
          <Form.Label>Fachabteilung Typ</Form.Label>
          <Form.Control
            as="select"
            onChange={event => setDepartmentType(event.currentTarget.value)}
          >
            {departmentTypes.map(departmentTypeItem => (
              // FIXME:
              // Key parameter needs to be unique! ideally we get a unique ID with the departmenTypes Array
              // which we can reference here to guarante uniqueness. Math.random() etc. shouldnt be used
              // because the Key would change on each rerender thus impacting performance and leading to bugs.
              <option
                key={departmentTypeItem.value}
                value={departmentTypeItem.value}
              >
                {departmentTypeItem.label}
              </option>
            ))}
          </Form.Control>
          <Form.Text className="text-muted">
            Welche Fachabteilung wollen Sie anlegen?
          </Form.Text>
        </Form.Group>
        <Form.Group controlId="formDepartmentName">
          <Form.Label>Name der Fachabteilung</Form.Label>
          <Form.Control
            as="input"
            placeholder="Kennzeichnung"
            onChange={event => setDepartmentName(event.currentTarget.value)}
          />
          <Form.Text className="text-muted">
            Geben Sie die Individuelle Kennzeichnung der Fachabteilung an
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

export default EditDepartment;
