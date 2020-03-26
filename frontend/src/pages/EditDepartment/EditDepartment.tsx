import * as React from "react";

import { useParams } from "react-router-dom";
import { Container, Row, Col, Breadcrumb, Form, Button, Card, Alert } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";

// Services
import { getHospital } from "../../Services/HospitalService";
import { getDepartment } from "../../Services/DepartmentService";
import { getWardTypes, addWard, deleteWard } from "../../Services/WardService";

// Types
import { Ward, WardType } from "../../datatypes/ListView.types";

// Components
import CardData from "../../components/CardData/CardData";

const EditDepartment: React.FunctionComponent = () => {
  const [hospitalData, setHospitalData] = React.useState<any | null>(null);
  const [departmentData, setDepartmentData] = React.useState<any | null>(null);
  const [wardTypesList, setWardTypesList] = React.useState<any | null>(null);
  const [wardName, setWardName] = React.useState("");
  const [wardType, setWardType] = React.useState<WardType>();

  const { hospitalId, departmentId } = useParams();

  React.useEffect(() => {
    if (hospitalId && departmentId) {
      (async () => {
        const hospitalResponse = await getHospital(hospitalId);
        const departmentResponse = await getDepartment(departmentId);
        const wardTypesResponse = await getWardTypes();

        setHospitalData(hospitalResponse);
        setDepartmentData(departmentResponse);
        setWardTypesList(wardTypesResponse);
        setWardType(wardTypesResponse[0].name as WardType);
      })();
    }
  }, []);

  const handleFormSubmit = async () => {
    if (wardName && wardName.length > 0 && departmentData.id && wardType) {
      const response = await addWard({
        name: wardName,
        departmentId: departmentData.id,
        beds: [],
        wardType
      });

      const newWardData = [response, ...departmentData.wards];

      setDepartmentData({
        ...departmentData,
        wards: newWardData
      });
    } else {
      console.error("Missing POST-Body Data");
    }
  };

  const handleDelete = async (wardId: string) => {
    const response = await deleteWard(wardId);

    if (response.status === 200) {
      // Remove deleted Department from State-Data on successfull DELETE
      const newWardData = departmentData.wards.filter((ward: Ward) => ward.id !== wardId);

      setDepartmentData({
        ...departmentData,
        wards: newWardData
      });
    }
  };

  return (
    <Container>
      {hospitalData && departmentData && (
        <>
          <Row>
            <Col>
              <Breadcrumb>
                <LinkContainer to={`/hospital/${hospitalId}/edit`}>
                  <Breadcrumb.Item>{hospitalData.name}</Breadcrumb.Item>
                </LinkContainer>
                <Breadcrumb.Item active>{departmentData.name}</Breadcrumb.Item>
              </Breadcrumb>
            </Col>
          </Row>
          <Row className="mb-4">
            <Col>
              <h3>Stationen editieren</h3>
            </Col>
          </Row>
          <Row>
            <Col>
              {departmentData.wards.length > 0 ? (
                departmentData.wards.map((ward: Ward) => (
                  <CardData
                    key={ward.id}
                    title={ward.name}
                    subTitle={`Beds: ${ward.beds ? ward.beds.length : 0}`}
                    mainId={departmentData.id}
                    subId={ward.id}
                    editLink={`/hospital/${hospitalData.id}/${departmentData.id}/${ward.id}/edit`}
                    deleteFunc={() => handleDelete(ward.id)}
                  />
                ))
              ) : (
                <p className="text-muted">Keine Stationen angelegt</p>
              )}
            </Col>
            <Col>
              <Card>
                <Card.Body>
                  <Card.Title>Neue Station hinzufügen</Card.Title>
                  <Form>
                    <Form.Group>
                      <Form.Label>Name</Form.Label>
                      <Form.Control
                        value={wardName}
                        onChange={(e: React.FormEvent<HTMLInputElement>) =>
                          setWardName(e.currentTarget.value)
                        }
                      />
                    </Form.Group>
                    <Form.Group>
                      <Form.Label>Typ</Form.Label>
                      {wardTypesList && (
                        <Form.Control
                          as="select"
                          custom
                          value={wardType}
                          onChange={e => setWardType(e.currentTarget.value as WardType)}
                        >
                          {wardTypesList.map((wardTypeItem: any) => (
                            <option key={Math.random() * 999} value={wardTypeItem.name}>
                              {wardTypeItem.name}
                            </option>
                          ))}
                        </Form.Control>
                      )}
                    </Form.Group>
                    <Form.Group>
                      <Form.Label>
                        Department ID <code>(Dev only)</code>
                      </Form.Label>
                      <Form.Control value={departmentData.id} disabled />
                    </Form.Group>
                    <Button onClick={handleFormSubmit}>Station hinzufügen</Button>
                  </Form>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </>
      )}
    </Container>
  );
};

export default EditDepartment;
