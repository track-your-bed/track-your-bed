import * as React from "react";
import {Menubar} from 'primereact/menubar';
import { useHistory } from 'react-router-dom'

interface Header {
    title: string;
}

const Header: React.FunctionComponent<Header> = ({title}: Header) => {

    const history = useHistory();
    const navigateToPage = (path: string) => {
        console.log('Navigate to path ' + path);
        history.push(path);
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
                    command:()=>{
                        navigateToPage('/settings')
                    }
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
        </header>
    );
};
export default Header;
