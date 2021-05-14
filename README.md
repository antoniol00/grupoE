# Grupo E en Sistemas de Información para Internet

## Diseño de aplicación para gestión de grupos en secretaría

## Integrantes
-   Antonio Lara Gutiérrez
-   Víctor Ramírez Mármol
-   Federico González Vico Berral
-   Manuel López Aroca
-   Francisco José García Barbero
 
### TAREA 0'. Requisitos Definitivos
El documento de requisitos se encuentra actualizado en el archivo REQUISITOS_FUNCIONALES_v1.1.pdf

### TAREA 1. Modelo Entidad-Relación y entidades JPA
Último commit realizado para la tarea:
commit 75e7b7b082ac9aa445d7b83de56299c41999cc33

El esquema de la base de datos creado en la asignatura de ABD también se encuentra en el repositorio con el nombre "modeloABD.ddl".

Al ejecutar la clase "Main" se genera el fichero "schema.sql" con las instrucciones que generan la base de datos.

### TAREA 2. Capa de negocio (EJB)
Último commit realizado para la tarea:
commit e9efca1204711a4827e9f627437458e7790c9c40

Las interfaces e implementaciones EJB se encuentran en la carpeta src/main/java/es/uma/informatica/sii/ejb.
Las entidades JPA de la tarea 1 se encuentra, al igual que antes, en src/main/java/es/uma/informatica/sii/entities.
Los test se encuentran en src/test/java/es/uma/informatica/sii/tests. La suite SuiteTest ejecuta un total de 29 test entre las 5 clases de prueba creadas. La clase BaseDatos.java inicializa la base de datos h2 al inicio.

NO HAY CAMBIOS EN LOS REQUISITOS DE LA TAREA 0'.

CAMBIOS ENTIDADES JPA:

 - Se ha creado la clase PETICIONES, con el fin de poder implementar los requisitos RF4.1 y RF4.2 (Grupo RF4 Peticiones Puntuales).
 - La clave primaria de ASIGNATURA ha sido modificada a una clave compuesta de (CODIGO_ASIGNATURA, CODIGO_TITULACION). Esto es debido al formato de los datos a importar. Si consideramos la clave primaria como la referencia de la asignatura, nos encontramos con inconsistencias al insertar asignaturas de distintos grados. Esto evita el problema y permite la búsqueda más simple que con referencia, ya que el código de la asignatura es conocido por todos, mientras que la referencia es más extraña y difícil de ubicar.
 - El método de herencia usado para la clase OPTATIVA se ha modificado a TABLE_PER_CLASS con el fin de poder dividir en dos tablas distintas las optativas y otras asignaturas.
 - La importación con el método de herencia anterior daría error como consecuencia de duplicidad de la clave primaria.
 - Se ha cambiado los atributos "creditos_xx" de la entidad EXPEDIENTE para que sean de tipo Double, ya que estaban asignados como Integer y teníamos el problema de insertar créditos con decimales.

### TAREA 3. Aplicación completa
Pendiente
