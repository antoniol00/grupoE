CREATE TABLE alumno (
    dni                     VARCHAR2(9) NOT NULL,
    nombre_completo         VARCHAR2(255) NOT NULL,
    email_institucional     VARCHAR2(255) NOT NULL,
    email_personal          VARCHAR2(255),
    tel�fono_fijo           NUMBER(9),
    tel�fono_m�vil          NUMBER(9),
    direcci�n_notificaci�n  VARCHAR2(255),
    localidad_notificaci�n  VARCHAR2(64),
    provincia_notificaci�n  VARCHAR2(32),
    c�digo_postal           NUMBER(5)
);


ALTER TABLE alumno ADD CONSTRAINT alumno_pk PRIMARY KEY ( dni )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE asigna_grupos (
    grupo_id                      VARCHAR2(10),
    matricula_curso               VARCHAR2(6) NOT NULL,
    matricula_expediente_n�mero   NUMBER(9) NOT NULL,
    asignatura_titulacion_c�digo  NUMBER(4) NOT NULL,
    asignatura_c�digo             NUMBER(3) NOT NULL
);

ALTER TABLE asigna_grupos
    ADD CONSTRAINT asigna_grupos_pk PRIMARY KEY ( matricula_curso,
                                                  matricula_expediente_n�mero,
                                                  asignatura_titulacion_c�digo,
                                                  asignatura_c�digo )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE asignatura (
    referencia          NUMBER(5) NOT NULL,
    c�digo              NUMBER(3) NOT NULL,
    cr�ditos_te�ricos   NUMBER(3, 1) NOT NULL,
    cr�ditos_pr�cticos  NUMBER(3, 1) NOT NULL,
    ofertada            CHAR(1) NOT NULL,
    nombre              VARCHAR2(255) NOT NULL,
    curso               NUMBER(1),
    cuatrimestre        NUMBER(1),
    idiomas             VARCHAR2(32),
    titulacion_c�digo   NUMBER(4) NOT NULL
);

ALTER TABLE asignatura ADD CONSTRAINT asignatura_pk PRIMARY KEY ( titulacion_c�digo,
                                                                  c�digo )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE centro (
    nombre           VARCHAR2(64) NOT NULL,
    direcci�n        VARCHAR2(255),
    tlf_conserjer�a  NUMBER(9)
);

ALTER TABLE centro ADD CONSTRAINT centro_pk PRIMARY KEY ( nombre ) USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE clase (
    d�a                           VARCHAR2(10) NOT NULL,
    hora_inicio                   VARCHAR2(5) NOT NULL,
    hora_fin                      VARCHAR2(5) NOT NULL,
    grupo_id                      VARCHAR2(10) NOT NULL,
    asignatura_titulacion_c�digo  NUMBER(4) NOT NULL,
    asignatura_c�digo             NUMBER(3) NOT NULL
);

ALTER TABLE clase
    ADD CONSTRAINT clase_pk PRIMARY KEY ( d�a,
                                          hora_inicio,
                                          grupo_id );

CREATE TABLE encuesta (
    fecha_env�o        DATE NOT NULL,
    expediente_n�mero  NUMBER(9) NOT NULL
);


ALTER TABLE encuesta ADD CONSTRAINT encuesta_pk PRIMARY KEY ( fecha_env�o,
                                                              expediente_n�mero )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE encuesta_grupos (
    encuesta_fecha_env�o  DATE NOT NULL,
    encuesta_n�mero       NUMBER(9) NOT NULL,
    grupos_asig_curso     VARCHAR2(5) NOT NULL,
    grupos_asig_id        VARCHAR2(10) NOT NULL,
    grupos_asig_c�digo1   NUMBER(4) NOT NULL,
    grupos_asig_c�digo    NUMBER(3) NOT NULL
);

