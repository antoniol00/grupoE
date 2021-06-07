package es.uma.informatica.sii.web.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import es.uma.informatica.sii.anotaciones.Requisitos;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlumnosIntegracion {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	private static String URL_ALUMNOS = "/home/alumno/git/grupoE/grupoE-ejb/DATOS/alumnos.csv";

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
		driver.manage().window().setSize(new Dimension(1050, 626));
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	// Diseño de pagina con elementos principales para importar y listar alumnos
	@Requisitos({ "RF2.1" })
	@Test
	public void test07DisenoInicialAlumnos() {
		driver.get("http://localhost:8080/grupoE-war/faces/alumnos.xhtml");
		assertThat(driver.getTitle(), is("Alumnos"));
		assertThat(driver.findElement(By.id("titulo_listado")).getText(), is("Listado de alumnos"));
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(1)")).getText(), is("DNI"));
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(2)")).getText(), is("Nombre"));
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(3)")).getText(), is("Email inst."));
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(4)")).getText(), is("Telefono Movil"));
		{
			List<WebElement> elements = driver.findElements(By.id("j_idt16:file-id"));
			assert (elements.size() == 1);
		}
		{
			List<WebElement> elements = driver.findElements(By.id("j_idt16:upload"));
			assert (elements.size() == 1);
		}
		{
			List<WebElement> elements = driver.findElements(By.id("j_idt16:borrarTodo"));
			assert (elements.size() == 1);
		}
	}

	// Al importar alumnos de un fichero no valido o de un fichero nulo, debe
	// indicar que ha habido un error y no importar nada
	@Requisitos({ "RF2.1" })
	@Test
	public void test08ImportarAlumnosFicheroErroneo() {
		driver.get("http://localhost:8080/grupoE-war/faces/alumnos.xhtml");
		driver.findElement(By.id("j_idt16:upload")).click();
		assertThat(driver.switchTo().alert().getText(), is(
				"La subida de archivos debe hacerse desde alumnos.csv. Se cargaran los datos de ALUMNOS, EXPEDIENTES Y MATRICULAS. Confirmar?"));
		driver.switchTo().alert().accept();
		assertThat(driver.findElement(By.id("j_idt16:mensaje_subida")).getText(),
				is("Error de importación. Sin cambios"));
	}

	// Al importar alumnos de un fichero valido debe poderse ver un total de 1508
	// alumnos y su informacion
	@Requisitos({ "RF2.1" })
	@Test
	public void test09ImportarAlumnosCorrectamente() {
		driver.get("http://localhost:8080/grupoE-war/faces/alumnos.xhtml");
		driver.findElement(By.id("j_idt16:file-id")).sendKeys(URL_ALUMNOS);
		driver.findElement(By.id("j_idt16:upload")).click();
		assertThat(driver.switchTo().alert().getText(), is(
				"La subida de archivos debe hacerse desde alumnos.csv. Se cargaran los datos de ALUMNOS, EXPEDIENTES Y MATRICULAS. Confirmar?"));
		driver.switchTo().alert().accept();
		{
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.textToBe(By.id("j_idt18:0:dni"), "00128979G"));
		}
		assertThat(driver.getTitle(), is("Alumnos"));
		assertThat(driver.findElement(By.id("j_idt18:0:dni")).getText(), is("00128979G"));
		assertThat(driver.findElement(By.id("j_idt18:0:nombre")).getText(), is("Andre Guillen Espinoza"));
		assertThat(driver.findElement(By.id("j_idt18:0:email1")).getText(), is("06105600113@uma.es"));
		assertThat(driver.findElement(By.id("j_idt18:0:telef_mov")).getText(), is("678405943"));
		{
			List<WebElement> elements = driver.findElements(By.id("mas_info"));
			assert (elements.size() > 0);
		}
		driver.findElement(By.id("mas_info")).click();
		{
			List<WebElement> elements = driver.findElements(By.id("j_idt18:0:dniCompleto"));
			assert (elements.size() > 0);
		}
		driver.findElement(By.cssSelector("#containerID1 .close > span")).click();
		{
			WebElement element = driver.findElement(By.cssSelector(".scrolltabla"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).clickAndHold().perform();
		}
		{
			WebElement element = driver.findElement(By.cssSelector(".scrolltabla"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		{
			WebElement element = driver.findElement(By.cssSelector(".scrolltabla"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).release().perform();
		}
		assertThat(driver.findElement(By.id("j_idt18:1507:dni")).getText(), is("99975279T"));
	}

	// Al seleccionar la opcion de vaciar datos, se debe confirmar que se ha borrado
	// correctamente y no aparece ningun alumno en la vista
	@Requisitos({ "RF2.1" })
	@Test
	public void test10VaciarDatosAlumnos() {
		driver.get("http://localhost:8080/grupoE-war/faces/alumnos.xhtml");
		driver.findElement(By.id("j_idt16:borrarTodo")).click();
		assertThat(driver.switchTo().alert().getText(), is(
				"Desea vaciar datos de ALUMNOS? Recuerde que todas las peticiones y expedientes relacionadas tambien seran eliminadas"));
		driver.switchTo().alert().accept();
		{
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.textToBe(By.id("j_idt16:mensaje_subida"), "Borrado correcto"));
		}
		{
			List<WebElement> elements = driver.findElements(By.id("j_idt18:0:dni"));
			assert (elements.size() == 0);
		}
	}

}