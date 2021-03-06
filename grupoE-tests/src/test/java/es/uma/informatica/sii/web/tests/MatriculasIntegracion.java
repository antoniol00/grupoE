package es.uma.informatica.sii.web.tests;

// Generated by Selenium IDE
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import es.uma.informatica.sii.anotaciones.Requisitos;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MatriculasIntegracion {
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
		driver.manage().window().setSize(new Dimension(1440,900));
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	// Diseño de pagina con elementos principales para importar y listar matriculas
	@Requisitos({ "RF3.1" })
	@Test
	public void test20DisenoInicialMatriculas() {
		driver.get("http://localhost:8080/grupoE-war/faces/matriculas.xhtml");
		assertThat(driver.getTitle(), is("Matriculas"));
		assertThat(driver.findElement(By.id("titulo_matricula")).getText(),
				is("Listado de matrículas (importadas desde Alumnos)"));
		assertThat(driver.findElement(By.id("j_idt16:orden_visualizacion")).getText(), is("Orden de visualización:"));
		{
			List<WebElement> elements = driver.findElements(By.id("j_idt16:tipo"));
			assert (elements.size() > 0);
		}
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(1)")).getText(), is("Curso"));
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(2)")).getText(), is("DNI"));
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(3)")).getText(), is("Turno pref."));
		assertThat(driver.findElement(By.cssSelector(".formatoHeader:nth-child(4)")).getText(), is("Tipo de Estudio"));
	}

	// Al importar los alumnos desde la vista de alumnos, aparecen correctamente sus
	// matriculas en la vista Matriculas
	@Requisitos({ "RF3.1" })
	@Test
	public void test21InformacionCorrectaMatriculas() {
		driver.get("http://localhost:8080/grupoE-war/faces/alumnos.xhtml");
		driver.findElement(By.id("j_idt16:file-id")).sendKeys(URL_ALUMNOS);
		driver.findElement(By.id("j_idt16:upload")).click();
		assertThat(driver.switchTo().alert().getText(), is(
				"La subida de archivos debe hacerse desde alumnos.csv. Se cargaran los datos de ALUMNOS, EXPEDIENTES Y MATRICULAS. Confirmar?"));
		driver.switchTo().alert().accept();
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.textToBe(By.id("j_idt16:mensaje_subida"), "Importación correcta"));
		}
		driver.get("http://localhost:8080/grupoE-war/faces/matriculas.xhtml");
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.textToBe(By.id("j_idt22:0:expediente"), "83582041G"));
		}
		assertThat(driver.findElement(By.id("j_idt22:0:tipo_estudio")).getText(), is("GRADO"));
		{
			List<WebElement> elements = driver.findElements(By.id("mas_info"));
			assert (elements.size() > 0);
		}
	}

	// Se generan diferentes listados segun el filtro escogido
	@Requisitos({ "RF3.2" })
	@Test
	public void test22OrdenadoMatriculas() {
		driver.get("http://localhost:8080/grupoE-war/faces/matriculas.xhtml");
		assertThat(driver.findElement(By.id("j_idt22:0:expediente")).getText(), is("83582041G"));
		{
			WebElement dropdown = driver.findElement(By.id("j_idt16:tipo"));
			dropdown.findElement(By.xpath("//option[. = 'Por fecha']")).click();
		}
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.textToBe(By.id("j_idt22:0:expediente"), "15461203A"));
		}
		{
			WebElement dropdown = driver.findElement(By.id("j_idt16:tipo"));
			dropdown.findElement(By.xpath("//option[. = 'Por número de archivo (mayor a menor)']")).click();
		}
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.textToBe(By.id("j_idt22:0:expediente"), "78280867P"));
		}
		{
			WebElement dropdown = driver.findElement(By.id("j_idt16:tipo"));
			dropdown.findElement(By.xpath("//option[. = 'Por número de archivo (menor a mayor)']")).click();
		}
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.textToBe(By.id("j_idt22:0:expediente"), "95115697E"));
		}
	}

	// Al vaciar desde la vista de Alumnos, tambien se vacia la informacion relativa
	// en la vista de Matriculas
	@Requisitos({ "RF3.1" })
	@Test
	public void test23VaciadoMatriculas() {

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
		driver.get("http://localhost:8080/grupoE-war/faces/matriculas.xhtml");
		{
			List<WebElement> elements = driver.findElements(By.id("j_idt22:0:expediente"));
			assert (elements.size() == 0);
		}
	}
}
