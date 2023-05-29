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
	
	MenuItem psiItemNuevo = new MenuItem("Nuevo");
	MenuItem psiItemListado = new MenuItem("Listado");

	MenuItem sesItemNuevo = new MenuItem("Nuevo");
	MenuItem sesItemListado = new MenuItem("Listado");
	
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
		menuPsicologos.add(psiItemNuevo);
		menuSesiones.add(sesItemNuevo);
		
		if(tipoUsuario == 0) {
			menuPacientes.add(pacItemListado);
			menuPacientes.add(pacItemBaja);
			menuPacientes.add(pacItemModificar);
			
			menuPsicologos.add(psiItemListado);
			
			menuSesiones.add(sesItemListado);
		}
		
		pacItemNuevo.addActionListener(this);
		pacItemListado.addActionListener(this);
		pacItemBaja.addActionListener(this);
		pacItemModificar.addActionListener(this);
		psiItemNuevo.addActionListener(this);
		psiItemListado.addActionListener(this);
		sesItemNuevo.addActionListener(this);
		sesItemListado.addActionListener(this);

		ventanaMenu.setVisible(true);
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		conexion.actualizarLog("Ha salido.");
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

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
		
		else if (e.getSource().equals(psiItemNuevo)) {
			new NuevoPsicologoP();
		}
		
		else if (e.getSource().equals(psiItemListado)) {
			new ListadoPsicologosP();
		}
		
		else if (e.getSource().equals(sesItemNuevo)) {
			new NuevaSesionP();
		}
		
		else if (e.getSource().equals(sesItemListado)) {
			new ListadoSesionesP();
		}
	}
}
	
	
