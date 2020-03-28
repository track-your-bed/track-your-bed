import * as React from "react";
import classNames from "classnames";
import {
  Table,
  Container,
  Row,
  ButtonGroup,
  Dropdown,
  DropdownButton
} from "react-bootstrap";
import { getHospitalCapacity } from "../../Services/HospitalService";

// Components
import CaretRight from "../../icons/CaretRight";

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

  const toggleRow = (id: string) => {
    if (expandedRows.includes(id)) {
      setExpandedRows(expandedRows.filter(rowId => rowId !== id));
    } else {
      setExpandedRows(prevState => [...prevState, id]);
    }
  };

  return (
    <Container fluid>
      <Row>
        <div className="list-view">
          {listData && (
            <div>
              <h1>{listData.name}</h1>
              <h2>
                {listData.all.freeCapacity} / {listData.all.maxCapacity}
              </h2>
              <DropdownButton
                as={ButtonGroup}
                id="drop"
                title="Fachabteilungen"
                className="my-3 mx-2"
              >
                <Dropdown.Item>Fachabteilung 1</Dropdown.Item>
                <Dropdown.Item>Fachabteilung 2</Dropdown.Item>
                <Dropdown.Item>Fachabteilung 3</Dropdown.Item>
                <Dropdown.Item>Fachabteilung 4</Dropdown.Item>
                <Dropdown.Item>Fachabteilung 5</Dropdown.Item>
              </DropdownButton>
              <DropdownButton
                as={ButtonGroup}
                id="drop2"
                title="Stationen"
                className="my-3 mx-2"
              >
                <Dropdown.Item>Station 1</Dropdown.Item>
                <Dropdown.Item>Station 2</Dropdown.Item>
                <Dropdown.Item>Station 3</Dropdown.Item>
                <Dropdown.Item>Station 4</Dropdown.Item>
                <Dropdown.Item>Station 5</Dropdown.Item>
              </DropdownButton>
              <Table className="list-view__table" hover>
                <thead>
                  <tr>
                    <th>-</th>
                    <th>Fachabteilungen</th>
                    {headers.map((header: Headers) => (
                      <th key={`headings-${header.id}`}>{header.label}</th>
                    ))}
                  </tr>
                </thead>
                <tbody>
                  {listData.departmentCapacities.map((department: any) => {
                    const expanded = expandedRows.includes(
                      `list-${department.name}`
                    );

                    const trClass = classNames("list-view__table__row", {
                      "--expanded": expanded
                    });

                    return (
                      <React.Fragment key={department.id}>
                        <tr
                          className={trClass}
                          onClick={() => toggleRow(`list-${department.name}`)}
                        >
                          <td>
                            <CaretRight color="#000" />
                          </td>
                          <td>{department.name}</td>
                          {headers.map((header: Headers) => (
                            <td key={`header-${header.id}-${department.id}`}>
                              {`${department[header.value].freeCapacity} / ${
                                department[header.value].maxCapacity
                              }`}
                            </td>
                          ))}
                        </tr>
                        {expanded &&
                          department.wardCapacities.map((ward: any) => (
                            <tr
                              key={ward.id}
                              className="list-view__table__row --sub"
                            >
                              <td>o</td>
                              <td>{ward.name}</td>
                              {headers.map((header): any => (
                                <td key={`inner-${header.id}-${ward.id}`}>
                                  {`${ward[header.value].freeCapacity} / ${
                                    ward[header.value].maxCapacity
                                  }`}
                                </td>
                              ))}
                            </tr>
                          ))}
                      </React.Fragment>
                    );
                  })}
                </tbody>
              </Table>
            </div>
          )}
        </div>
      </Row>
    </Container>
  );
};

export default ListView;
