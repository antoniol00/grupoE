CREATE TABLE alumno (
    dni                     VARCHAR2(9) NOT NULL,
    nombre_completo         VARCHAR2(255) NOT NULL,
    email_institucional     VARCHAR2(255) NOT NULL,
    email_personal          VARCHAR2(255),
    teléfono_fijo           NUMBER(9),
    teléfono_móvil          NUMBER(9),
    dirección_notificación  VARCHAR2(255),
    localidad_notificación  VARCHAR2(64),
    provincia_notificación  VARCHAR2(32),
    código_postal           NUMBER(5)
);

ALTER TABLE alumno ADD CONSTRAINT alumno_pk PRIMARY KEY ( dni );

CREATE TABLE asigna_grupos (
    grupo_id                     VARCHAR2(10),
    matricula_curso              VARCHAR2(6) NOT NULL,
    matricula_expediente_número  NUMBER(9) NOT NULL,
    asignatura_referencia        NUMBER(5) NOT NULL
);

ALTER TABLE asigna_grupos
    ADD CONSTRAINT asigna_grupos_pk PRIMARY KEY ( matricula_curso,
                                                  matricula_expediente_número,
                                                  asignatura_referencia );

CREATE TABLE asignatura (
    referencia          NUMBER(5) NOT NULL,
    código              NUMBER(3) NOT NULL,
    créditos_teóricos   NUMBER(3, 1) NOT NULL,
    créditos_prácticos  NUMBER(3, 1) NOT NULL,
    ofertada            CHAR(1) NOT NULL,
    nombre              VARCHAR2(255) NOT NULL,
    curso               NUMBER(1),
    carácter            VARCHAR2(32),
    duración            NUMBER(2),
    cuatrimestre        NUMBER(1),
    idiomas             VARCHAR2(32),
    titulacion_código   NUMBER(4) NOT NULL
);

ALTER TABLE asignatura ADD CONSTRAINT asignatura_pk PRIMARY KEY ( referencia );

CREATE TABLE centro (
    nombre           VARCHAR2(64) NOT NULL,
    dirección        VARCHAR2(255),
    tlf_conserjería  NUMBER(9)
);

ALTER TABLE centro ADD CONSTRAINT centro_pk PRIMARY KEY ( nombre );

ALTER TABLE centro ADD CONSTRAINT centro_nombre_un UNIQUE ( nombre );

CREATE TABLE clase (
    día                    VARCHAR2(10) NOT NULL,
    hora_inicio            VARCHAR2(5) NOT NULL,
    hora_fin               VARCHAR2(5) NOT NULL,
    asignatura_referencia  NUMBER(5) NOT NULL,
    grupo_id               VARCHAR2(10) NOT NULL
);

ALTER TABLE clase
    ADD CONSTRAINT clase_pk PRIMARY KEY ( día,
                                          hora_inicio,
                                          grupo_id );

CREATE TABLE encuesta (
    fecha_envío        DATE NOT NULL,
    expediente_número  NUMBER(9) NOT NULL
);


ALTER TABLE encuesta ADD CONSTRAINT encuesta_pk PRIMARY KEY ( fecha_envío,
                                                              expediente_número );

CREATE TABLE encuesta_grupos (
    encuesta_fecha_envío    DATE NOT NULL,
    encuesta_número         NUMBER(9) NOT NULL,
    grupos_asig_curso       VARCHAR2(5) NOT NULL,
    grupos_asig_id          VARCHAR2(10) NOT NULL,
    grupos_asig_referencia  NUMBER(5) NOT NULL
);

ALTER TABLE encuesta_grupos
    ADD CONSTRAINT encuesta_grupos_pk PRIMARY KEY ( encuesta_fecha_envío,
                                                    encuesta_número,
                                                    grupos_asig_curso,
                                                    grupos_asig_id,
                                                    grupos_asig_referencia );

CREATE TABLE expediente (
    número             NUMBER(9) NOT NULL,
    activo             CHAR(1),
    nota_media         NUMBER(3, 2),
    créditos_fb        NUMBER(3),
    créditos_ob        NUMBER(3),
    créditos_op        NUMBER(3),
    créditos_pe        NUMBER(3),
    créditos_tf        NUMBER(3),
    titulacion_código  NUMBER(4) NOT NULL,
    alumno_dni         VARCHAR2(9) NOT NULL
);

ALTER TABLE expediente ADD CONSTRAINT expediente_pk PRIMARY KEY ( número );

CREATE TABLE grupo (
    id                 VARCHAR2(10) NOT NULL,
    curso              NUMBER(1) NOT NULL,
    letra              VARCHAR2(1) NOT NULL,
    turno              VARCHAR2(6) NOT NULL,
    inglés             CHAR(1) NOT NULL,
    visible            CHAR(1),
    plazas             NUMBER(3),
    asignar            VARCHAR2(5),
    grupo_id           VARCHAR2(10),
    titulacion_código  NUMBER(4) NOT NULL
);

ALTER TABLE grupo ADD CONSTRAINT grupo_pk PRIMARY KEY ( id );

ALTER TABLE grupo ADD CONSTRAINT grupo_letra_un UNIQUE ( letra );

