package ar.edu.ub.buscaminas.juego.testcase;

import java.util.ArrayList;
import java.util.List;

import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.juego.Juego;
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaMultiplayer;
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaSingleplayer;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.tablero.Tablero;
import ar.edu.ub.buscaminas.tablero.TableroPrueba;
import ar.edu.ub.buscaminas.testing.listenerprueba.JuegoListenerPrueba;
import junit.framework.TestCase;

public class TestJuegoSupervivenciaMultiplayer extends TestCase {

	
	public void testElegirCasillaBlanco (){
		
		Jugador jugador = new Jugador("simba");
		Jugador jugador2 = new Jugador("pumba");

		List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(jugador);
		jugadores.add(jugador2);

		Tablero tablero = new TableroPrueba();
		Juego juego = new JuegoSupervivenciaMultiplayer(tablero, jugadores);
		JuegoListenerPrueba listener = new JuegoListenerPrueba();
		
		juego.setListener(listener);
		
		try {
			juego.elegirCasilla( new Coordenada(1,1) );
		} catch (CoordenadaInvalidaException e) {
		}

		
		assertFalse( listener.isHayPerdedor() );
		assertFalse(listener.isHayGanador() );
		assertFalse( listener.isHayEmpate() );
		assertFalse(listener.isHayCambioTurno());
	
	}
	
	/*
	public void testElegirCasillaBomba (){
		
		Jugador jugador = new Jugador("simba");
		Jugador jugador2 = new Jugador("pumba");

		List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(jugador);
		jugadores.add(jugador2);

		Tablero tablero = new TableroPrueba();
		Juego juego = new JuegoSupervivenciaMultiplayer(tablero, jugadores);
		JuegoListenerPrueba listener = new JuegoListenerPrueba();
		
		juego.setListener(listener);
		

		try {
			juego.elegirCasilla( new Coordenada(0,0) );
		} catch (CoordenadaInvalidaException e) {
		}

		
		assertTrue( listener.isHayPerdedor() );
		assertTrue(listener.isHayGanador() );
		assertFalse( listener.isHayEmpate() );
		assertFalse(listener.isHayCambioTurno());
	
	}
	*/
}
