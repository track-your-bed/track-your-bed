import * as React from "react";
import { Link } from "react-router-dom";

// Components
import { Navbar, Nav } from "react-bootstrap";

interface Header {
  title?: string;
}

const Header: React.FunctionComponent<Header> = ({ title }: Header) => (
  <header>
    <Navbar bg="light">
      <Navbar.Brand>
        <Link to="/">TrackYourBed</Link>
      </Navbar.Brand>
      <Navbar.Collapse>
        <Nav className="w-100">
          <Nav.Link as={Link} to="/list">
            Stammdaten
          </Nav.Link>
          <Nav.Link as={Link} to="/wardBedManagement">
            Bettenmanagement
          </Nav.Link>
          <Nav.Link as={Link} to="/settings">
            Mein Profil
          </Nav.Link>
          <Nav.Link as={Link} to="/logout" className="ml-auto">
            Logout
          </Nav.Link>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  </header>
);

export default Header;
