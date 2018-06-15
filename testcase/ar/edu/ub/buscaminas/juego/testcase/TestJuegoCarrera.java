package ar.edu.ub.buscaminas.juego.testcase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.excepciones.TableroException;
import ar.edu.ub.buscaminas.juego.JuegoCarrera;
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaSingleplayer;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.menu.MenuSinglePlayer.DificultadesSinglePlayer;
import ar.edu.ub.buscaminas.tablero.ITablero;
import ar.edu.ub.buscaminas.tablero.Tablero;
import ar.edu.ub.buscaminas.tablero.TableroCarrera;
import junit.framework.TestCase;

public class TestJuegoCarrera extends TestCase {

	public void testCrearJuegoCarrera() {
		
		
		Jugador jugador = new Jugador("simba");
		Jugador jugador2 = new Jugador("pumba");
		Jugador jugador3 = new Jugador("simba");
		Jugador jugador4 = new Jugador("simba");
		
		List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(jugador);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		jugadores.add(jugador4);


		ITablero tablero = 	TableroCarrera.crearTableroPartidaCorta();	
		
		
		boolean pudoCrearPartida = false;
		
		try {
			new JuegoCarrera(tablero,jugadores);
			pudoCrearPartida = true;
		}catch(JuegoException e) {
			pudoCrearPartida = false;
		}
		
		assertTrue(pudoCrearPartida);
	
		
		
	}
}
