import * as React from "react";
import { Link } from "react-router-dom";

interface Header {
  title: string;
}

const Header: React.FunctionComponent<Header> = ({ title }: Header) => (
  <header>
    <div className="header-home">
      <Link to="/">Home</Link>
    </div>
    <div className="header-sidetitle">{title}</div>
    <div className="header-profile">
      <Link to="/profile">Profil</Link>
    </div>
  </header>
);

export default Header;
