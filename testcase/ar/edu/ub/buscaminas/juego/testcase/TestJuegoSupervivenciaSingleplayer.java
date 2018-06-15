package ar.edu.ub.buscaminas.juego.testcase;

import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.listener.JuegoListener;
import ar.edu.ub.buscaminas.menu.MenuSinglePlayer;
import ar.edu.ub.buscaminas.menu.MenuSinglePlayer.DificultadesSinglePlayer;

import java.util.Collection;

import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.excepciones.TableroException;
import ar.edu.ub.buscaminas.juego.Juego;
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaSingleplayer;
import ar.edu.ub.buscaminas.tablero.Tablero;
import ar.edu.ub.buscaminas.tablero.TableroPrueba;
import junit.framework.TestCase;

public class TestJuegoSupervivenciaSingleplayer extends TestCase {
	
	//TODO mover a un paquete de testing
	class JuegoListenerPrueba implements JuegoListener{
		private boolean hayGanador;
		private boolean hayPerdedor;
		private boolean hayEmpate;
		private boolean hayCambioTurno;
		
		public JuegoListenerPrueba() {
			this.setHayGanador(false);
			this.setHayPerdedor(false);
			this.setHayEmpate(false);
			this.setHayCambioTurno(false);
		}

		@Override
		public void mostrarGanador(Jugador jugador) {
			this.setHayGanador(true);			
		}

		@Override
		public void mostrarPerdedor(Jugador jugador) {
			this.setHayPerdedor(true);			
		}

		@Override
		public void mostrarEmpate(Collection<Jugador> jugadores) {
			this.setHayEmpate( true );
			
		}

		@Override
		public void pedirCambioDeTurno() {
			this.setHayCambioTurno(true);			
		}

		public boolean isHayEmpate() {
			return hayEmpate;
		}

		private void setHayEmpate(boolean hayEmpate) {
			this.hayEmpate = hayEmpate;
		}

		public boolean isHayPerdedor() {
			return hayPerdedor;
		}

		private void setHayPerdedor(boolean hayPerdedor) {
			this.hayPerdedor = hayPerdedor;
		}

		public boolean isHayGanador() {
			return hayGanador;
		}

		private void setHayGanador(boolean hayGanador) {
			this.hayGanador = hayGanador;
		}

		public boolean isHayCambioTurno() {
			return hayCambioTurno;
		}

		public void setHayCambioTurno(boolean hayCambioTurno) {
			this.hayCambioTurno = hayCambioTurno;
		}
		
	}

	public void testPerderPartida() {
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
