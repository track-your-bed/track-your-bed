import * as React from "react";
import classNames from "classnames";
import { Container, Row, Col } from "react-bootstrap";
import { getHospitalCapacity } from "../../Services/HospitalService";

// Components
import CaretRight from "../../icons/CaretRight";
import ListFilter from "../ListFilter/ListFilter";
import Table from "../Table/Table";

// Types
import { ListData } from "../../datatypes/ListView.types";

// Styles
import "./ListView.scss";

const TEMP_HOSPITAL_ID = "35ad5a65-f00a-4eb6-8302-3f170863b15c";

const ListView: React.FunctionComponent = () => {
  const [listData, setListData] = React.useState<any | null>(null);
  const [expandedRows, setExpandedRows] = React.useState<string[]>([]);
  const [dataFilter, setDataFilter] = React.useState("");

  React.useEffect(() => {
    (async () => {
      const data = await getHospitalCapacity(TEMP_HOSPITAL_ID);
      console.log(data);
      setListData(data);
    })();
  }, []);

  /**
   * TODO: Get Header Items from DB to allow variability
   */
  interface Headers {
    id: string;
    label: string;
    value: string;
  }
  const headers = [
    { id: "header-all", label: "Freie Betten", value: "all" },
    { id: "header-normal", label: "Standard", value: "normal" },
    { id: "header-imc", label: "IMC", value: "imc" },
    { id: "header-icu", label: "ICU", value: "icu" },
    { id: "header-covid", label: "Covid Normal", value: "covid" },
    { id: "header-covidicu", label: "Covid Intensiv", value: "covidIcu" }
  ];

  const tempDepartments = [
    { id: "dep1", value: "DepartmentAX", label: "DepartmentAX" },
    { id: "dep2", value: "Department23", label: "Department23" },
    { id: "dep3", value: "test-dep-1", label: "test-dep-1" }
  ];

  const toggleRow = (id: string) => {
    if (expandedRows.includes(id)) {
      setExpandedRows(expandedRows.filter(rowId => rowId !== id));
    } else {
      setExpandedRows(prevState => [...prevState, id]);
    }
  };

  return (
    <Container fluid>
      {listData && (
        <>
          <Row>
            <Col>
              <h1>{listData.name}</h1>
            </Col>
          </Row>
          <Row>
            <Col className="list-view">
              <Table rawData={listData.departmentCapacities} />
            </Col>
          </Row>
        </>
      )}
    </Container>
  );
};

export default ListView;
