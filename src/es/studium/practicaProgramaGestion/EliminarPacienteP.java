package es.studium.practicaProgramaGestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EliminarPacienteP implements WindowListener, ActionListener {

Frame ventana = new Frame ("Baja Paciente");
	
	Label introLbl = new Label ("Elegir el paciente a Eliminar.");
	Choice pacienteChoice = new Choice();
	Button btnEliminar = new Button("Eliminar");
	
	Dialog dlgSeguro = new Dialog (ventana, "¿Está segur@?", true);
	Label lblSeguro = new Label();
	Button btnSi = new Button("Sí");
	Button btnNo = new  Button("No");
	
	Dialog dlgMensaje = new Dialog (ventana, "Mensaje", true);
	Label lblMensaje = new Label ("Paciente Eliminado.");
	
	ConexionP conexion = new ConexionP();
	
	EliminarPacienteP () {
		
		ventana.setSize(300, 225);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new FlowLayout());
		
		ventana.addWindowListener(this);
		btnEliminar.addActionListener(this);
		
		ventana.add(introLbl);
		ventana.add(pacienteChoice);
		ventana.add(btnEliminar);
		
		conexion.rellenarChoicePacientes(pacienteChoice);
		
		ventana.setVisible(true);
	}


	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
			if(dlgSeguro.isActive()) {
				dlgSeguro.setVisible(false);
				
			} else if (dlgMensaje.isActive()){
				dlgSeguro.setVisible(false);
				ventana.setVisible(false);
				dlgMensaje.setVisible(false);
				
			} else {
				ventana.setVisible(false);
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
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnEliminar)) {
			
			if(pacienteChoice.getSelectedIndex() != 0) {     
				
				dlgSeguro.setLayout(new FlowLayout());
				dlgSeguro.setSize(170, 100);
				dlgSeguro.addWindowListener(this);
				
				lblSeguro.setText("¿Seguro de eliminar " + pacienteChoice.getSelectedItem() + "?");
				dlgSeguro.add(lblSeguro);
				btnSi.addActionListener(this); btnNo.addActionListener(this);
				dlgSeguro.add(btnSi); dlgSeguro.add(btnNo);
				
				dlgSeguro.setResizable(false); dlgSeguro.setLocationRelativeTo(null);
				dlgSeguro.setVisible(true);
			}
		
		} else if (e.getSource().equals(btnNo)) {
			dlgSeguro.setVisible(false);
			
		} else if (e.getSource().equals(btnSi)) {
			String tabla[] = pacienteChoice.getSelectedItem().split("-");
			int respuesta = conexion.eliminarPaciente(tabla[0]);
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(170, 100);
			dlgMensaje.addWindowListener(this);
			
			if (respuesta == 0) {
				lblMensaje.setText("Usuario Eliminado.");
				
			} else {
				lblMensaje.setText("Error al eliminar.");
			}
			
			dlgMensaje.add(lblMensaje); dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null); dlgMensaje.setVisible(true);	
		} 
	}
}


