export interface InfoItem {
  id: string;
  name: string;
}

export interface Bed {
  id: string;
  name: string;
  state_last_changed: string;
  bed_type: InfoItem;
  bed_state: InfoItem;
}

export interface Ward {
  id: string;
  name: string;
  stationtype: InfoItem;
  bed: Bed[];
}

export interface Department {
  id: string;
  name: string;
  ward: Ward[];
}

export interface ListData {
  id: string;
  name: string;
  max_capacity: number;
  lat: string;
  long: string;
  department: Department[];
}
