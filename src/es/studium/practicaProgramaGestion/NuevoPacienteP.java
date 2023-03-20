package es.studium.practicaProgramaGestion;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NuevoPacienteP implements ActionListener, WindowListener {

	Frame ventanaAltaPaciente = new Frame("Nuevo Paciente");

	Label tituloAltaLbl = new Label("------ Alta de Paciente ------");
	Label nombreLbl = new Label("Nombre:");
	Label dniLbl = new Label("DNI:");
	Label edadLbl = new Label("Edad:");
	Label fechaInicioTratamientoLbl = new Label("Fecha inicio tratamiento:");
	Label formatoFechaLbl = new Label("(dd-mm-AAAA)");
	
	TextField txtNombre = new TextField(20);
	TextField txtDNI = new TextField(20);
	TextField txtEdad = new TextField(20);
	TextField txtFechaInicioTratamiento = new TextField(20);

	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");

	Dialog dlgMensaje = new Dialog(ventanaAltaPaciente, "mensaje", true);
	Label lblMensaje = new Label("Alta correcta");

	ConexionP conexion = new ConexionP();

	NuevoPacienteP() {

		ventanaAltaPaciente.setSize(220, 350);
		ventanaAltaPaciente.setResizable(false);
		ventanaAltaPaciente.setLocationRelativeTo(null);
		ventanaAltaPaciente.setLayout(new FlowLayout());

		ventanaAltaPaciente.addWindowListener(this);

		ventanaAltaPaciente.add(tituloAltaLbl);
		ventanaAltaPaciente.add(nombreLbl);
		ventanaAltaPaciente.add(txtNombre);
		ventanaAltaPaciente.add(dniLbl);
		ventanaAltaPaciente.add(txtDNI);
		ventanaAltaPaciente.add(edadLbl);
		ventanaAltaPaciente.add(txtEdad);
		ventanaAltaPaciente.add(fechaInicioTratamientoLbl);
		ventanaAltaPaciente.add(formatoFechaLbl);
		ventanaAltaPaciente.add(txtFechaInicioTratamiento);
		ventanaAltaPaciente.add(btnAceptar);
		ventanaAltaPaciente.add(btnCancelar);

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		ventanaAltaPaciente.setVisible(true);

	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (dlgMensaje.isActive()) {
			dlgMensaje.setVisible(false);
		} else {
			ventanaAltaPaciente.setVisible(false);
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

			if (txtNombre.getText().equals("") || txtDNI.getText().equals("") || txtEdad.getText().equals("")
					|| txtFechaInicioTratamiento.getText().equals("")) {

				lblMensaje.setText("No se pueden dejar campos vacíos.");

			} else {
				
				String fechaFormateada = conexion.fechaFormatToAnglosajon(txtFechaInicioTratamiento.getText());
				
				String sentencia = "insert into pacientes values (null, '" + txtNombre.getText() + "', '"
						+ txtDNI.getText() + "', '" + txtEdad.getText() + "', '" + fechaFormateada + "');";
				int respuesta = conexion.altaPaciente(sentencia);

				if (respuesta != 0) {
					lblMensaje.setText("Error en Alta.");
				}
			}
			
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(250, 200);
			dlgMensaje.add(lblMensaje);
			dlgMensaje.addWindowListener(this);
			dlgMensaje.setVisible(true);

		} else if (e.getSource().equals(btnCancelar)) {

			txtNombre.setText("");
			txtDNI.setText("");
			txtEdad.setText("");
			txtFechaInicioTratamiento.setText("");
			txtNombre.requestFocus();
		}
	}
	
	

}
