package es.studium.practicaProgramaGestion;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ListadoSesionesP implements WindowListener, ActionListener {

	Frame ventana = new Frame("Listado Sesiones");

	TextArea listaSesiones = new TextArea(6, 30);
	Button btnPdf = new Button("PDF");

	ConexionP conexion = new ConexionP();

	ListadoSesionesP() {

		ventana.setSize(300, 225);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new FlowLayout());

		ventana.addWindowListener(this);

		ventana.add(listaSesiones);
		ventana.add(btnPdf);
		btnPdf.addActionListener(this);
		
		listaSesiones.append("      Fecha /   Precio   / Pagada / Paciente / Psicólogo\n");

		conexion.rellenarListaSesiones(listaSesiones);

		ventana.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ventana.setVisible(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(btnPdf)) {

			try {
				// Initialize PDF writer
				PdfWriter writer = new PdfWriter("prueba.pdf");
				// Initialize PDF document
				PdfDocument pdf = new PdfDocument(writer);
				// Initialize document
				Document document = new Document(pdf);
				// Add paragraph to the document
				document.add(new Paragraph("LISTA SESIONES\n" + listaSesiones.getText()));
				// Close document
				document.close();
				// Open the new PDF document just created
				Desktop.getDesktop().open(new File("prueba.pdf"));
			
			} catch (IOException ioe) {
			}
		}
		
	}
}
