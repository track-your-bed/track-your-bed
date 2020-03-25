import * as React from "react";
import { Link } from "react-router-dom";
import { Navbar, Nav, Button } from "react-bootstrap";

// Context
import { UserContext } from "../../contexts/UserContext";

interface Header {
  title?: string;
}

const Header: React.FunctionComponent<Header> = ({ title }: Header) => {
  const auth = React.useContext(UserContext);

  return (
    <header className="mb-4">
      <Navbar bg="light" expand="md">
        <Navbar.Brand>
          <Link to="/">TrackYourBed</Link>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse>
          <Nav className="w-100">
            <Nav.Link as={Link} to="/list">
              Stammdaten
            </Nav.Link>
            <Nav.Link
              as={Link}
              to="/wardBedManagement/989b78c4-1ae9-42d6-9408-686437dfc457"
            >
              Bettenmanagement
            </Nav.Link>
            <Nav.Link as={Link} to="/settings">
              Mein Profil
            </Nav.Link>
            <Nav.Link
              as={Button}
              variant="light"
              onClick={(auth as any).logout}
              className="ml-auto"
            >
              Logout
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </header>
  );
};

export default Header;
