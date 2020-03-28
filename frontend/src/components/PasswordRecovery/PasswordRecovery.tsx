import * as React from "react";
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';


interface PasswordRecovery {
    abortFunction: (
        event: React.MouseEvent<HTMLButtonElement, MouseEvent>
    ) => void;
}

const PasswordRecovery: React.FunctionComponent<PasswordRecovery> = ({
                                                                         abortFunction
                                                                     }: PasswordRecovery) => {
    const [emailAddress, setEmailAddress] = React.useState("");

    const alertMessage = `E-Mail Address: ${emailAddress}`;

    function handleSubmit() {
        console.log("Clicked");

        alert(alertMessage);
    }

    return (
        <div>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="formRecoveryEmail">
                    <Form.Label>E-Mail Adresse</Form.Label>
                    <Form.Control as="input" value={emailAddress} onChange={(e) => setEmailAddress(e.currentTarget.value)} placeholder="E-Mail Adresse"/>
                </Form.Group>
                <Form.Row>
                    <Button variant="primary" type="submit">
                        Passwort zurücksetzen
                    </Button>
                    <Button variant="secondary" onClick={abortFunction}>
                        Abbrechen
                    </Button>
                </Form.Row>
            </Form>
        </div>
    );
};

export default PasswordRecovery;
