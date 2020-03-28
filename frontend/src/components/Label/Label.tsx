import * as React from "react";

interface Label {
    id: string;
    label: string;
}

const Label: React.FunctionComponent<Label> = ({
                                                   id,
                                                   label
                                               }: Label) => {
    return (<label htmlFor={id}>{label}</label>);
};

export default Label;
