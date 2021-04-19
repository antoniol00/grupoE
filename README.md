# Grupo E en Sistemas de Información para Internet

## Diseño de aplicación para gestión de grupos en secretaría

## Integrantes
-   Antonio Lara Gutiérrez
-   Víctor Ramírez Mármol
-   Federico González Vico Berral
-   Manuel López Aroca
-   Francisco José García Barbero

## Directorios
```
📦grupoE
 ┣ 📂src
 ┃ ┣ 📂main
 ┃ ┃ ┣ 📂java.es.uma.informatica.sii
 ┃ ┃ ┃	┣ 📂ejb -> interfaces e implementación EJB
 ┃ ┃ ┃	┣ 📂entities -> clases JPA de todas las entidades
 ┃ ┃ ┃	┗ 📂main
 ┃ ┃ ┗ 📂resources.META-INF
 ┃ ┃	 ┗ 📜persistence.xml -> definición contexto
 ┃ ┗ 📂test
 ┃	┣ 📂java.es.uma.informatica.sii
 ┃ 	┃  ┗ 📂tests
 ┃	┃	┣ 📜BaseDatos.java -> definicion bbdd pruebas
 ┃	┃	┗ 📜TestApplication.java
 ┃	┗ 📂resources.META-INF
 ┃          ┣ 📜domain.xml -> configuracion GlassFish
 ┃          ┗ 📜persistence.xml -> definición contexto pruebas
 ┣ 📂DATOS -> ficheros de datos para importar
 ┣ 📜REQUISITOS_FUNCIONALES_v1.1.pdf
 ┣ 📜LICENSE
 ┣ 📜README.md
 ┣ 📜esquema.dll -> esquema generado por las entidades JPA
 ┣ 📜modeloABD.dll -> esquema generado en la asignatura ABD
 ┗ 📜pom.xml
 ```
 
### TAREA 0'. Requisitos Definitivos
El documento de requisitos se encuentra actualizado en el archivo REQUISITOS_FUNCIONALES_v1.1.pdf

### TAREA 1. Modelo Entidad-Relación y entidades JPA
Último commit realizado para la tarea:
commit 75e7b7b082ac9aa445d7b83de56299c41999cc33

El esquema de la base de datos creado en la asignatura de ABD también se encuentra en el repositorio con el nombre "modeloABD.ddl".

Al ejecutar la clase "Main" se genera el fichero "schema.sql" con las instrucciones que generan la base de datos.

### TAREA 2. Capa de negocio (EJB)
Pendiente

### TAREA 3. Aplicación completa
Pendiente

