import * as React from "react";
import { useParams, Link } from "react-router-dom";
import { LinkContainer } from "react-router-bootstrap";
import { Container, Row, Col, Form, Card, Button, Spinner } from "react-bootstrap";

// Components
import CardData from "../../components/CardData/CardData";

// Services
import { getHospital } from "../../Services/HospitalService";
import {
  getDepartmentTypes,
  addDepartment,
  deleteDepartment
} from "../../Services/DepartmentService";

// Types
import { Department } from "../../datatypes/ListView.types";

const EditHospital: React.FC = () => {
  const { hospitalId } = useParams();
  const [departmentName, setDepartmentName] = React.useState("");
  const [departmentType, setDepartmentType] = React.useState("");
  const [hospitalData, setHospitalData] = React.useState<any | null>(null);
  const [departmentTypesList, setDepartmentTypesList] = React.useState<any | null>(null);

  React.useEffect(() => {
    if (hospitalId) {
      (async () => {
        const hospitalResponse = await getHospital(hospitalId);
        const departmentResponse = await getDepartmentTypes();

        setHospitalData(hospitalResponse);
        setDepartmentTypesList(departmentResponse);
        setDepartmentType(departmentResponse[0].name);
      })();
    }
  }, []);

  const handleFormSubmit = async () => {
    if (departmentName && departmentName.length > 0 && hospitalData.id && departmentType) {
      const response = await addDepartment({
        name: departmentName,
        hospitalId: hospitalData.id,
        wards: [],
        departmentType
      });

      const newDepartmentData = [response, ...hospitalData.departments];

      setHospitalData({
        ...hospitalData,
        departments: newDepartmentData
      });
      setDepartmentName("");
    } else {
      console.error("Missing POST-Body Data");
    }
  };

  const handleDelete = async (departmentId: string) => {
    const response = await deleteDepartment(departmentId);

    if (response.status === 200) {
      // Remove deleted Department from State-Data on successfull DELETE
      const newDepartmentData = hospitalData.departments.filter(
        (dep: Department) => dep.id !== departmentId
      );

      setHospitalData({
        ...hospitalData,
        departments: newDepartmentData
      });
    }
  };

  return (
    <Container>
      {hospitalData ? (
        <>
          <Row className="mb-4">
            <Col>
              <h3>{hospitalData.name}</h3>
              <h4 className="text-muted">Fachbereiche editieren</h4>
            </Col>
          </Row>
          <Row>
            <Col>
              {hospitalData.departments.length > 0 ? (
                hospitalData.departments.map((department: Department) => (
                  <CardData
                    key={department.id}
                    title={department.name}
                    editLink={`/hospital/${hospitalData.id}/${department.id}/edit`}
                    subTitle={`Stationen: ${department.wards ? department.wards.length : 0}`}
                    deleteFunc={handleDelete}
                    mainId={hospitalData.id}
                    subId={department.id}
                  />
                ))
              ) : (
                <p className="text-muted">Keine Fachabteilungen angelegt</p>
              )}
            </Col>
            <Col>
              <Card>
                <Card.Body>
                  <Card.Title>Neuen Fachbereich hinzufügen</Card.Title>
                  <Form>
                    <Form.Group>
                      <Form.Label>Name</Form.Label>
                      <Form.Control
                        value={departmentName}
                        onChange={(e: React.FormEvent<HTMLInputElement>) =>
                          setDepartmentName(e.currentTarget.value)
                        }
                      />
                    </Form.Group>
                    <Form.Group>
                      <Form.Label>Typ</Form.Label>
                      {departmentTypesList && (
                        <Form.Control
                          as="select"
                          custom
                          value={departmentType}
                          onChange={e => setDepartmentType(e.currentTarget.value)}
                        >
                          {departmentTypesList.map((departmentTypeItem: any) => (
                            <option key={Math.random() * 999}>{departmentTypeItem.name}</option>
                          ))}
                        </Form.Control>
                      )}
                    </Form.Group>
                    <Form.Group>
                      <Form.Label>
                        Hospital ID <code>(Dev only)</code>
                      </Form.Label>
                      <Form.Control value={hospitalData.id} disabled />
                    </Form.Group>
                    <Button onClick={handleFormSubmit}>Fachbereich hinzufügen</Button>
                  </Form>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </>
      ) : (
        <Spinner animation="border" />
      )}
    </Container>
  );
};

export default EditHospital;
