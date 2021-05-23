/**
 * verifica que se quiere borrar el objeto indicado
 */
var x = 0;

function compruebaBorrado() {
	return confirm("¿Desea borrar esta peticion?");
}

function compruebaVaciado() {
	return confirm("¿Desea vaciar datos de ALUMNOS? ¡Recuerde que todas las peticiones y expedientes relacionadas también serán eliminadas!");
}

function devuelveOrden() {
	x++;
	return x;
}