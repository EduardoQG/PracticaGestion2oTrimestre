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

public class NuevoPsicologoP implements ActionListener, WindowListener {

	Frame ventana = new Frame("Nuevo Psicólogo");

	Label lblAlta = new Label ("---- Alta de Psicólogo ----");
	Label lblNombre = new Label ("Nombre:");
	TextField txtNombre = new TextField(20);
	Label lblDNI = new Label ("DNI:");
	TextField txtDNI = new TextField(20);
	Label lblSupervisor = new Label("Supervisor:");
	Choice choSupervisor = new Choice();
	Label lblEspecialidad = new Label("Especialidad:");
	TextField txtEspecialidad = new TextField(20);
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	Label lblMensajeDlg = new Label();
	
	ConexionP conexion = new ConexionP();
	
	// Esta variable la uso para guardar la cadena que aparecerá en el insert, dependiendo de lo que se haya seleccionado.
	String choCadena;
	
	public NuevoPsicologoP() {

		ventana.setSize(220, 350);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new FlowLayout());

		ventana.add(lblAlta);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblDNI);
		ventana.add(txtDNI);
		ventana.add(lblSupervisor);
		ventana.add(choSupervisor);
		ventana.add(lblEspecialidad);
		ventana.add(txtEspecialidad);
		ventana.add(btnAceptar);
		ventana.add(btnCancelar);
		
		choSupervisor.add("Ninguno");
		conexion.rellenarChoPsicologo(choSupervisor);
		
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
			
			if (txtNombre.getText().equals("") || txtDNI.getText().equals("") || txtEspecialidad.getText().equals("")) {

				lblMensajeDlg.setText("No se pueden dejar campos vacíos.");

			} else {
				String sentencia;
				
				if (choSupervisor.getSelectedIndex() == 0) {
					sentencia = "insert into psicologos values (null, '" + txtNombre.getText() + "', '"
							+ txtDNI.getText() + "', '" + txtEspecialidad.getText() + "', null);";
					
				} else {
					sentencia = "insert into psicologos values (null, '" + txtNombre.getText() + "', '"
							+ txtDNI.getText() + "', '" + txtEspecialidad.getText() + "', " + choSupervisor.getSelectedIndex() + ");";
				}
				
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
			txtNombre.setText("");
			txtDNI.setText("");
			txtEspecialidad.setText("");
			choSupervisor.select(0);
			txtNombre.requestFocus();
		}
	}

}
