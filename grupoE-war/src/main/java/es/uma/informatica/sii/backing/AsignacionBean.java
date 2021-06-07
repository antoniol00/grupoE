package es.uma.informatica.sii.backing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import es.uma.informatica.sii.ejb.AsignacionImpl;
import es.uma.informatica.sii.ejb.AsignaturasImpl;
import es.uma.informatica.sii.ejb.GestionAsignacion;
import es.uma.informatica.sii.ejb.GestionAsignaturas;
import es.uma.informatica.sii.ejb.GestionMatriculas;
import es.uma.informatica.sii.ejb.MatriculasImpl;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Alumno;
import es.uma.informatica.sii.entities.Asigna_grupos;
import es.uma.informatica.sii.entities.Asignatura;
import es.uma.informatica.sii.entities.Grupo;
import es.uma.informatica.sii.entities.Grupos_asig;

@Named
@RequestScoped
@SuppressWarnings("unused")
public class AsignacionBean {
	@EJB
	private GestionAsignacion ga = new AsignacionImpl();
	@EJB
	private GestionMatriculas gm = new MatriculasImpl();
	@EJB
	private GestionAsignaturas gas = new AsignaturasImpl();

	private String mensaje;
	private String containerID;
	private String prevID;
	private int id;

	private List<Grupo> listaGrupos;
	private String grupos;

	public AsignacionBean() {
		id = 0;
	}

	public List<Asigna_grupos> getAsignacionProvisional() throws SecretariaException {
		return ga.listaAsignacionProvisional();
	}

	public String realizarAsignacion() {
		try {
			if (!gm.listaMatriculas("DATE").isEmpty() && !gas.listarAsignaturas().isEmpty()) {
				ga.asignaGruposAlumnos();
				mensaje = "Asignacion correcta";
			} else {
				mensaje = "Falta informacion";
			}
		} catch (Exception e) {
			mensaje = "No se pudo realizar asignacion";
		}
		return null;
	}

	public String vaciarDatos() {
		for (Asigna_grupos ag : ga.listaAsignacionProvisional()) {
			ga.borraAsignacion(ag);
		}
		mensaje = "Asignacion borrada completamente";
		return null;
	}

	public List<Grupo> listaGrupos(Asignatura asignatura) {
		List<Grupos_asig> lga = gas.listarGrupoXAsignaturas();
		List<Grupo> res = new ArrayList<>();

		for (Grupos_asig x : lga) {
			if (asignatura.getTitulacion().equals(x.getAsignatura().getTitulacion())
					&& asignatura.getCodigo().equals(x.getAsignatura().getCodigo())) {
				res.add(x.getGrupo());
			}
		}
		return res;
	}

	public String edita(Asigna_grupos x) {

		try {
			ga.modificaGrupo(x.getAsignatura().getCodigo(), x.getAsignatura().getTitulacion().getCodigo(),
					x.getMatricula().getCurso(), x.getMatricula().getExpediente().getNumero(), grupos);
		} catch (SecretariaException e) {
			return null;
		}

		return null;
	}

	public List<Alumno> getListaAlumnos() {
		List<Alumno> la = new ArrayList<>();
		for (Asigna_grupos ag : ga.listaAsignacionProvisional()) {
			if (!la.contains(ag.getMatricula().getExpediente().getAlumno()))
				la.add(ag.getMatricula().getExpediente().getAlumno());
		}
		return la;
	}

	public String existeColision(Integer col) throws SecretariaException {
		return ga.ColisionesHorario(col) ? "SI" : "NO";
	}

	public String getContainerID() {
		id++;
		return "#containerID" + id;
	}