ALTER TABLE encuesta_grupos
    ADD CONSTRAINT encuesta_grupos_pk PRIMARY KEY ( encuesta_fecha_env�o,
                                                    encuesta_n�mero,
                                                    grupos_asig_curso,
                                                    grupos_asig_id,
                                                    grupos_asig_c�digo1,
                                                    grupos_asig_c�digo )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE expediente (
    n�mero             NUMBER(9) NOT NULL,
    activo             CHAR(1),
    nota_media         NUMBER(3, 2),
    cr�ditos_fb        NUMBER(5, 2),
    cr�ditos_ob        NUMBER(5, 2),
    cr�ditos_op        NUMBER(5, 2),
    cr�ditos_pe        NUMBER(5, 2),
    cr�ditos_tf        NUMBER(5, 2),
    titulacion_c�digo  NUMBER(4) NOT NULL,
    alumno_dni         VARCHAR2(9) NOT NULL
);


ALTER TABLE expediente ADD CONSTRAINT expediente_pk PRIMARY KEY ( n�mero )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE grupo (
    id                 VARCHAR2(10) NOT NULL,
    curso              NUMBER(1) NOT NULL,
    letra              VARCHAR2(1) NOT NULL,
    turno              VARCHAR2(6) NOT NULL,
    ingl�s             CHAR(1) NOT NULL,
    visible            CHAR(1),
    plazas             NUMBER(3),
    asignar            VARCHAR2(5),
    grupo_id           VARCHAR2(10),
    titulacion_c�digo  NUMBER(4) NOT NULL
);

ALTER TABLE grupo ADD CONSTRAINT grupo_pk PRIMARY KEY ( id )  USING INDEX TABLESPACE TS_INDICES;

ALTER TABLE grupo ADD CONSTRAINT grupo_letra_un UNIQUE ( letra )  USING INDEX TABLESPACE TS_INDICES;

ALTER TABLE grupo ADD CONSTRAINT grupo_curso_un UNIQUE ( curso )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE grupos_asig (
    curso                         VARCHAR2(5) NOT NULL,
    oferta                        VARCHAR2(10),
    grupo_id                      VARCHAR2(10) NOT NULL,
    asignatura_titulacion_c�digo  NUMBER(4) NOT NULL,
    asignatura_c�digo             NUMBER(3) NOT NULL
);


ALTER TABLE grupos_asig
    ADD CONSTRAINT grupos_asig_pk PRIMARY KEY ( curso,
                                                grupo_id,
                                                asignatura_titulacion_c�digo,
                                                asignatura_c�digo )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE matricula (
    curso                VARCHAR2(6) NOT NULL,
    n�mero_archivo       NUMBER(9) NOT NULL,
    activa               CHAR(1) NOT NULL,
    turno                VARCHAR2(6),
    tipo_estudio         VARCHAR2(10),
    fecha_matr�cula      DATE,
    nuevo_ingreso        CHAR(1),
    listado_asignaturas  VARCHAR2(255),
    expediente_n�mero    NUMBER(9) NOT NULL
);


ALTER TABLE matricula ADD CONSTRAINT matricula_pk PRIMARY KEY ( curso,
                                                                expediente_n�mero )  USING INDEX TABLESPACE TS_INDICES;

ALTER TABLE matricula ADD CONSTRAINT matricula_n�mero_archivo_un UNIQUE ( n�mero_archivo )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE optativa (
    plazas   NUMBER(3),
    c�digo   NUMBER(3) NOT NULL,
    menci�n  VARCHAR2(20),
    c�digo1  NUMBER(4) NOT NULL
);


ALTER TABLE optativa ADD CONSTRAINT optativa_pk PRIMARY KEY ( c�digo1,
                                                              c�digo )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE peticion (
    id           NUMBER(9) NOT NULL,
    tipo         VARCHAR2(255),
    descripci�n  VARCHAR2(255),
    alumno_dni   VARCHAR2(9) NOT NULL
);


ALTER TABLE peticion ADD CONSTRAINT peticion_pk PRIMARY KEY ( id )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE titul_centro (
    centro_nombre      VARCHAR2(64) NOT NULL,
    titulacion_c�digo  NUMBER(4) NOT NULL
);

ALTER TABLE titul_centro ADD CONSTRAINT titul_centro_pk PRIMARY KEY ( centro_nombre,
                                                                      titulacion_c�digo )  USING INDEX TABLESPACE TS_INDICES;

CREATE TABLE titulacion (
    c�digo    NUMBER(4) NOT NULL,
    nombre    VARCHAR2(64) NOT NULL,
    cr�ditos  NUMBER(3) NOT NULL
);


