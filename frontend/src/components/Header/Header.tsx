import * as React from "react";
import {
    BrowserRouter as Router,
    Link
  } from "react-router-dom";

interface IHeader{
    title: string;
}

const Header: React.FunctionComponent<IHeader> = ({title}:IHeader) => {
    return(
        <header>
            <div className="header-home">
                <Link to="/">Home</Link>
            </div>
            <div className="header-sidetitle">
                {title}
            </div>
            <div className = "header-profile">
                <Link to="/profile">Profil</Link>
            </div>
        </header>
    );
};

export default Header;