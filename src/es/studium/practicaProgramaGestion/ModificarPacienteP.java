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

public class ModificarPacienteP implements WindowListener, ActionListener {

	Frame ventanaEditar = new Frame("Editar Paciente");
	Frame ventanaEditar2 = new Frame("Editar Paciente");

	Dialog dlgMensaje = new Dialog(ventanaEditar2, "Mensaje", true);

	ConexionP conexion = new ConexionP();

	// Objetos ventana 1:
	Label lblElegir = new Label("Elegir el paciente a Editar:");
	Choice pacienteChoice = new Choice();
	Button btnEditar = new Button("Editar");

	// Objetos ventana 2:
	Label modificarLbl = new Label(" --------------- Editar Paciente -------------- ");
	Label nombreLbl = new Label("Nombre:");
	Label DNILbl = new Label("DNI:");
	Label edadLbl = new Label("Edad:");
	Label fechaInicioTratamientoLbl = new Label("Inicio Tratamiento:");
	Label formatoFechaLbl = new Label("(dd-mm-AAAA)");

	TextField txtNombre = new TextField(20);
	TextField txtDNI = new TextField(20);
	TextField txtEdad = new TextField(20);
	TextField txtFechaInicioTratamiento = new TextField(20);

	Button btnModificar = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");

	// Objetos dialog:
	Label lblMensaje = new Label("Modificación de Paciente correcta.");

	String idPaciente = "";
	// El uso de esta variable se encuentra en el ActionListener.

	ModificarPacienteP() {

		// Ventana 1:
		ventanaEditar.setSize(300, 225);
		ventanaEditar.setResizable(false);
		ventanaEditar.setLocationRelativeTo(null);
		ventanaEditar.setLayout(new FlowLayout());

		ventanaEditar.addWindowListener(this);
		conexion.rellenarChoicePacientes(pacienteChoice);

		btnEditar.addActionListener(this);
		ventanaEditar.add(lblElegir);
		ventanaEditar.add(pacienteChoice);
		ventanaEditar.add(btnEditar);

		ventanaEditar.setVisible(true);

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {

		if (dlgMensaje.isActive()) {
			dlgMensaje.setVisible(false);
			ventanaEditar2.setVisible(false);
			ventanaEditar.setVisible(false);

		} else if (ventanaEditar2.isActive()) {
			ventanaEditar2.setVisible(false);
			ventanaEditar.setVisible(false);
		} else {
			ventanaEditar.setVisible(false);
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnEditar)) {

			if (pacienteChoice.getSelectedIndex() != 0) {

				// Ventana 2:
				ventanaEditar2.setSize(220, 350);
				ventanaEditar2.setResizable(false);
				ventanaEditar2.setLocationRelativeTo(null);
				ventanaEditar2.setLayout(new FlowLayout());

				ventanaEditar2.addWindowListener(this);

				ventanaEditar2.add(modificarLbl);
				ventanaEditar2.add(nombreLbl);
				ventanaEditar2.add(txtNombre);
				ventanaEditar2.add(DNILbl);
				ventanaEditar2.add(txtDNI);
				ventanaEditar2.add(edadLbl);
				ventanaEditar2.add(txtEdad);
				ventanaEditar2.add(fechaInicioTratamientoLbl);
				ventanaEditar2.add(formatoFechaLbl);
				ventanaEditar2.add(txtFechaInicioTratamiento);
				ventanaEditar2.add(btnModificar);
				ventanaEditar2.add(btnCancelar);

				btnModificar.addActionListener(this);
				btnCancelar.addActionListener(this);

				String tabla[] = pacienteChoice.getSelectedItem().split("-");

				String resultado = conexion.getDatosEdicion(tabla[0]);

				String datos[] = resultado.split("/");
				String fechaFormateada = conexion.fechaFormatToEspanyol(datos[4]);
				idPaciente = datos[0];
				txtNombre.setText(datos[1]);
				txtDNI.setText(datos[2]);
				txtEdad.setText(datos[3]);
				txtFechaInicioTratamiento.setText(fechaFormateada);

				ventanaEditar2.setVisible(true);
			}

		} else if (e.getSource().equals(btnModificar)) {

			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(220, 100);
			dlgMensaje.addWindowListener(this);

			if (txtNombre.getText().length() == 0 || txtDNI.getText().length() == 0 || txtEdad.getText().length() == 0
					|| txtFechaInicioTratamiento.getText().length() == 0) {
				lblMensaje.setText("Los campos están vacíos.");
			}

			else {
				
				String fechaFormateada = conexion.fechaFormatToAnglosajon(txtFechaInicioTratamiento.getText());     
				
				String sentencia = "UPDATE pacientes SET nombrePaciente='" + txtNombre.getText()
						+ "', DNIPaciente = '" + txtDNI.getText() + "', edadPaciente = '"
						+ txtEdad.getText() + "', fechaInicioTratamiento= '" + fechaFormateada 
						+ "' WHERE idPaciente=" + idPaciente + ";";
				int respuesta = conexion.modificarPaciente(sentencia);

				if (respuesta != 0) {
					lblMensaje.setText("Error en Modificación");
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);

		} else if (e.getSource().equals(btnCancelar)) {
			ventanaEditar2.setVisible(false);
		}

	}
}
