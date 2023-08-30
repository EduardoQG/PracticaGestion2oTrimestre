package es.studium.practicaProgramaGestion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pruebas {

	public static void main(String[] args) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String ahora = dtf.format(now);
		System.out.println(ahora);

	}

}
