import * as React from "react";

import Icon from "../datatypes/Icon.types";

const ChevronRight: React.FunctionComponent<Icon> = ({
  size = 24,
  color = "#FFF"
}: Icon) => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width={size}
    height={size}
    viewBox="0 0 24 24"
    fill="none"
    stroke={color}
    strokeWidth="2"
    strokeLinecap="round"
    strokeLinejoin="round"
  >
    <path d="M9 18l6-6-6-6" />
  </svg>
);
export default ChevronRight;
