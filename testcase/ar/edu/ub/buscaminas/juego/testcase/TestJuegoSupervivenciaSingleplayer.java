package ar.edu.ub.buscaminas.juego.testcase;

import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.menu.MenuSinglePlayer.DificultadesSinglePlayer;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.excepciones.TableroException;
import ar.edu.ub.buscaminas.juego.Juego;
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaSingleplayer;
import ar.edu.ub.buscaminas.tablero.Tablero;
import ar.edu.ub.buscaminas.tablero.TableroPrueba;
import ar.edu.ub.buscaminas.testing.listenerprueba.JuegoListenerPrueba;
import junit.framework.TestCase;

public class TestJuegoSupervivenciaSingleplayer extends TestCase {

	public void testPerderJuego (){
		Tablero tablero = new TableroPrueba();
		Juego juego = new JuegoSupervivenciaSingleplayer(tablero, "FACIL", new Jugador("Walter") );
		JuegoListenerPrueba listener = new JuegoListenerPrueba();
		
		juego.setListener(listener);
		
		//Elijo la bomba fijada en 0,0 en el tablero de prueba
		try {
			juego.elegirCasilla( new Coordenada(0,0) );
		} catch (CoordenadaInvalidaException e) {
		}

		
		assertTrue( listener.isHayPerdedor() );
		assertFalse(listener.isHayGanador() );
		assertFalse( listener.isHayEmpate() );
	
	}
	/*
	public void testElijoCasilleroSinBomba() {
		Tablero tablero = new TableroPrueba();
		Juego juego = new JuegoSupervivenciaSingleplayer(tablero, "FACIL", new Jugador("asd") );
		JuegoListenerPrueba listener = new JuegoListenerPrueba();
		
		juego.setListener(listener);
		System.out.println(listener.isHayPerdedor());
		
		//Elijo la bomba fijada en 0,0 en el tablero de prueba
		try {
			juego.elegirCasilla( new Coordenada(1,1) );
		} catch (CoordenadaInvalidaException e) {
		}
		
		System.out.println(listener.isHayPerdedor());
	}
	*/
	
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
		
		
		Juego juego = 	new JuegoSupervivenciaSingleplayer(tablero,dificultad , jugador);
		
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
