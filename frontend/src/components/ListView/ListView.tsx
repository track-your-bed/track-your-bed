import * as React from "react";

interface ListData {
  name: string;
  id: number;
}

interface ListView {
  listData?: ListData[];
}

const dummyData = [
  { name: "Paracelsus-Klinik Adorf", id: 1, hospitalId: "123" },
  { name: "Paracelsus-Klinik Reichenbach", id: 2, hospitalId: "123" },
  { name: "Paracelsus-Klinik Schäneck", id: 3, hospitalId: "123" },
  { name: "Klinikum Obergötlzsch Rodewisch", id: 4, hospitalId: "123" },
  { name: "Helios Vogtland Klinikum Plauen", id: 5, hospitalId: "123" }
];

const ListView: React.FunctionComponent<ListView> = ({
  listData = dummyData
}: ListView) => {
  return (
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>ID</th>
        </tr>
      </thead>
      <tbody>
        {listData.map(item => {
          return (
            <tr key={item.id}>
              <td>{item.name}</td>
              <td>{item.id}</td>
            </tr>
          );
        })}
      </tbody>
    </table>
  );
};

export default ListView;
