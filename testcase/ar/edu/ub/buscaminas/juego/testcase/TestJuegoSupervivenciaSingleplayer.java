package ar.edu.ub.buscaminas.juego.testcase;

import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.menu.MenuSinglePlayer;
import ar.edu.ub.buscaminas.menu.MenuSinglePlayer.DificultadesSinglePlayer;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.excepciones.TableroException;
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaSingleplayer;
import ar.edu.ub.buscaminas.tablero.Tablero;
import junit.framework.TestCase;

public class TestJuegoSupervivenciaSingleplayer extends TestCase {

	
	public void testCrearJuegoSupervivenciaSingleplayer() {
		
		
		Jugador jugador = new Jugador("simba");
		String pathMapa = "./mapas/cs_assaut.mapa";
		String dificultad = "FACIL";
	
		
		
		Tablero tablero = new Tablero();
		try {
			tablero.loadFromFile( pathMapa, DificultadesSinglePlayer.FACIL.getPorcentajeBombas());
		} catch (TableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean pudoCrearPartida = false;
		
		try {
			new JuegoSupervivenciaSingleplayer(tablero,dificultad , jugador);
			pudoCrearPartida = true;
		}catch(JuegoException e) {
			pudoCrearPartida = false;
		}
		
		assertTrue(pudoCrearPartida);
	
		
		
	}
	
	public void testCrearJuegoSupervivenciaSinglePlayerDificultadNull() {
		
		
		Jugador jugador = new Jugador("simba");
		String pathMapa = "./mapas/cs_assaut.mapa";
		String dificultad = null;
	
		
		
		Tablero tablero = new Tablero();
		try {
			tablero.loadFromFile( pathMapa, DificultadesSinglePlayer.FACIL.getPorcentajeBombas());
		} catch (TableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean pudoCrearPartida = false;
		
		try {
			new JuegoSupervivenciaSingleplayer(tablero,dificultad , jugador);
			pudoCrearPartida = true;
		}catch(JuegoException e) {
			pudoCrearPartida = false;
		}
		
		assertFalse(pudoCrearPartida);
	}
	
	public void testCrearJuegoSupervivenciaSinglePlayerDificultadVacio() {
		
		
		Jugador jugador = new Jugador("simba");
		String pathMapa = "./mapas/cs_assaut.mapa";
		String dificultad = "";
	
		
		
		Tablero tablero = new Tablero();
		try {
			tablero.loadFromFile( pathMapa, DificultadesSinglePlayer.FACIL.getPorcentajeBombas());
		} catch (TableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean pudoCrearPartida = false;
		
		try {
			new JuegoSupervivenciaSingleplayer(tablero,dificultad , jugador);
			pudoCrearPartida = true;
		}catch(JuegoException e) {
			pudoCrearPartida = false;
		}
		
		assertFalse(pudoCrearPartida);
	}
	
	public void testJuegoSupervivenciaSinglePlayerGetNombreDificultad() {
		
		Jugador jugador = new Jugador("simba");
		String pathMapa = "./mapas/cs_assaut.mapa";
		String dificultad = "MEDIO";
	
		
		
		Tablero tablero = new Tablero();
		try {
			tablero.loadFromFile( pathMapa, DificultadesSinglePlayer.FACIL.getPorcentajeBombas());
		} catch (TableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JuegoSupervivenciaSingleplayer juego = 	new JuegoSupervivenciaSingleplayer(tablero,dificultad , jugador);
	
		
		assertEquals(dificultad, juego.getNombreDificultad());
	}
	/*
	public void testJuegoSupervivenciaSinglePlayerElegiCasillaBomba() {
		Jugador jugador = new Jugador("simba");
		String pathMapa = "./mapas/cs_assaut.mapa";
		String dificultad = "MEDIO";
	
		
		
		Tablero tablero = new Tablero();
		try {
			tablero.loadFromFile( pathMapa, DificultadesSinglePlayer.FACIL.getPorcentajeBombas());
		} catch (TableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JuegoSupervivenciaSingleplayer juego = 	new JuegoSupervivenciaSingleplayer(tablero,dificultad , jugador);
	
		CasillaBomba bomba = new CasillaBomba(new Coordenada(4,4));
		
		//juego.elegiCasilla(bomba);
	}
	
	*/
	
	public void testTerminoJuegoSinglePlayerJugadorVivo() {
		Jugador jugador = new Jugador("simba");
		String pathMapa = "./mapas/cs_assaut.mapa";
		String dificultad = "MEDIO";
	
		
		
		Tablero tablero = new Tablero();
		try {
			tablero.loadFromFile( pathMapa, DificultadesSinglePlayer.FACIL.getPorcentajeBombas());
		} catch (TableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JuegoSupervivenciaSingleplayer juego = 	new JuegoSupervivenciaSingleplayer(tablero,dificultad , jugador);
		
		assertFalse(juego.terminoJuego());
	}
	
	public void testTerminoJuegoSinglePlayerJugadorMuerto() {
		Jugador jugador = new Jugador("simba");
		String pathMapa = "./mapas/cs_assaut.mapa";
		String dificultad = "MEDIO";
	
		
		
		Tablero tablero = new Tablero();
		try {
			tablero.loadFromFile( pathMapa, DificultadesSinglePlayer.FACIL.getPorcentajeBombas());
		} catch (TableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JuegoSupervivenciaSingleplayer juego = 	new JuegoSupervivenciaSingleplayer(tablero,dificultad , jugador);
		juego.getJugadores().clear();
		
		assertTrue(juego.terminoJuego());
	}
	/*
	public void testTerminoJuegoSinglePlayerNumeroYBlancosBocaArriba() {
		Jugador jugador = new Jugador("simba");
		String pathMapa = "./mapas/cs_assaut.mapa";
		String dificultad = "MEDIO";
	
		
		
		Tablero tablero = new Tablero();
		try {
			tablero.loadFromFile( pathMapa, DificultadesSinglePlayer.FACIL.getPorcentajeBombas());
		} catch (TableroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JuegoSupervivenciaSingleplayer juego = 	new JuegoSupervivenciaSingleplayer(tablero,dificultad , jugador);
		juego.getTablero().

		
		//assertFalse(juego.terminoJuego());
	}
	*/
}
