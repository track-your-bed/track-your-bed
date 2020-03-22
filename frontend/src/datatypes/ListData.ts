export interface InfoItem {
    id: string;
    name: string;
}

export interface Bed {
    id: string;
    name: string;
    stateLastChanged: string;
    bedType: string;
    bedState: string;
}

export interface Ward {
    id: string;
    name: string;
    wardtype: InfoItem;
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