ALTER TABLE titulacion ADD CONSTRAINT titulacion_pk PRIMARY KEY ( c�digo )  USING INDEX TABLESPACE TS_INDICES;

ALTER TABLE asigna_grupos
    ADD CONSTRAINT asigna_grupos_asignatura_fk FOREIGN KEY ( asignatura_titulacion_c�digo,
                                                             asignatura_c�digo )
        REFERENCES asignatura ( titulacion_c�digo,
                                c�digo );

ALTER TABLE asigna_grupos
    ADD CONSTRAINT asigna_grupos_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE asigna_grupos
    ADD CONSTRAINT asigna_grupos_matricula_fk FOREIGN KEY ( matricula_curso,
                                                            matricula_expediente_n�mero )
        REFERENCES matricula ( curso,
                               expediente_n�mero );

ALTER TABLE asignatura
    ADD CONSTRAINT asignatura_titulacion_fk FOREIGN KEY ( titulacion_c�digo )
        REFERENCES titulacion ( c�digo );

ALTER TABLE clase
    ADD CONSTRAINT clase_asignatura_fk FOREIGN KEY ( asignatura_titulacion_c�digo,
                                                     asignatura_c�digo )
        REFERENCES asignatura ( titulacion_c�digo,
                                c�digo );

ALTER TABLE clase
    ADD CONSTRAINT clase_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE encuesta
    ADD CONSTRAINT encuesta_expediente_fk FOREIGN KEY ( expediente_n�mero )
        REFERENCES expediente ( n�mero );

ALTER TABLE encuesta_grupos
    ADD CONSTRAINT encuesta_grupos_encuesta_fk FOREIGN KEY ( encuesta_fecha_env�o,
                                                             encuesta_n�mero )
        REFERENCES encuesta ( fecha_env�o,
                              expediente_n�mero );

ALTER TABLE encuesta_grupos
    ADD CONSTRAINT encuesta_grupos_grupos_asig_fk FOREIGN KEY ( grupos_asig_curso,
                                                                grupos_asig_id,
                                                                grupos_asig_c�digo1,
                                                                grupos_asig_c�digo )
        REFERENCES grupos_asig ( curso,
                                 grupo_id,
                                 asignatura_titulacion_c�digo,
                                 asignatura_c�digo );

ALTER TABLE expediente
    ADD CONSTRAINT expediente_alumno_fk FOREIGN KEY ( alumno_dni )
        REFERENCES alumno ( dni );

ALTER TABLE expediente
    ADD CONSTRAINT expediente_titulacion_fk FOREIGN KEY ( titulacion_c�digo )
        REFERENCES titulacion ( c�digo );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE grupo
    ADD CONSTRAINT grupo_titulacion_fk FOREIGN KEY ( titulacion_c�digo )
        REFERENCES titulacion ( c�digo );

ALTER TABLE grupos_asig
    ADD CONSTRAINT grupos_asig_asignatura_fk FOREIGN KEY ( asignatura_titulacion_c�digo,
                                                           asignatura_c�digo )
        REFERENCES asignatura ( titulacion_c�digo,
                                c�digo );

ALTER TABLE grupos_asig
    ADD CONSTRAINT grupos_asig_grupo_fk FOREIGN KEY ( grupo_id )
        REFERENCES grupo ( id );

ALTER TABLE matricula
    ADD CONSTRAINT matricula_expediente_fk FOREIGN KEY ( expediente_n�mero )
        REFERENCES expediente ( n�mero );

ALTER TABLE optativa
    ADD CONSTRAINT optativa_asignatura_fk FOREIGN KEY ( c�digo1,
                                                        c�digo )
        REFERENCES asignatura ( titulacion_c�digo,
                                c�digo );

ALTER TABLE peticion
    ADD CONSTRAINT peticion_alumno_fk FOREIGN KEY ( alumno_dni )
        REFERENCES alumno ( dni );

ALTER TABLE titul_centro
    ADD CONSTRAINT titul_centro_centro_fk FOREIGN KEY ( centro_nombre )
        REFERENCES centro ( nombre );

ALTER TABLE titul_centro
    ADD CONSTRAINT titul_centro_titulacion_fk FOREIGN KEY ( titulacion_c�digo )
        REFERENCES titulacion ( c�digo );