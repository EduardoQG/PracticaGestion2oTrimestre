package es.studium.practicaProgramaGestion;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipalP implements WindowListener, ActionListener {

	Frame ventanaMenu = new Frame("Menú Principal");

	MenuBar menuBar = new MenuBar();
	Menu menuPacientes = new Menu("Pacientes");
	Menu menuPsicologos = new Menu("Psicólogos");
	Menu menuSesiones = new Menu("Sesiones");
	MenuItem pacItemNuevo = new MenuItem("Nuevo");
	MenuItem pacItemListado = new MenuItem("Listado");
	MenuItem pacItemBaja = new MenuItem("Baja");
	MenuItem pacItemModificar = new MenuItem("Modificar");
	
	ConexionP conexion = new ConexionP();
	
	/* 
	(TERCER TRIMESTRE) 
	MenuItem psiItemNuevo = new MenuItem("Nuevo");
	MenuItem psitemListado = new MenuItem("Listado");
	MenuItem psiItemBaja = new MenuItem("Baja");
	MenuItem psiItemModificar = new MenuItem("Modificar");
	MenuItem sesItemNuevo = new MenuItem("Nuevo");
	MenuItem sestemListado = new MenuItem("Listado");
	MenuItem sesItemBaja = new MenuItem("Baja");
	MenuItem sesItemModificar = new MenuItem("Modificar");
	*/
	
	MenuPrincipalP (int t) {

		int tipoUsuario = t;
		
		ventanaMenu.setSize(400, 400);
		ventanaMenu.setResizable(false);
		ventanaMenu.setLocationRelativeTo(null);

		ventanaMenu.addWindowListener(this);

		ventanaMenu.setMenuBar(menuBar);
		menuBar.add(menuPacientes);
		menuBar.add(menuPsicologos);
		menuBar.add(menuSesiones);
		
		menuPacientes.add(pacItemNuevo);
		
		if(tipoUsuario == 0) {
			menuPacientes.add(pacItemListado);
			menuPacientes.add(pacItemBaja);
			menuPacientes.add(pacItemModificar);
		}
		
		pacItemNuevo.addActionListener(this);
		pacItemListado.addActionListener(this);
		pacItemBaja.addActionListener(this);
		pacItemModificar.addActionListener(this);

		ventanaMenu.setVisible(true);
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		conexion.actualizarLog("Ha salido.");
		System.exit(0);
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
	
		if (e.getSource().equals(pacItemNuevo)) {
			new NuevoPacienteP();
		}
		
		else if (e.getSource().equals(pacItemListado)) {
			new ListadoPacientesP();
		}
		
		else if (e.getSource().equals(pacItemBaja)) {
			new EliminarPacienteP();
		}
		
		else if (e.getSource().equals(pacItemModificar)) {
			new ModificarPacienteP();
		} 
	}
}
	
	
