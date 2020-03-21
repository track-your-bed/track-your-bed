import * as React from "react";
import Label from "../Label/Label";

interface InputField {
  id: string;
  label: string;
  type?: string;
  onChange: (event: React.FormEvent<HTMLInputElement>) => void;
}

const InputField: React.FunctionComponent<InputField> = ({
  id,
  label,
  type = "text",
  onChange
}: InputField) => {
  return (
    <div>
        <Label id={id} label={label} />
        <input type={type} id={id} onChange={onChange} />
    </div>
  );
};

export default InputField;
