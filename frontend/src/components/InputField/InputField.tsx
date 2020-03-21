import * as React from "react";

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
      <label htmlFor={id}>
        {label}
        <input type={type} id={id} onChange={onChange} />
      </label>
    </div>
  );
};

export default InputField;
