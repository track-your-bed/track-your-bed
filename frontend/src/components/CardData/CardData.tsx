import * as React from "react";
import { Card, Button } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";

interface CardData {
  title: string;
  subTitle?: string;
  mainId: string;
  subId: string;
  editLink?: string;
  deleteFunc: (departmentId: string) => void;
}

const CardData: React.FC<CardData> = ({
  title,
  subTitle,
  mainId,
  subId,
  editLink,
  deleteFunc
}: CardData) => {
  return (
    <Card className="mb-3">
      <Card.Body>
        <Card.Title>{title}</Card.Title>
        <Card.Subtitle className="mb-3 text-muted">{subTitle}</Card.Subtitle>
        <div className="text-right">
          {editLink && (
            <LinkContainer to={editLink}>
              <Card.Link as={Button} variant="primary">
                Bearbeiten
              </Card.Link>
            </LinkContainer>
          )}
          <Card.Link as={Button} variant="light" onClick={() => deleteFunc(subId as string)}>
            Löschen
          </Card.Link>
        </div>
      </Card.Body>
    </Card>
  );
};

export default CardData;
