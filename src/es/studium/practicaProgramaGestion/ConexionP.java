package es.studium.practicaProgramaGestion;

import java.awt.Choice;
import java.awt.TextArea;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ConexionP {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/practicagestion";
	String login = "root";
	String password = "Studium2022;";
	String sentencia = "";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	static String nombreUsuario;
	
	ConexionP() {

		connection = this.conectar();
	}

	public Connection conectar() {

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, login, password);
			return (DriverManager.getConnection(url, login, password));

		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1-" + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Error 2-" + sqle.getMessage());
		}
		return null;
	}

	public int comprobarCredenciales(String u, String p) {

		String cadena = "select * from usuarios where nombreUsuario = '" + u + "' and claveUsuario = SHA2('" + p
				+ "', 256);";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(cadena);

			if (rs.next()) {
				setNombreUsuario(rs.getString("nombreUsuario"));
				actualizarLog("Se ha logueado.");
				return rs.getInt("tipoUsuario");
			} else {
				return -1;
			}
		} catch (SQLException sqle) {
			System.out.println("Error 3-" + sqle.getMessage());
		}

		return -1;
	}

	public int altaPaciente(String sentencia) {

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			actualizarLog(sentencia);
			return 0;
		} catch (SQLException sqle) {
			System.out.println("Error 2-" + sqle.getMessage());
			return 1;
		}
	}

	public void rellenarListaPacientes(TextArea listaPacientes) {

		String sentencia = "select idPaciente, nombrePaciente, DNIPaciente, edadPaciente, fechaInicioTratamiento from practicagestion.pacientes";

		try {

			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			
			actualizarLog(sentencia);
			
			while (resultado.next()) {
				String fechaFormateada=fechaFormatToEspanyol(resultado.getString("fechaInicioTratamiento"));
				listaPacientes.append(resultado.getString("idPaciente") + " ");
				listaPacientes.append(resultado.getString("nombrePaciente") + " ");
				listaPacientes.append(resultado.getString("DNIPaciente") + " ");
				listaPacientes.append(resultado.getString("edadPaciente") + " ");
				listaPacientes.append(fechaFormateada + "\n");
			}
		} catch (SQLException sqle) {
			System.out.println("Error 5-" + sqle.getMessage());
		}
	}

	public void rellenarChoicePacientes(Choice pacienteChoice) {

		String sentencia = "select idPaciente, nombrePaciente from pacientes order by 1;";

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);

			pacienteChoice.add("Elige un usuario:");

			while (resultado.next()) {
				pacienteChoice.add(resultado.getString("idPaciente") + "-" + resultado.getString("nombrePaciente"));
			}
		} catch (SQLException sqle) {
			System.out.println("Error 5-" + sqle.getMessage());
		}
	}

	public int eliminarPaciente(String idPaciente) {

		String sentencia = "delete from pacientes where idPaciente = " + idPaciente;

		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			actualizarLog(sentencia);
			return 0;

		} catch (SQLException sqle) {
			System.out.println("Error 7-" + sqle.getMessage());
			return 1;
		}
	}

	public String getDatosEdicion(String idPaciente) {

		String resultado = "";
		String sentencia = "SELECT * FROM pacientes WHERE idPaciente = " + idPaciente;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet resultSet = statement.executeQuery(sentencia);
			resultSet.next();
			resultado = (resultSet.getString("idPaciente") + "/" + resultSet.getString("nombrePaciente") + "/"
					+ resultSet.getString("DNIPaciente") + "/" + resultSet.getString("edadPaciente") + "/"
					+ resultSet.getString("fechaInicioTratamiento"));

		} catch (SQLException sqle) {
			System.out.println("Error 8-" + sqle.getMessage());
		}
		return resultado;
	}

	public int modificarPaciente(String sentencia) {
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			actualizarLog(sentencia);
			return 0;
		} catch (SQLException sqle) {
			System.out.println("Error 9-" + sqle.getMessage());
			return 1;
		}
	}

	public String fechaFormatToAnglosajon(String fecha){

		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		String fechaNueva = formatoFecha.format(date1);
		return fechaNueva;
	}
	
	public String fechaFormatToEspanyol (String fecha) {
		
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		String fechaNueva = formatoFecha.format(date1);
		return fechaNueva;
	}
	
	// Método para guardar en nombreUsuario el nombre del usuario que se registre.
	public void setNombreUsuario (String nombreUsuario) {
		ConexionP.nombreUsuario = nombreUsuario;
	}
	
	public void actualizarLog (String cadena) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String ahora = dtf.format(now);
		
		// Se crea una cadena que incluye el momento actual, el nombre de usuario y la cadena
		// que se le pasa por parámetros (que es la sentencia en la mayoría de casos).
		// Esta cadena final se imprime en el fichero .log.
		String cadenaFinal = "[" + ahora + "][" + nombreUsuario + "]" + cadena;
	
		try {
			FileWriter fw = new FileWriter("registro.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(cadenaFinal);
			
			pw.close(); bw.close(); fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
