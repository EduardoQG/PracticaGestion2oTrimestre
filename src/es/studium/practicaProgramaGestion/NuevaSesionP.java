package es.studium.practicaProgramaGestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NuevaSesionP implements ActionListener, WindowListener {

	Frame ventana = new Frame("Nueva Sesión");

	Label lblTitulo = new Label("---- Nueva Sesión ----");
	Label lblFecha = new Label("Fecha (dd-mm-AAAA):");
	TextField txtFecha = new TextField(20);
	Label lblPrecio = new Label("Precio Sesión (€):");
	TextField txtPrecio = new TextField(20);
	Label lblPagada = new Label("----- Pagada:");
	Choice choPagada = new Choice();
	Label lblPsicologo = new Label("----- Psicólogo:");
	Choice choPsicologo = new Choice();
	Label lblPaciente = new Label("----- Paciente:");
	Choice choPaciente = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");

	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	Label lblMensajeDlg = new Label();

	ConexionP conexion = new ConexionP();

	public NuevaSesionP() {

		ventana.setSize(220, 350);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new FlowLayout());

		ventana.add(lblTitulo);
		ventana.add(lblFecha);
		ventana.add(txtFecha);
		ventana.add(lblPrecio);
		ventana.add(txtPrecio);
		ventana.add(lblPagada);
		ventana.add(choPagada);
		choPagada.add("Sí");
		choPagada.add("No");
		ventana.add(lblPsicologo);
		ventana.add(choPsicologo);
		choPsicologo.add("Elegir:");
		conexion.rellenarChoPsicologo(choPsicologo);
		ventana.add(lblPaciente);
		ventana.add(choPaciente);
		choPaciente.add("Elegir:");
		conexion.rellenarChoicePacientes(choPaciente);
		ventana.add(btnAceptar);
		ventana.add(btnCancelar);

		ventana.addWindowListener(this);
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		ventana.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {

		if (dlgMensaje.isActive()) {
			dlgMensaje.setVisible(false);
		} else {
			ventana.setVisible(false);
		}
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

		if (e.getSource().equals(btnAceptar)) {

			if (txtFecha.getText().equals("") || txtPrecio.getText().equals("") || choPsicologo.getSelectedIndex() == 0
					|| choPaciente.getSelectedIndex() == 0) {

				lblMensajeDlg.setText("No se pueden dejar campos vacíos.");

			} else {
				String sentencia;
				int pagada = 0;
				if (choPagada.getSelectedIndex() == 1) { pagada = 1;} else { pagada = 0;}
				String tabla[] = choPsicologo.getSelectedItem().split("-");
				String idPsicologoFK = tabla[0];
				String tabla2[] = choPaciente.getSelectedItem().split("-");
				String idPacienteFK = tabla2[0];
				String fechaFormateada = conexion.fechaFormatToAnglosajon(txtFecha.getText());		
				
				
				sentencia = "insert into sesiones values (null, '" + fechaFormateada + "', '" + 
				txtPrecio.getText() + "', " + pagada + ", " + idPsicologoFK + ", " + idPacienteFK + ");";

				int respuesta = conexion.darDeAlta(sentencia);

				if (respuesta != 0) {
					lblMensajeDlg.setText("Error en Alta.");
				} else {
					lblMensajeDlg.setText("Alta Correcta.");
				}
			}

			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(250, 200);
			dlgMensaje.add(lblMensajeDlg);
			dlgMensaje.addWindowListener(this);
			dlgMensaje.setVisible(true);

		} else if (e.getSource().equals(btnCancelar)) {
			txtFecha.setText("");
			txtPrecio.setText("");
			choPagada.select(0);
			choPsicologo.select(0);
			choPaciente.select(0);
		}
	}

}
