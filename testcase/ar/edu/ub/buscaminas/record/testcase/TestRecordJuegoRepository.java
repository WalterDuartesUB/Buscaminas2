package ar.edu.ub.buscaminas.record.testcase;



import ar.edu.ub.buscaminas.excepciones.RecordJuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.record.RecordJuegoConquista;
import ar.edu.ub.buscaminas.record.RecordJuegoRepository;
import ar.edu.ub.buscaminas.record.RecordJuegoSupervivenciaMultiplayer;
import ar.edu.ub.buscaminas.record.RecordJuegoSupervivenciaSingleplayer;
import junit.framework.TestCase;

public class TestRecordJuegoRepository extends TestCase {
	
	public void testCrearRecord() {
		String path = "./record/records2.rec";
		
		boolean pudoCrear = false;
		
		try {
			new RecordJuegoRepository(path);
			pudoCrear = true;
		}catch(RecordJuegoException e) {
			pudoCrear = false;
		}
		
		assertTrue(pudoCrear);
	}
	
	
	public void testCrearRecordNullConquista() {
		String path = "./record/records2.rec";
		//RecordJuegoConquista record = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba);
		RecordJuegoConquista record = null;
		RecordJuegoRepository repo = new RecordJuegoRepository(path);
		boolean pudoAgregar = false;

		try {
			repo.add(record);
			pudoAgregar = true;
		}catch(RecordJuegoException e) {
			pudoAgregar = false;
		}
		
		assertFalse(pudoAgregar);
	}
	
	public void testAgregarRecordConquista() {
		String path = "./record/records2.rec";
		int cantidadBomba = 5;
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		RecordJuegoConquista record = new RecordJuegoConquista(nombreMapa,jugador,cantidadBomba);
		RecordJuegoRepository repo = new RecordJuegoRepository(path);
		boolean pudoAgregar = false;

		try {
			repo.add(record);
			pudoAgregar = true;
		}catch(RecordJuegoException e) {
			pudoAgregar = false;
		}
		
		assertTrue(pudoAgregar);
	}
	public void testCrearRecordSingle() {
		String path = "./record/records2.rec";
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		long tiempo = 44444;
		String dificultad = "FACIL";
		
		RecordJuegoSupervivenciaSingleplayer record = new RecordJuegoSupervivenciaSingleplayer(nombreMapa,dificultad, jugador, tiempo);
		RecordJuegoRepository repo = new RecordJuegoRepository(path);
		boolean pudoAgregar = false;

		try {
			repo.add(record);
			pudoAgregar = true;
		}catch(RecordJuegoException e) {
			pudoAgregar = false;
		}
		
		assertTrue(pudoAgregar);
	}
	
	public void testAgregarRecordSingleNull() {
		String path = "./record/records2.rec";

		RecordJuegoSupervivenciaSingleplayer record = null;
		RecordJuegoRepository repo = new RecordJuegoRepository(path);
		boolean pudoAgregar = false;

		try {
			repo.add(record);
			pudoAgregar = true;
		}catch(RecordJuegoException e) {
			pudoAgregar = false;
		}
		
		assertFalse(pudoAgregar);
	}
	
	public void testCrearRecordMulti() {
		String path = "./record/records2.rec";
		Jugador jugador = new Jugador("asd");
		String nombreMapa = "fdsa";
		long tiempo = 44444;
		
		RecordJuegoSupervivenciaMultiplayer record = new RecordJuegoSupervivenciaMultiplayer(nombreMapa, jugador, tiempo);
		RecordJuegoRepository repo = new RecordJuegoRepository(path);
		boolean pudoAgregar = false;

		try {
			repo.add(record);
			pudoAgregar = true;
		}catch(RecordJuegoException e) {
			pudoAgregar = false;
		}
		
		assertTrue(pudoAgregar);
	}
	
	public void testAgregarRecordMultiNull() {
		String path = "./record/records2.rec";
		
		RecordJuegoSupervivenciaMultiplayer record = null;
		RecordJuegoRepository repo = new RecordJuegoRepository(path);
		boolean pudoAgregar = false;

		try {
			repo.add(record);
			pudoAgregar = true;
		}catch(RecordJuegoException e) {
			pudoAgregar = false;
		}
		
		assertFalse(pudoAgregar);
	}
	
	

}
