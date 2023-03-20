package es.studium.practicaProgramaGestion;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ListadoPacientesP implements WindowListener {

	Frame ventana = new Frame("Listado Pacientes");

	TextArea listaPacientes = new TextArea(6, 30);
	Button btnPdf = new Button("PDF");

	ConexionP conexion = new ConexionP();

	ListadoPacientesP() {

		ventana.setSize(300, 225);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new FlowLayout());

		ventana.addWindowListener(this);

		ventana.add(listaPacientes);
		ventana.add(btnPdf);

		conexion.rellenarListaPacientes(listaPacientes);

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
}
