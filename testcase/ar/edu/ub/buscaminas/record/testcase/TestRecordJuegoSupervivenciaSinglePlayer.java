package ar.edu.ub.buscaminas.record.testcase;

import ar.edu.ub.buscaminas.excepciones.RecordJuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.record.RecordJuegoSupervivenciaSingleplayer;
import junit.framework.TestCase;

public class TestRecordJuegoSupervivenciaSinglePlayer extends TestCase {
	

	public void testCrearRecord() {
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		long tiempo = 44444;
		String dificultad = "FACIL";
		
		boolean pudoCrear = false;
		
		try {
			new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo);
			pudoCrear = true;
		}catch(RecordJuegoException e) {
			pudoCrear = false;
		}
		
		assertTrue(pudoCrear);
	}
	
	public void testCrearRecordTiempoMenorA0() {
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		long tiempo = -44444;
		String dificultad = "FACIL";
		
		boolean pudoCrear = false;
		
		try {
			new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo);
			pudoCrear = true;
		}catch(RecordJuegoException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	
	public void testCrearRecordDificultadNull() {
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		long tiempo = -44444;
		String dificultad = null;
		
		boolean pudoCrear = false;
		
		try {
			new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo);
			pudoCrear = true;
		}catch(RecordJuegoException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	
	public void testCrearRecordDificultadVacio() {
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		long tiempo = -44444;
		String dificultad = "";
		
		boolean pudoCrear = false;
		
		try {
			new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo);
			pudoCrear = true;
		}catch(RecordJuegoException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	
	public void testGetTiempo() {

		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		long tiempo = 44444;
		String dificultad = "FACIL";
		
		RecordJuegoSupervivenciaSingleplayer record = new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo);
		

		assertEquals(tiempo, record.getTiempoPartida());
	}
	public void testGetDificultad() {

		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		long tiempo = 44444;
		String dificultad = "FACIL";
		
		RecordJuegoSupervivenciaSingleplayer record = new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo);
		

		assertEquals(dificultad, record.getDificultad());
	}
	
	public void testCompareRecordMenor() {
		long tiempo = 10;
		long tiempo2 = 15;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		String dificultad = "FACIL";
		
		RecordJuegoSupervivenciaSingleplayer record = new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo);
		RecordJuegoSupervivenciaSingleplayer record2 = new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo2);

		assertEquals(-5, record.compareTo(record2));
	}
	
	public void testCompareRecordIgual() {
		long tiempo = 10;
		long tiempo2 = 10;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		String dificultad = "FACIL";
		
		RecordJuegoSupervivenciaSingleplayer record = new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo);
		RecordJuegoSupervivenciaSingleplayer record2 = new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo2);
		
		assertEquals(0, record.compareTo(record2));
		
	}
	
	public void testCompareRecordMayor() {
		long tiempo = 15;
		long tiempo2 = 10;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		String dificultad = "FACIL";
		
		RecordJuegoSupervivenciaSingleplayer record = new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo);
		RecordJuegoSupervivenciaSingleplayer record2 = new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo2);
		
		assertEquals(5, record.compareTo(record2));

	}
	
	
	public void testToStringRecord () {
		
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		long tiempo = 44444;
		String dificultad = "FACIL";
		
		RecordJuegoSupervivenciaSingleplayer record = new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo);
		
		String tostring = "Supervivencia - SinglePlayer - fdsa - FACIL - asd - 44444 segundos";
	

		assertEquals(tostring, record.toString());
		
	}

}