ALTER TABLE grupo ADD CONSTRAINT grupo_curso_un UNIQUE ( curso );

CREATE TABLE grupos_asig (
    curso                  VARCHAR2(5) NOT NULL,
    oferta                 VARCHAR2(10),
    grupo_id               VARCHAR2(10) NOT NULL,
    asignatura_referencia  NUMBER(5) NOT NULL
);

ALTER TABLE grupos_asig
    ADD CONSTRAINT grupos_asig_pk PRIMARY KEY ( curso,
                                                grupo_id,
                                                asignatura_referencia );

CREATE TABLE matricula (
    curso                VARCHAR2(6) NOT NULL,
    número_archivo       NUMBER(9) NOT NULL,
    activa               CHAR(1) NOT NULL,
    turno                VARCHAR2(6),
    tipo_estudio         VARCHAR2(10),
    fecha_matrícula      DATE,
    nuevo_ingreso        CHAR(1),
    listado_asignaturas  VARCHAR2(255),
    expediente_número    NUMBER(9) NOT NULL
);

ALTER TABLE matricula ADD CONSTRAINT matricula_pk PRIMARY KEY ( curso,
                                                                expediente_número );

ALTER TABLE matricula ADD CONSTRAINT matricula_número_archivo_un UNIQUE ( número_archivo );

CREATE TABLE optativa (
    referencia  NUMBER(5) NOT NULL,
    plazas      NUMBER(3),
    mención     VARCHAR2(20)
);

ALTER TABLE optativa ADD CONSTRAINT optativa_pk PRIMARY KEY ( referencia );

CREATE TABLE titul_centro (
    centro_nombre      VARCHAR2(64) NOT NULL,
    titulacion_código  NUMBER(4) NOT NULL
);

ALTER TABLE titul_centro ADD CONSTRAINT titul_centro_pk PRIMARY KEY ( centro_nombre,
                                                                      titulacion_código );

CREATE TABLE titulacion (
    código    NUMBER(4) NOT NULL,
    nombre    VARCHAR2(64) NOT NULL,
    créditos  NUMBER(3) NOT NULL
);

ALTER TABLE titulacion ADD CONSTRAINT titulacion_pk PRIMARY KEY ( código );

ALTER TABLE asigna_grupos
    ADD CONSTRAINT asigna_grupos_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE asigna_grupos
    ADD CONSTRAINT asigna_grupos_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE asigna_grupos
    ADD CONSTRAINT asigna_grupos_matricula_fk FOREIGN KEY ( matricula_curso,
                                                            matricula_expediente_número )
        REFERENCES matricula ( curso,
                               expediente_número );

ALTER TABLE asignatura
    ADD CONSTRAINT asignatura_titulacion_fk FOREIGN KEY ( titulacion_código )
        REFERENCES titulacion ( código );

ALTER TABLE clase
    ADD CONSTRAINT clase_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE clase
    ADD CONSTRAINT clase_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE encuesta
    ADD CONSTRAINT encuesta_expediente_fk FOREIGN KEY ( expediente_número )
        REFERENCES expediente ( número );

ALTER TABLE encuesta_grupos
    ADD CONSTRAINT encuesta_grupos_encuesta_fk FOREIGN KEY ( encuesta_fecha_envío,
                                                             encuesta_número )
        REFERENCES encuesta ( fecha_envío,
                              expediente_número );

ALTER TABLE encuesta_grupos
    ADD CONSTRAINT encuesta_grupos_grupos_asig_fk FOREIGN KEY ( grupos_asig_curso,
                                                                grupos_asig_id,
                                                                grupos_asig_referencia )
        REFERENCES grupos_asig ( curso,
                                 grupo_id,
                                 asignatura_referencia );

ALTER TABLE expediente
    ADD CONSTRAINT expediente_alumno_fk FOREIGN KEY ( alumno_dni )
        REFERENCES alumno ( dni );

ALTER TABLE expediente
    ADD CONSTRAINT expediente_titulacion_fk FOREIGN KEY ( titulacion_código )
        REFERENCES titulacion ( código );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_titulacion_fk FOREIGN KEY ( titulacion_código )
        REFERENCES titulacion ( código );

ALTER TABLE grupos_asig
    ADD CONSTRAINT grupos_asig_asignatura_fk FOREIGN KEY ( asignatura_referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE grupos_asig
    ADD CONSTRAINT grupos_asig_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE matricula
    ADD CONSTRAINT matricula_expediente_fk FOREIGN KEY ( expediente_número )
        REFERENCES expediente ( número );

ALTER TABLE optativa
    ADD CONSTRAINT optativa_asignatura_fk FOREIGN KEY ( referencia )
        REFERENCES asignatura ( referencia );

ALTER TABLE titul_centro
    ADD CONSTRAINT titul_centro_centro_fk FOREIGN KEY ( centro_nombre )
        REFERENCES centro ( nombre );

ALTER TABLE titul_centro
    ADD CONSTRAINT titul_centro_titulacion_fk FOREIGN KEY ( titulacion_código )
        REFERENCES titulacion ( código );
