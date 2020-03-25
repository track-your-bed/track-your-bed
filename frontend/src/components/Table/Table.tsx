/* eslint-disable react/prop-types */
/* eslint-disable react/display-name */
/* eslint-disable react/jsx-key */
/* eslint-disable react/jsx-props-no-spreading */
import * as React from "react";
import {
  useTable,
  useExpanded,
  useFilters,
  useGlobalFilter
} from "react-table";

// Components
import CaretRight from "../../icons/CaretRight";
import CaretDown from "../../icons/CaretDown";

// Styles
import "./Table.scss";

interface Table {
  rawData: any;
}

const headers = [
  { id: "header-all", label: "Freie Betten", value: "all" },
  { id: "header-normal", label: "Standard", value: "normal" },
  { id: "header-imc", label: "IMC", value: "imc" },
  { id: "header-icu", label: "ICU", value: "icu" },
  { id: "header-covid", label: "Covid Normal", value: "covid" },
  { id: "header-covidicu", label: "Covid Intensiv", value: "covidIcu" }
];

const Table: React.FunctionComponent<Table> = ({ rawData }: Table) => {
  const data = React.useMemo(() => rawData, []);

  const getStatusColor = (
    freeCapacity: number,
    maxCapacity: number
  ): string => {
    let statusColor: string;

    const percentage =
      maxCapacity > 0 ? Math.round((freeCapacity / maxCapacity) * 100) : 0;
    if (percentage >= 25) {
      statusColor = "green";
    } else if (percentage > 0) {
      statusColor = "orange";
    } else {
      statusColor = "red";
    }

    return statusColor;
  };

  function DefaultColumnFilter({
    column: { filterValue, preFilteredRows, setFilter }
  }: any) {
    const count = preFilteredRows.length;

    return (
      <input
        value={filterValue || ""}
        onChange={e => {
          setFilter(e.target.value || undefined); // Set undefined to remove the filter entirely
        }}
        placeholder={`Search ${count} records...`}
      />
    );
  }

  const SelectColumnFilter = ({
    column: { filterValue, setFilter, preFilteredRows, id }
  }: any) => {
    console.log("Filter Row");
    console.log(preFilteredRows);

    const selectOptions = React.useMemo(() => {
      return preFilteredRows.map((row: any) => row.values[id]);
    }, [id, preFilteredRows]);

    // const options: any = React.useMemo(() => {
    //   const optionsSet = new Set();

    //   preFilteredRows.forEach((row: any) => {
    //     console.log("fiter: ", row);
    //     optionsSet.add(row.values[id]);
    //   });

    //   // return [...optionsSet.values()];
    // }, [id, preFilteredRows]);

    // Render a multi-select box
    return (
      <select
        value={filterValue}
        onChange={e => {
          setFilter(e.target.value || undefined);
        }}
      >
        <option value="">All</option>
        {selectOptions.map((option: any, i: number) => (
          // eslint-disable-next-line react/no-array-index-key
          <option key={i} value={option}>
            {option}
          </option>
        ))}
      </select>
    );
  };

  const columns = React.useMemo(
    () => [
      {
        id: "expander",
        Header: ({ getToggleAllRowsExpandedProps, isAllRowsExpanded }: any) => {
          return (
            <span {...getToggleAllRowsExpandedProps()}>
              {isAllRowsExpanded ? <CaretDown /> : <CaretRight />}
            </span>
          );
        },
        Cell: ({ row }: any) => {
          console.log(row);
          let statusColor = "";

          if (row.depth > 0) {
            statusColor = getStatusColor(
              row.original.all.freeCapacity,
              row.original.all.maxCapacity
            );
          }

          return row.canExpand ? (
            <span {...row.getToggleRowExpandedProps()}>
              {row.isExpanded ? <CaretDown /> : <CaretRight />}
            </span>
          ) : (
            row.depth > 0 && <div className={`row-status --${statusColor}`} />
          );
        }
        // Use the row.canExpand and row.getToggleRowExpandedProps prop getter
        // to build the toggle for expanding a row
      },
      {
        Header: "",
        accessor: "name",
        Filter: SelectColumnFilter
      },
      ...headers.map(header => {
        return {
          Header: header.label,
          accessor: (row: any) =>
            `${row[header.value].freeCapacity} / ${
              row[header.value].maxCapacity
            }`
        };
      })
    ],
    []
  );

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow
  } = useTable(
    {
      columns,
      data,
      getSubRows: (row: any) => row.wardCapacities || []
    },
    useExpanded,
    useFilters
  );

  return (
    <table {...getTableProps()}>
      <thead>
        {headerGroups.map(headerGroup => (
          <tr {...headerGroup.getHeaderGroupProps()}>
            {headerGroup.headers.map((column: any) => {
              return (
                <th {...column.getHeaderProps()}>
                  {column.render("Header")}
                  <div>
                    {column.canFilter && column.id === "name"
                      ? column.render("Filter")
                      : null}
                  </div>
                </th>
              );
            })}
          </tr>
        ))}
      </thead>
      <tbody {...getTableBodyProps()}>
        {rows.map((row, i) => {
          prepareRow(row);
          return (
            <tr {...row.getRowProps()}>
              {row.cells.map(cell => {
                return <td {...cell.getCellProps()}>{cell.render("Cell")}</td>;
              })}
            </tr>
          );
        })}
      </tbody>
    </table>
  );
};

export default Table;
