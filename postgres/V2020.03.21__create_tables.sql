CREATE EXTENSION "uuid-ossp";

CREATE TABLE public.hospital (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR (255) NOT NULL,
    max_capacity SMALLINT NOT NULL,
    lat VARCHAR (255),
    long VARCHAR (255)
);

CREATE TABLE public.station_type (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
name VARCHAR (255) NOT NULL
);

ALTER TABLE ONLY public.station_type
    ADD CONSTRAINT station_type_unique_name UNIQUE(name);

CREATE TABLE public.station (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR (255) NOT NULL,
    hospital_id UUID REFERENCES hospital(id) NOT NULL,
    station_type_id UUID REFERENCES station_type(id) NOT NULL
);

CREATE INDEX station_idx_hospital_id ON public.station USING btree(hospital_id);
CREATE INDEX station_idx_station_type_id ON public.station USING btree(station_type_id);

CREATE TABLE public.bed_type (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR (255)
);

ALTER TABLE ONLY public.bed_type
    ADD CONSTRAINT bed_type_unique_name UNIQUE(name);

CREATE INDEX bed_type_idx_name ON bed_type USING btree(name);

CREATE TABLE public.bed_state (
        id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
        name VARCHAR (255)
);

ALTER TABLE ONLY public.bed_state
    ADD CONSTRAINT bed_state_unique_name UNIQUE(name);

CREATE INDEX bed_state_idx_name ON bed_state USING btree(name);

CREATE TABLE public.bed (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL,
    state_last_changed BIGINT NOT NULL DEFAULT EXTRACT(EPOCH FROM now()) *  1000,
    station_id UUID REFERENCES station(id) NOT NULL,
    bed_type_id UUID REFERENCES bed_type(id) NOT NULL,
    bed_state_id UUID REFERENCES bed_state(id) NOT NULL
);

CREATE INDEX bed_idx_station_id ON bed USING btree(station_id);
CREATE INDEX bed_idx_bed_type_id ON bed USING btree(bed_type_id);
CREATE INDEX bed_idx_bed_state_id ON bed USING btree(bed_state_id);

CREATE FUNCTION state_changed() RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NEW.bed_state_id != OLD.bed_state_id THEN
        NEW.state_last_changed := EXTRACT(EPOCH FROM NOW()) * 1000;
    END IF;
    RETURN NEW;
END;
$$;

CREATE TRIGGER trigger_bed_state_changed
    BEFORE UPDATE ON bed
    FOR EACH ROW
EXECUTE PROCEDURE state_changed();