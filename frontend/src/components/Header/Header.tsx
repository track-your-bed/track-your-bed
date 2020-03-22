import * as React from "react";
import {Link} from "react-router-dom";
import {Menubar} from 'primereact/menubar';
import { useHistory } from 'react-router-dom'
import UserSettings from "../../pages/UserSettings/UserSettings";
import {Dialog} from "primereact/dialog";
import Label from "../Label/Label";

interface Header {
    title: string;
}

const Header: React.FunctionComponent<Header> = ({title}: Header) => {

    const history = useHistory();
    const [visible, setVisible] = React.useState(false);
    const navigateToPage = (path: string) => {
        console.log('Navigate to path ' + path);
        history.push(path);
    };

    function openUserDialog () {
        setVisible(true)
    };

    const items = [
        {
            label: 'Mein Klinikum',
            icon: 'pi pi-home',
            items: [
                {
                    label: 'Stammdatenverwaltung',
                    command: () => {
                        navigateToPage('/list')
                    }
                },
                {
                    separator: true
                },
                {
                    label: 'Stationsbettenmanagement',
                    command: () => {
                        navigateToPage('/wardBedManagement')
                    }
                },
            ]
        },
        {
            label: 'Mein Profil',
            icon: 'pi pi-fw pi-user',
            items: [
                {
                    label: 'Settings',
                    icon: 'pi pi-fw pi-cog',
                    command:()=>{openUserDialog()}
                },
            ]
        },
        {
            label: 'Quit',
            icon: 'pi pi-fw pi-power-off',
            command: () => {
                navigateToPage('/logout')
            }
        }
    ];
    return (<header>
            <Menubar model={items}/>

            <Dialog header="Benutzer Einstellungen" visible={visible} style={{width: '50vw'}} modal={true}
                    onHide={() => setVisible(false)} position="top">
                <UserSettings />

            </Dialog>
        </header>
    );
};
export default Header;
