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

export interface Warn {
    id: string;
    name: string;
    warntype: InfoItem;
    bed: Bed[];
}

export interface Department {
    id: string;
    name: string;
    warn: Warn[];
}

export interface ListData {
    id: string;
    name: string;
    max_capacity: number;
    lat: string;
    long: string;
    department: Department[];
}
