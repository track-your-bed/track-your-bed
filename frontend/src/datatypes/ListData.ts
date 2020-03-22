export interface Bed {
    id: string;
    name: string;
    state_last_changed: string;
    bed_type: string;
    bed_state: string;
}

export interface Ward {
    id: string;
    name: string;
    wardtype: string;
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
