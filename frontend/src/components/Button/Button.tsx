import * as React from "react";
import * as classNames from "classnames";

interface Button {
    id: string;
  text: string;
  className: string;
  onClick: (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
}

const Button: React.FunctionComponent<Button> = ({
  id,
  text,
  className,
  onClick
}: Button) => (
    <div>
      <button type="button" id={id} className={className} onClick={onClick}>{text}</button>
    </div>
  );

export default Button;