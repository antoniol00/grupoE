// verifica borrado de peticion
function compruebaBorradoPeticion() {
	return confirm("Desea borrar esta peticion?");
}

// verifica vaciado de alumnos
function compruebaVaciadoAlumnos() {
	return confirm("Desea vaciar datos de ALUMNOS? Recuerde que todas las peticiones y expedientes relacionadas tambien seran eliminadas");
}

// verific subida de alumnos
function compruebaSubidaAlumnos() {
	return confirm("La subida de archivos debe hacerse desde alumnos.csv. Se cargaran los datos de ALUMNOS, EXPEDIENTES Y MATRICULAS. Confirmar?");
}

function compruebaAsignacion() {
	return confirm("Compruebe que alumnos, asignaturas y grupos han sido introducidos en el sistema. Continuar con la asignacion?");
}