	public String getPrevID() {
		return "containerID" + id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getGrupos() {
		return grupos;
	}

	public void setGrupos(String grupos) {
		this.grupos = grupos;
	}

	public String refresh() {
		return null;
	}

	public void creaPDF() {
		try {
			Document document = new Document();
			document.setMargins(20f, 20f, 20f, 20f);
			Date date = new Date();
			String filename = new SimpleDateFormat("'asignacionProvisional'yyyy-dd-MM-HHmmss'.pdf'").format(new Date());
			PdfWriter.getInstance(document, new FileOutputStream(filename));

			document.open();

			PdfPTable table = new PdfPTable(6);

			Stream.of("Expediente", "DNI", "Nombre", "Curso", "Asignatura", "Grupo").forEach(columnTitle -> {
				PdfPCell header = new PdfPCell();
				header.setBackgroundColor(BaseColor.LIGHT_GRAY);
				header.setBorderWidth(2);
				header.setPhrase(new Phrase(columnTitle));
				table.addCell(header);
			});

			Font fontCell = FontFactory.getFont(FontFactory.TIMES, 8, BaseColor.BLACK);
			for (Asigna_grupos ag : ga.listaAsignacionProvisional()) {
				table.addCell(
						new PdfPCell(new Phrase(ag.getMatricula().getExpediente().getNumero().toString(), fontCell)));
				table.addCell(
						new PdfPCell(new Phrase(ag.getMatricula().getExpediente().getAlumno().getDni(), fontCell)));
				table.addCell(new PdfPCell(
						new Phrase(ag.getMatricula().getExpediente().getAlumno().getNombre_completo(), fontCell)));
				table.addCell(new PdfPCell(new Phrase(ag.getAsignatura().getCurso().toString(), fontCell)));
				table.addCell(new PdfPCell(new Phrase(ag.getAsignatura().getCodigo().toString(), fontCell)));
				table.addCell(new PdfPCell(new Phrase(ag.getGrupo().getLetra(), fontCell)));
			}

			document.add(table);
			document.close();

			File file = new File("/opt/jboss/" + filename);
			String fileName = file.getName();
			int contentLength = (int) file.length();

			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext ec = ctx.getExternalContext();

			ec.responseReset();
			ec.setResponseContentType("application/pdf");
			ec.setResponseContentLength(contentLength);
			ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			java.io.OutputStream output = ec.getResponseOutputStream();
			Files.copy(file.toPath(), output);

			ctx.responseComplete();

			Files.delete(file.toPath());

		} catch (DocumentException | IOException e) {
			mensaje = "Error al crear PDF";
		}
	}

	public void creaExcel() {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Asignacion provisional");

		Map<String, Object[]> datos = new TreeMap<String, Object[]>();
		datos.put("1", new Object[] { "Expediente", "DNI", "Nombre", "Curso", "Asignatura", "Grupo" });
		int x = 2;
		for (Asigna_grupos ag : ga.listaAsignacionProvisional()) {
			datos.put(Integer.toString(x),
					new Object[] { ag.getMatricula().getExpediente().getNumero().toString(),
							ag.getMatricula().getExpediente().getAlumno().getDni(),
							ag.getMatricula().getExpediente().getAlumno().getNombre_completo(),
							ag.getAsignatura().getCurso().toString(), ag.getAsignatura().getCodigo().toString(),
							ag.getGrupo().getLetra() });
			x++;
		}

		Set<String> keyset = datos.keySet();
		int numeroRenglon = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(numeroRenglon++);
			Object[] arregloObjetos = datos.get(key);
			int numeroCelda = 0;
			for (Object obj : arregloObjetos) {
				Cell cell = row.createCell(numeroCelda++);
				if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else if (obj instanceof Integer) {
					cell.setCellValue((Integer) obj);
				}
			}
		}
		try {
			String filename = new SimpleDateFormat("'asignacionProvisional'yyyy-dd-MM-HHmmss'.xlsx'")
					.format(new Date());
			FileOutputStream out = new FileOutputStream(new File("/opt/jboss/" + filename));
			workbook.write(out);
			out.close();
			workbook.close();

			File file = new File("/opt/jboss/" + filename);
			String fileName = file.getName();
			int contentLength = (int) file.length();

			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext ec = ctx.getExternalContext();

			ec.responseReset();
			ec.setResponseContentType("application/vnd.ms-excel");
			ec.setResponseContentLength(contentLength);
			ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

			java.io.OutputStream output = ec.getResponseOutputStream();
			Files.copy(file.toPath(), output);

			ctx.responseComplete();

			Files.delete(file.toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
