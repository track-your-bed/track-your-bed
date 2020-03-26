import * as React from "react";
import { useParams } from "react-router-dom";
import { Container, Row, Col, Form, Card, Button, Spinner } from "react-bootstrap";

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

      const newDepartmentData = [...hospitalData.departments, response];

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
              <h4 className="text-muted">Stationen editieren</h4>
            </Col>
          </Row>
          <Row>
            <Col>
              {hospitalData.departments.map((department: Department) => (
                <Card key={department.id} className="mb-3">
                  <Card.Body>
                    <Card.Title>{department.name}</Card.Title>
                    <Card.Subtitle className="mb-3 text-muted">
                      Stationen: {department.wards.length || 0}
                    </Card.Subtitle>
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
            </Col>
            <Col>
              <Card>
                <Card.Body>
                  <Card.Title>Neue Station hinzufügen</Card.Title>
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
                    <Button onClick={handleFormSubmit}>Station hinzufügen</Button>
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
