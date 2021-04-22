CREATE TABLE ASIGNATURA (CODIGO INTEGER NOT NULL, CREDITOS_PRACTICOS DOUBLE NOT NULL, CREDITOS_TEORICOS DOUBLE NOT NULL, CUATRIMESTRE INTEGER, CURSO INTEGER, IDIOMAS VARCHAR, NOMBRE VARCHAR NOT NULL, OFERTADA BOOLEAN NOT NULL, REFERENCIA INTEGER, TITULACION_CODIGO INTEGER NOT NULL, PRIMARY KEY (CODIGO, TITULACION_CODIGO))
CREATE TABLE OPTATIVA (CODIGO INTEGER NOT NULL, CREDITOS_PRACTICOS DOUBLE NOT NULL, CREDITOS_TEORICOS DOUBLE NOT NULL, CUATRIMESTRE INTEGER, CURSO INTEGER, IDIOMAS VARCHAR, MENCION VARCHAR, NOMBRE VARCHAR NOT NULL, OFERTADA BOOLEAN NOT NULL, PLAZAS INTEGER, REFERENCIA INTEGER, TITULACION_CODIGO INTEGER NOT NULL, PRIMARY KEY (CODIGO, TITULACION_CODIGO))
CREATE TABLE ALUMNO (DNI VARCHAR(9) NOT NULL, CODIGO_POSTAL INTEGER, DIRECCION_NOTIFICACION VARCHAR, EMAIL_INSTITUCIONAL VARCHAR NOT NULL, EMAIL_PERSONAL VARCHAR, LOCALIDAD_NOTIFICACION VARCHAR(64), NOMBRE_COMPLETO VARCHAR NOT NULL, PROVINCIA_NOTIFICACION VARCHAR(32), TELEFONO_FIJO INTEGER, TELEFONO_MOVIL INTEGER, PRIMARY KEY (DNI))
CREATE TABLE EXPEDIENTE (NUMERO INTEGER NOT NULL, ACTIVO BOOLEAN, CREDITOS_FB DOUBLE, CREDITOS_OB DOUBLE, CREDITOS_PE DOUBLE, CREDITOS_TF DOUBLE, NOTA_MEDIA DOUBLE, ALUMNO_DNI VARCHAR(9) NOT NULL, TITULACION_CODIGO INTEGER NOT NULL, PRIMARY KEY (NUMERO))
CREATE TABLE ENCUESTA (FECHA_ENVIO DATE NOT NULL, EXPEDIENTE INTEGER NOT NULL, PRIMARY KEY (FECHA_ENVIO, EXPEDIENTE))
CREATE TABLE GRUPOS_ASIG (CURSO VARCHAR(5) NOT NULL, OFERTA VARCHAR(10), CODIGO INTEGER NOT NULL, TITULACION_CODIGO INTEGER NOT NULL, GRUPO_ID VARCHAR(10) NOT NULL, PRIMARY KEY (CURSO, CODIGO, TITULACION_CODIGO, GRUPO_ID))
CREATE TABLE MATRICULA (CURSO VARCHAR(10) NOT NULL, ACTIVA BOOLEAN NOT NULL, FECHA_MATRICULA DATE, LISTADO_ASIGNATURAS VARCHAR, NUEVO_INGRESO BOOLEAN, NUMERO_ARCHIVO INTEGER NOT NULL UNIQUE, TIPO_ESTUDIO VARCHAR(10), TURNO VARCHAR(6), EXPEDIENTE_NUMERO INTEGER NOT NULL, PRIMARY KEY (CURSO, EXPEDIENTE_NUMERO))
CREATE TABLE GRUPO (ID VARCHAR(10) NOT NULL, ASIGNAR VARCHAR, CURSO INTEGER NOT NULL, INGLES BOOLEAN NOT NULL, LETRA VARCHAR(1) NOT NULL, PLAZAS INTEGER, TURNO VARCHAR(6) NOT NULL, VISIBLE BOOLEAN, GRUPO_ID VARCHAR(10), TITULACION_CODIGO INTEGER NOT NULL, PRIMARY KEY (ID))
CREATE TABLE CENTRO (NOMBRE VARCHAR NOT NULL, DIRECCION VARCHAR, TLF_CONSERJERIA INTEGER, PRIMARY KEY (NOMBRE))
CREATE TABLE TITULACION (CODIGO INTEGER NOT NULL, CREDITOS INTEGER NOT NULL, NOMBRE VARCHAR NOT NULL, PRIMARY KEY (CODIGO))
CREATE TABLE ASIGNA_GRUPOS (CODIGO INTEGER NOT NULL, TITULACION_CODIGO INTEGER NOT NULL, GRUPO_ID VARCHAR(10), CURSO VARCHAR(10) NOT NULL, EXPEDIENTE_NUMERO INTEGER NOT NULL, PRIMARY KEY (CODIGO, TITULACION_CODIGO, CURSO, EXPEDIENTE_NUMERO))
CREATE TABLE CLASE (HORA_INICIO VARCHAR NOT NULL, DIA VARCHAR NOT NULL, HORA_FIN VARCHAR NOT NULL, TITULACION_CODIGO INTEGER NOT NULL, ASIGNATURA_CODIGO INTEGER NOT NULL, GRUPO_ID VARCHAR(10) NOT NULL, PRIMARY KEY (HORA_INICIO, DIA, GRUPO_ID))
CREATE TABLE PETICION (ID INTEGER NOT NULL, DESCRIPCION VARCHAR, TIPO VARCHAR, ALUMNO_DNI VARCHAR(9), PRIMARY KEY (ID))
CREATE TABLE ENCUESTA_GRUPOS (FECHA_ENVIO DATE NOT NULL, EXPEDIENTE INTEGER NOT NULL, CURSO VARCHAR(5) NOT NULL, GRUPO VARCHAR(10) NOT NULL, ASIGNATURA INTEGER NOT NULL, TITULACION INTEGER NOT NULL, PRIMARY KEY (FECHA_ENVIO, EXPEDIENTE, CURSO, GRUPO, ASIGNATURA, TITULACION))
CREATE TABLE TITUL_CENTRO (CENTRO_NOMBRE VARCHAR NOT NULL, TITULACION_CODIGO INTEGER NOT NULL, PRIMARY KEY (CENTRO_NOMBRE, TITULACION_CODIGO))
ALTER TABLE ASIGNATURA ADD CONSTRAINT FK_ASIGNATURA_TITULACION_CODIGO FOREIGN KEY (TITULACION_CODIGO) REFERENCES TITULACION (CODIGO)
ALTER TABLE OPTATIVA ADD CONSTRAINT FK_OPTATIVA_TITULACION_CODIGO FOREIGN KEY (TITULACION_CODIGO) REFERENCES TITULACION (CODIGO)
ALTER TABLE EXPEDIENTE ADD CONSTRAINT FK_EXPEDIENTE_ALUMNO_DNI FOREIGN KEY (ALUMNO_DNI) REFERENCES ALUMNO (DNI)
ALTER TABLE EXPEDIENTE ADD CONSTRAINT FK_EXPEDIENTE_TITULACION_CODIGO FOREIGN KEY (TITULACION_CODIGO) REFERENCES TITULACION (CODIGO)
ALTER TABLE ENCUESTA ADD CONSTRAINT FK_ENCUESTA_EXPEDIENTE FOREIGN KEY (EXPEDIENTE) REFERENCES EXPEDIENTE (NUMERO)
ALTER TABLE GRUPOS_ASIG ADD CONSTRAINT FK_GRUPOS_ASIG_GRUPO_ID FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO (ID)
ALTER TABLE MATRICULA ADD CONSTRAINT FK_MATRICULA_EXPEDIENTE_NUMERO FOREIGN KEY (EXPEDIENTE_NUMERO) REFERENCES EXPEDIENTE (NUMERO)
ALTER TABLE GRUPO ADD CONSTRAINT FK_GRUPO_GRUPO_ID FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO (ID)
ALTER TABLE GRUPO ADD CONSTRAINT FK_GRUPO_TITULACION_CODIGO FOREIGN KEY (TITULACION_CODIGO) REFERENCES TITULACION (CODIGO)
ALTER TABLE ASIGNA_GRUPOS ADD CONSTRAINT FK_ASIGNA_GRUPOS_GRUPO_ID FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO (ID)
ALTER TABLE ASIGNA_GRUPOS ADD CONSTRAINT FK_ASIGNA_GRUPOS_CURSO FOREIGN KEY (CURSO, EXPEDIENTE_NUMERO) REFERENCES MATRICULA (CURSO, EXPEDIENTE_NUMERO)
ALTER TABLE CLASE ADD CONSTRAINT FK_CLASE_GRUPO_ID FOREIGN KEY (GRUPO_ID) REFERENCES GRUPO (ID)
ALTER TABLE PETICION ADD CONSTRAINT FK_PETICION_ALUMNO_DNI FOREIGN KEY (ALUMNO_DNI) REFERENCES ALUMNO (DNI)
ALTER TABLE ENCUESTA_GRUPOS ADD CONSTRAINT FK_ENCUESTA_GRUPOS_FECHA_ENVIO FOREIGN KEY (FECHA_ENVIO, EXPEDIENTE) REFERENCES ENCUESTA (FECHA_ENVIO, EXPEDIENTE)
ALTER TABLE ENCUESTA_GRUPOS ADD CONSTRAINT FK_ENCUESTA_GRUPOS_CURSO FOREIGN KEY (CURSO, ASIGNATURA, TITULACION, GRUPO) REFERENCES GRUPOS_ASIG (CURSO, CODIGO, TITULACION_CODIGO, GRUPO_ID)
ALTER TABLE TITUL_CENTRO ADD CONSTRAINT FK_TITUL_CENTRO_CENTRO_NOMBRE FOREIGN KEY (CENTRO_NOMBRE) REFERENCES CENTRO (NOMBRE)
ALTER TABLE TITUL_CENTRO ADD CONSTRAINT FK_TITUL_CENTRO_TITULACION_CODIGO FOREIGN KEY (TITULACION_CODIGO) REFERENCES TITULACION (CODIGO)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT NUMERIC(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
