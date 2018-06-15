package ar.edu.ub.buscaminas.record.testcase;


import ar.edu.ub.buscaminas.excepciones.RecordJuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.record.RecordJuego;
import ar.edu.ub.buscaminas.record.RecordJuegoConquista;
import junit.framework.TestCase;

public class TestRecordJuegoConquista extends TestCase {
	
	public void testCrearRecord() {
		int cantidadBomba = 5;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		
		boolean pudoCrear = false;
		
		try {
			RecordJuego record = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba);
			pudoCrear = true;
		}catch(RecordJuegoException e) {
			pudoCrear = false;
		}
		
		assertTrue(pudoCrear);
	}
	
	public void testCrearRecordConMenor0Bombas() {
		int cantidadBomba = -5;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		
		boolean pudoCrear = false;
		
		try {
			RecordJuego record = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba);
			pudoCrear = true;
		}catch(RecordJuegoException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	
	public void testGetCantidadBombas() {
		int cantidadBomba = 7;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		
		RecordJuegoConquista record = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba);
		

		assertEquals(cantidadBomba, record.getCantidadBombas());
	}
	
	public void testCompareRecordMenor() {
		int cantidadBomba = 7;
		int cantidadBomba2 = 9;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		
		RecordJuegoConquista record = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba);
		RecordJuegoConquista record2 = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba2);
		
		assertEquals(2, record.compareTo(record2));
	}
	
	public void testCompareRecordIgual() {
		int cantidadBomba = 7;
		int cantidadBomba2 = 7;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		
		RecordJuegoConquista record = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba);
		RecordJuegoConquista record2 = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba2);
		
		assertEquals(0, record.compareTo(record2));
		
	}
	
	public void testCompareRecordMayor() {
		int cantidadBomba = 11;
		int cantidadBomba2 = 9;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		
		RecordJuegoConquista record = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba);
		RecordJuegoConquista record2 = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba2);
		
		assertEquals(-2, record.compareTo(record2));

	}
	
	public void testToStringRecord () {
		
		int cantidadBomba = 7;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		
		RecordJuegoConquista record = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba);
		String tostring = "Conquista - fdsa - asd - 7 bombas";
		
		assertEquals(tostring, record.toString());
		
	}

}
