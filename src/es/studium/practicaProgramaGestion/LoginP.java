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

public class LoginP implements WindowListener, ActionListener {

	Frame ventanaLogin = new Frame("Login");
	Dialog dlgMensaje = new Dialog(ventanaLogin, "Error", true);

	Label lblUsuario = new Label("Usuario");
	Label lblClave = new Label("Clave");
	Label lblMensaje = new Label("Credenciales incorrectas");

	TextField txtUsuario = new TextField(10);
	TextField txtClave = new TextField(10);

	Button btnAcceder = new Button("Acceder");

	ConexionP conexion = new ConexionP();

	int tipoUsuario;

	LoginP() {

		ventanaLogin.setLayout(new FlowLayout());
		ventanaLogin.addWindowListener(this);
		btnAcceder.addActionListener(this);

		ventanaLogin.add(lblUsuario);
		ventanaLogin.add(txtUsuario);
		ventanaLogin.add(lblClave);
		ventanaLogin.add(txtClave);
		ventanaLogin.add(btnAcceder);

		txtClave.setEchoChar('*'); // Esto sirve para cambiar cada letra de la contraseña por '*'.

		ventanaLogin.setSize(220, 150);
		ventanaLogin.setResizable(false);
		ventanaLogin.setLocationRelativeTo(null);

		ventanaLogin.setVisible(true);
	}

	public static void main(String[] args) {

		new LoginP();
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {

		if (dlgMensaje.isActive()) {
			dlgMensaje.setVisible(false);
		} else {
			System.exit(0);
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

		String usuario= txtUsuario.getText();
		String clave = txtClave.getText();
		
		tipoUsuario = conexion.comprobarCredenciales(usuario, clave);
		
		if (tipoUsuario != -1) {
			
			new MenuPrincipalP(tipoUsuario);
			ventanaLogin.setVisible(false);
			
		} else {
			dlgMensaje.add(lblMensaje);
			dlgMensaje.addWindowListener(this);
			dlgMensaje.setSize(210,80);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			
			dlgMensaje.setVisible(true);
			
		}

	}

}
