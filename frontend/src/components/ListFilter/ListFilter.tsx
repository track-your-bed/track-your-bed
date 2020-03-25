import * as React from "react";
import Select from "react-select";

interface ListFilter {
  placeholder?: string;
  options: any;
}

const ListFilter: React.FunctionComponent<ListFilter> = ({
  placeholder,
  options
}: ListFilter) => {
  return (
    <div className="w-50 my-3 mx-2">
      <Select options={options} placeholder={placeholder} isMulti />
    </div>
  );
};

export default ListFilter;
