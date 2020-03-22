CREATE TABLE public.department_type (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR (255) NOT NULL
);

ALTER TABLE public.department_type
    ADD CONSTRAINT department_type_unique_name UNIQUE(name);

INSERT INTO public.department_type(name)
VALUES('anesthesiology');

INSERT INTO public.department_type(name)
VALUES('ophthalmology');

INSERT INTO public.department_type(name)
VALUES('surgey');

INSERT INTO public.department_type(name)
VALUES('general surgey');

INSERT INTO public.department_type(name)
VALUES('surgery - vascular surgery');

INSERT INTO public.department_type(name)
VALUES('surgery - cardiac surgery');

INSERT INTO public.department_type(name)
VALUES('surgery - pediatric surgery');

INSERT INTO public.department_type(name)
VALUES('surgery - oromaxillofacial surgery');

INSERT INTO public.department_type(name)
VALUES('surgery - neurosurgery');

INSERT INTO public.department_type(name)
VALUES('surgery - trauma surgery');

INSERT INTO public.department_type(name)
VALUES('surgery - plastic and aesthetic surgery');

INSERT INTO public.department_type(name)
VALUES('surgery - thoracic surgery');

INSERT INTO public.department_type(name)
VALUES('dermatology');

INSERT INTO public.department_type(name)
VALUES('gynecology and obstetrics');

INSERT INTO public.department_type(name)
VALUES('otorhinolaryngology');

INSERT INTO public.department_type(name)
VALUES('internal medicine - angiology');

INSERT INTO public.department_type(name)
VALUES('internal medicine - endocrinology');

INSERT INTO public.department_type(name)
VALUES('internal medicine - gastroenterology');

INSERT INTO public.department_type(name)
VALUES('internal medicine - hematology');

INSERT INTO public.department_type(name)
VALUES('internal medicine - infectiology');

INSERT INTO public.department_type(name)
VALUES('internal medicine - cardiology');

INSERT INTO public.department_type(name)
VALUES('internal medicine - nephrology');

INSERT INTO public.department_type(name)
VALUES('internal medicine - oncology');

INSERT INTO public.department_type(name)
VALUES('internal medicine - pneumology');

INSERT INTO public.department_type(name)
VALUES('internal medicine - rheumatology');

INSERT INTO public.department_type(name)
VALUES('intensive care unit - surgical ICU');

INSERT INTO public.department_type(name)
VALUES('intensive care unit - internal medicine ICU');

INSERT INTO public.department_type(name)
VALUES('intensive care unit - interdisciplinary ICU');

INSERT INTO public.department_type(name)
VALUES('pediatrics and adolescent medicine');

INSERT INTO public.department_type(name)
VALUES('neurologyy');

INSERT INTO public.department_type(name)
VALUES('emergency room');

INSERT INTO public.department_type(name)
VALUES('nuclear medicine');

INSERT INTO public.department_type(name)
VALUES('orthopedics');

INSERT INTO public.department_type(name)
VALUES('palliative care');

INSERT INTO public.department_type(name)
VALUES('psychosomatic care');

INSERT INTO public.department_type(name)
VALUES('psychiatry');

INSERT INTO public.department_type(name)
VALUES('radiotherapy');

INSERT INTO public.department_type(name)
VALUES('urology');

INSERT INTO public.department_type(name)
VALUES('virology');

ALTER TABLE ONLY public.department
    ADD COLUMN department_type_name VARCHAR (255) REFERENCES department_type(name) NOT NULL DEFAULT 'anesthesiology';

ALTER TABLE ONLY public.department
    ALTER COLUMN department_type_name DROP DEFAULT;

CREATE INDEX department_idx_department_type_name ON public.department USING btree(name);

