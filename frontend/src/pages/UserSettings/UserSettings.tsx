

import * as React from "react";
import {Button} from 'primereact/button';

import 'primereact/resources/themes/nova-light/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

const divStyle = {
  margin: '50px'
};

const { Select } = require('mx-react-components');

const departmentTypes = [
//  {label: 'Kardiologie', value: 'Kardiologie'},
//  {label: 'Urologie', value: 'Urologie'},
//  {label: 'Radiologie', value: 'Radiologie'}
{displayValue: 'Akutgeriatrie/Remobilisation ', value: 'Akutgeriatrie/Remobilisation'},
{displayValue: 'Allergologie', value: 'Allergologie'},
{displayValue: 'Anästhesiologie', value: 'Anästhesiologie'},
{displayValue: 'Angiologie', value: 'Angiologie'},
{displayValue: 'Augenheilkunde', value: 'Augenheilkunde'},
{displayValue: 'Chirurgie', value: 'Chirurgie'},
{displayValue: '--Allgemeine Chirurgie', value: 'Allgemeine Chirurgie'},
{displayValue: '--Gefäßchirurgie', value: 'Gefäßchirurgie'},
{displayValue: '--Herzchirurgie', value: 'Herzchirurgie'},
{displayValue: '--Kinderchirurgie', value: 'Kinderchirurgie'},
{displayValue: 'Mund-, Kiefer- und Gesichtschirurgie', value: 'Mund-, Kiefer- und Gesichtschirurgie'},
{displayValue: 'Orthopädische Chirurgie', value: 'Orthopädische Chirurgie'},
{displayValue: '--Unfallchirurgie', value: 'Unfallchirurgie'},
{displayValue: 'Plastische, rekonstruktive, ästhetische Chirurgie', value: 'Plastische, rekonstruktive, ästhetische Chirurgie'},
{displayValue: 'Thoraxchirurgie', value: 'Thoraxchirurgie'},
{displayValue: 'Viszeralchirurgie', value: 'Viszeralchirurgie'},
{displayValue: 'Dermatologie', value: 'Dermatologie'},
{displayValue: 'Endokrinologie', value: 'Endokrinologie'},
{displayValue: 'Gastroenterologie ', value: 'Gastroenterologie'},
{displayValue: 'Gynäkologie und Geburtshilfe', value: 'Gynäkologie und Geburtshilfe'},
{displayValue: 'Hals-, Nasen- und Ohrenheilkunde', value: 'Hals-, Nasen- und Ohrenheilkunde'},
{displayValue: 'Hämatologie', value: 'Hämatologie'},
{displayValue: 'Infektionsepidemiologie', value: 'Infektionsepidemiologie'},
{displayValue: 'Intensivmedizin ', value: 'Intensivmedizin'},
{displayValue: 'Kardiologie', value: 'Kardiologie'},
{displayValue: 'Kinder- und Jugendchirurgie', value: 'Kinder- und Jugendchirurgie'},
{displayValue: 'Kinder- und Jugendheilkunde', value: 'Kinder- und Jugendheilkunde'},
{displayValue: 'Kinder- und Jugendpsychiatrie', value: 'Kinder- und Jugendpsychiatrie'},
{displayValue: 'Nephrologie', value: 'Nephrologie'},
{displayValue: 'Neurochirurgie ', value: 'Neurochirurgie'},
{displayValue: 'Neurologie', value: 'Neurologie'},
{displayValue: 'Notfallambulanz', value: 'Notfallambulanz'},
{displayValue: 'Nuklearmedizin ', value: 'Nuklearmedizin'},
{displayValue: 'Onkologie', value: 'Onkologie'},
{displayValue: 'Orthopädie', value: 'Orthopädie'},
{displayValue: 'Palliativmedizin ', value: 'Palliativmedizin'},
{displayValue: 'Pneumologie', value: 'Pneumologie'},
{displayValue: 'Psychosomatik ', value: 'Psychosomatik'},
{displayValue: 'Psychiatrie', value: 'Psychiatrie'},
{displayValue: 'Rheumatologie', value: 'Rheumatologie'},
{displayValue: 'Strahlentherapie-Radioonkologie', value: 'Strahlentherapie-Radioonkologie'},
{displayValue: 'Unfallchirurgie', value: 'Unfallchirurgie'},
{displayValue: 'Urologie ', value: 'Urologie'},
{displayValue: 'Virologie', value: 'Virologie'},
{displayValue: 'Zahn-, Mund- und Kieferheilkunde', value: 'Zahn-, Mund- und Kieferheilkunde'}
];

const EditDepartment: React.FunctionComponent = () => {
  
  const [departmentType, setDepartmentType] = React.useState(
    null
  )

  const hospitalId = 0;
  const userId = 0; 

  function handleClickSubmit(event: any){
    let json = { userId: userId, departmentId: departmentType, hospitalId: hospitalId }
    console.log(json.departmentId)
  }

  return (
    <div style={divStyle}>
      <h1>Ihre Fachabteilung</h1>
        <form>
          <Select
            options={departmentTypes}
            withSearch={true}/>
          <div>
            <Button label="Speichern" onClick={handleClickSubmit}/>
          </div>
        </form>
    </div>
  );
};

export default EditDepartment;