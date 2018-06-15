package ar.edu.ub.buscaminas.record.testcase;

import ar.edu.ub.buscaminas.excepciones.RecordJuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.record.RecordJuegoSupervivenciaMultiplayer;
import junit.framework.TestCase;

public class TestRecordJuegoSupervivenciaMultiplayer extends TestCase {

	
	public void testCrearRecord() {
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		long tiempo = 44444;
		
		boolean pudoCrear = false;
		
		try {
			new RecordJuegoSupervivenciaMultiplayer(nombreMapa, jugador, tiempo);
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
		
		boolean pudoCrear = false;
		
		try {
			 new RecordJuegoSupervivenciaMultiplayer(nombreMapa, jugador, tiempo);
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
		
		RecordJuegoSupervivenciaMultiplayer record = new RecordJuegoSupervivenciaMultiplayer(nombreMapa, jugador, tiempo);
		

		assertEquals(tiempo, record.getTiempoPartida());
	}
	
	public void testCompareRecordMenor() {
		long tiempo = 10;
		long tiempo2 = 15;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		
		RecordJuegoSupervivenciaMultiplayer record = new RecordJuegoSupervivenciaMultiplayer(nombreMapa, jugador, tiempo);
		RecordJuegoSupervivenciaMultiplayer record2 = new RecordJuegoSupervivenciaMultiplayer(nombreMapa, jugador, tiempo2);

		assertEquals(-5, record.compareTo(record2));
	}
	
	public void testCompareRecordIgual() {
		long tiempo = 10;
		long tiempo2 = 10;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		
		RecordJuegoSupervivenciaMultiplayer record = new RecordJuegoSupervivenciaMultiplayer(nombreMapa, jugador, tiempo);
		RecordJuegoSupervivenciaMultiplayer record2 = new RecordJuegoSupervivenciaMultiplayer(nombreMapa, jugador, tiempo2);
		
		assertEquals(0, record.compareTo(record2));
		
	}
	
	public void testCompareRecordMayor() {
		long tiempo = 15;
		long tiempo2 = 10;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		
		RecordJuegoSupervivenciaMultiplayer record = new RecordJuegoSupervivenciaMultiplayer(nombreMapa, jugador, tiempo);
		RecordJuegoSupervivenciaMultiplayer record2 = new RecordJuegoSupervivenciaMultiplayer(nombreMapa, jugador, tiempo2);
		
		assertEquals(5, record.compareTo(record2));

	}
	
	public void testToStringRecord () {
		
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		long tiempo = 44444;
		
		RecordJuegoSupervivenciaMultiplayer record = new RecordJuegoSupervivenciaMultiplayer(nombreMapa, jugador, tiempo);
		
		String tostring = "Supervivencia - Multiplayer - fdsa - asd - 44444 segundos";

		assertEquals(tostring, record.toString());
		
	}
}
