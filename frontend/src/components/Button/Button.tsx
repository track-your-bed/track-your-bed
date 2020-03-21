import * as React from "react";

interface Button {
    id: string;
  text: string;
  onClick: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
}

const Button: React.FunctionComponent<Button> = ({
  id,
  text,
  onClick
}: Button) => (
    <div>
      <button type="button" id={id} onClick={onClick}>{text}</button>
    </div>
  );


export default Button;
