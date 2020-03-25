import * as React from "react";
import { Container, Row, Col, Form, Button } from "react-bootstrap";

// Services
import { getHospital } from "../../Services/HospitalService";

// Context
import { UserContext } from "../../contexts/UserContext";

const EditDepartment: React.FunctionComponent = () => {
  const [departments, setDepartments] = React.useState([]);
  const [userDepartment, setUserDepartment] = React.useState("");
  const auth = React.useContext(UserContext);

  React.useEffect(() => {
    (async () => {
      const data = await getHospital(auth.hospitalId);
      setDepartments(data.departments);
    })();
  }, []);

  const updateUser = () => {
    /**
     * TODO: Add POST to UpdateUser once Usermanagement is available in Backend
     */
    console.log(`New User Department: ${userDepartment}`);
  };

  return (
    <Container>
      <Row>
        <Col>
          <h1>Mein Profil</h1>
          <Form className="mt-4">
            <Form.Group controlId="userDepartment" className="w-25">
              <Form.Label>Station auswählen</Form.Label>
              {departments && (
                <Form.Control
                  as="select"
                  custom
                  onChange={e => setUserDepartment(e.currentTarget.value)}
                >
                  {departments.map((department: any) => (
                    <option key={department.id} value={department.id}>
                      {department.name}
                    </option>
                  ))}
                </Form.Control>
              )}
            </Form.Group>
            <Button onClick={updateUser}>Speichern</Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default EditDepartment;
