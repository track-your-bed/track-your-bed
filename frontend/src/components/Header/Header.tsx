import * as React from "react";
import {
    BrowserRouter as Router,
    Link
  } from "react-router-dom";

interface IHeader{
    title: string
}

const Header: React.FunctionComponent<IHeader> = ({title}:IHeader) => {
    return(
        <header>
            <div className="Home">
                <Link to="/">Home</Link>
            </div>
            <div className="Sidetitle">
                {title}
            </div>
            <div className = "Profile">
            <Link to="/profile">Profil</Link>
            </div>
        </header>
    )
}

export default Header;