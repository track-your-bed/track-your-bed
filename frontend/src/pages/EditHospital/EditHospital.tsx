import * as React from "react";
import { useParams } from "react-router-dom";
import { Container, Row, Col, Form, Card, Button } from "react-bootstrap";

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
  const [departmentName, setDepartmentName] = React.useState<any | string>(null);
  const [departmentType, setDepartmentType] = React.useState<undefined | string>(undefined);
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
    const response = await addDepartment({
      name: departmentName,
      hospitalId: hospitalData.id,
      departmentType
    });

    console.log(response);
  };

  const handleDelete = async (departmentId: string) => {
    console.log(`delete ${departmentId}`);
    const response = await deleteDepartment(departmentId);
    console.log(response);
  };

  return (
    <Container>
      <Row>
        <Col>
          {hospitalData && (
            <div>
              <h3>{hospitalData.name} editieren</h3>
              {hospitalData.departments.map((department: Department) => (
                <Card key={department.id} className="my-3">
                  <Card.Body>
                    <Card.Title>{department.name}</Card.Title>
                    <Card.Link
                      as={Button}
                      variant="light"
                      onClick={() => handleDelete(department.id as string)}
                    >
                      Remove
                    </Card.Link>
                  </Card.Body>
                </Card>
              ))}
              <hr className="my-4" />
              <Card>
                <Card.Body>
                  <Card.Title>Neue Station hinzufügen</Card.Title>
                  <Form>
                    <Form.Group>
                      <Form.Label>Name</Form.Label>
                      <Form.Control
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
                          {departmentTypesList.map((departmentType: any) => (
                            <option key={Math.random() * 999}>{departmentType.name}</option>
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
                    <Button onClick={handleFormSubmit}>Station hinzufügen</Button>
                  </Form>
                </Card.Body>
              </Card>
            </div>
          )}
        </Col>
      </Row>
    </Container>
  );
};

export default EditHospital;
