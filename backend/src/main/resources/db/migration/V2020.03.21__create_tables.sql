CREATE TABLE public.hospital (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR (255) NOT NULL,
    max_capacity SMALLINT NOT NULL,
    lat VARCHAR (255),
    long VARCHAR (255)
);

CREATE TABLE public.department (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR (255) NOT NULL,
    hospital_id UUID REFERENCES hospital(id) NOT NULL
);

CREATE INDEX department_idx_hospital_id ON public.department USING btree(hospital_id);

CREATE TABLE public.ward_type (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR (255) NOT NULL
);

ALTER TABLE ONLY public.ward_type
    ADD CONSTRAINT ward_type_unique_name UNIQUE(name);

CREATE TABLE public.ward (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR (255) NOT NULL,
    department_id UUID REFERENCES department(id) NOT NULL,
    ward_type_name VARCHAR (255) REFERENCES ward_type(name) NOT NULL
);

CREATE INDEX ward_idx_department_id ON public.ward USING btree(department_id);
CREATE INDEX ward_idx_ward_type_name ON public.ward USING btree(ward_type_name);

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
    ward_id UUID REFERENCES ward(id) NOT NULL,
    bed_type_name VARCHAR (255) REFERENCES bed_type(name) NOT NULL,
    bed_state_name VARCHAR (255) REFERENCES bed_state(name) NOT NULL
);

CREATE INDEX bed_idx_ward_id ON bed USING btree(ward_id);
CREATE INDEX bed_idx_bed_type_name ON bed USING btree(bed_type_name);
CREATE INDEX bed_idx_bed_state_name ON bed USING btree(bed_state_name);

CREATE FUNCTION state_changed() RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF NEW.bed_state_name != OLD.bed_state_name THEN
        NEW.state_last_changed := EXTRACT(EPOCH FROM NOW()) * 1000;
    END IF;
    RETURN NEW;
END;
$$;

CREATE TRIGGER trigger_bed_state_changed
    BEFORE UPDATE ON bed
    FOR EACH ROW
EXECUTE PROCEDURE state_changed();