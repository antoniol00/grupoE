package es.uma.informatica.sii.web.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
// Generated by Selenium IDE
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import es.uma.informatica.sii.anotaciones.Requisitos;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PeticionesIntegracion {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
	}

	@After
	public void tearDown() {
		driver.quit();
		BaseDeDatos.vaciaPeticiones();
	}

	@AfterClass
	public static void afterClass() {
		BaseDeDatos.vaciaAlumnos();
	}

	@BeforeClass
	public static void beforeClass() {
		BaseDeDatos.insertaAlumno();
	}

	// Diseño de pagina con elementos principales para la generacion de las
	// incidencias
	@Requisitos({ "RF4.1" })
	@Test
	public void test01DisenoInicialPeticiones() {
		driver.get("http://localhost:8080/grupoE-war/faces/peticiones.xhtml");
		assertThat(driver.findElement(By.id("titulo_principal")).getText(), is("Listado de peticiones"));
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(1)")).getText(), is("DNI"));
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(2)")).getText(), is("Fecha"));
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(3)")).getText(), is("Tipo"));
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(4)")).getText(), is("Descripción"));
		{
			List<WebElement> elements = driver.findElements(By.id("creacion_peticion"));
			assert (elements.size() == 1);
		}
	}

	// Crear una peticion desde el asistente debe crear una peticion con los datos
	// correctos en el listado de peticiones de la pagina peticiones.xhtml
	@Requisitos({ "RF4.1" })
	@Test
	public void test02CreacionPeticionCorrecta() {
		driver.get("http://localhost:8080/grupoE-war/faces/peticiones.xhtml");
		driver.findElement(By.id("creacion_peticion")).click();
		driver.findElement(By.id("user-form:dni")).sendKeys("11111111A");
		{
			WebElement dropdown = driver.findElement(By.id("user-form:tipo"));
			dropdown.findElement(By.xpath("//option[. = 'Cambio idioma']")).click();
		}
		driver.findElement(By.id("user-form:desc")).sendKeys("nuevo idioma");
		driver.findElement(By.id("user-form:enviar")).click();
		assertThat(driver.getTitle(), is("Peticiones"));
		assertThat(driver.findElement(By.id("j_idt32:0:alumno")).getText(), is("11111111A"));
		assertThat(driver.findElement(By.id("j_idt32:0:tipo")).getText(), is("Cambio idioma"));
		assertThat(driver.findElement(By.id("j_idt32:0:descripcion")).getText(), is("nuevo idioma"));
		{
			List<WebElement> elements = driver.findElements(By.id("j_idt32:0:j_idt42:eliminar"));
			assert (elements.size() == 1);
		}
	}

	// Si se intentase crear una peticion para un alumno que no existe, se comunica
	// con un mensaje de error
	@Requisitos({ "RF4.1" })
	@Test
	public void test03CrearPeticionAlumnoNoExistente() {
		driver.get("http://localhost:8080/grupoE-war/faces/peticiones.xhtml");
		driver.findElement(By.id("creacion_peticion")).click();
		driver.findElement(By.id("user-form:dni")).sendKeys("xxx");
		driver.findElement(By.id("user-form:enviar")).click();
		assertThat(driver.getTitle(), is("Peticiones"));
		assertThat(driver.findElement(By.id("mensaje_error")).getText(),
				is("ERROR: peticion para alumno xxx no se ha podido crear"));
	}

	// Eliminar una peticion debe preguntar al usuario la confirmacion y su borrado
	// de la lista de peticiones actual
	@Requisitos({ "RF4.1" })
	@Test
	public void test04EliminarPeticion() {
		driver.get("http://localhost:8080/grupoE-war/faces/peticiones.xhtml");
		driver.findElement(By.id("creacion_peticion")).click();
		driver.findElement(By.id("user-form:dni")).sendKeys("11111111A");
		driver.findElement(By.id("user-form:enviar")).click();
		driver.findElement(By.id("j_idt32:0:j_idt42:eliminar")).click();
		assertThat(driver.switchTo().alert().getText(), is("Desea borrar esta peticion?"));
		driver.switchTo().alert().accept();
		assertThat(driver.getTitle(), is("Peticiones"));
		{
			List<WebElement> elements = driver.findElements(By.id("j_idt32:0:alumno"));
			assert (elements.size() == 0);
		}
	}

	// Permite modificar una petición para recoger otra incidencia o corregir algun
	// error
	@Requisitos({ "RF4.1" })
	@Test
	public void test05ModificarPeticionCorrectamente() {
		driver.get("http://localhost:8080/grupoE-war/faces/peticiones.xhtml");
		driver.findElement(By.id("creacion_peticion")).click();
		driver.findElement(By.id("user-form:dni")).sendKeys("11111111A");
		driver.findElement(By.id("user-form:desc")).sendKeys("grupo1");
		driver.findElement(By.id("user-form:enviar")).click();
		{
			List<WebElement> elements = driver.findElements(By.id("boton_edicion"));
			assert (elements.size() > 0);
		}
		driver.findElement(By.id("boton_edicion")).click();
		driver.findElement(By.id("j_idt32:0:user-form:desc")).sendKeys("grupo 2 semestre");
		driver.findElement(By.id("j_idt32:0:user-form:enviar")).click();
		assertThat(driver.getTitle(), is("Peticiones"));
		assertThat(driver.findElement(By.id("j_idt32:0:alumno")).getText(), is("11111111A"));
		assertThat(driver.findElement(By.id("j_idt32:0:tipo")).getText(), is("Cambio de grupo"));
		assertThat(driver.findElement(By.id("j_idt32:0:descripcion")).getText(), is("grupo 2 semestre"));
	}

	// Al crear una peticion de un alumno el mismo dia devuelve error, al
	// encontrarse ya registrada en el sistema
	@Requisitos({ "RF4.1" })
	@Test
	public void test06CrearPeticionYaExistente() {
		driver.get("http://localhost:8080/grupoE-war/faces/peticiones.xhtml");
		driver.findElement(By.id("creacion_peticion")).click();
		driver.findElement(By.id("user-form:dni")).sendKeys("11111111A");
		driver.findElement(By.id("user-form:enviar")).click();
		assertThat(driver.findElement(By.id("j_idt32:0:alumno")).getText(), is("11111111A"));
		assertThat(driver.findElement(By.id("j_idt32:0:tipo")).getText(), is("Cambio de grupo"));
		driver.findElement(By.id("creacion_peticion")).click();
		driver.findElement(By.id("user-form:dni")).sendKeys("11111111A");
		driver.findElement(By.id("user-form:enviar")).click();
		assertThat(driver.getTitle(), is("Peticiones"));
		assertThat(driver.findElement(By.id("mensaje_error")).getText(),
				is("ERROR: peticion para alumno 11111111A no se ha podido crear"));
		assertThat(driver.findElement(By.id("j_idt32:0:alumno")).getText(), is("11111111A"));
		assertThat(driver.findElement(By.id("j_idt32:0:tipo")).getText(), is("Cambio de grupo"));
		{
			List<WebElement> elements = driver.findElements(By.id("j_idt32:0:j_idt42:eliminar"));
			assert (elements.size() == 1);
		}

	}
}
