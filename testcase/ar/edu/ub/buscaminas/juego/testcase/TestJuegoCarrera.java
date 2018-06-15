package ar.edu.ub.buscaminas.juego.testcase;

import java.util.ArrayList;

import java.util.List;
import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.juego.JuegoCarrera;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.tablero.ITablero;
import ar.edu.ub.buscaminas.tablero.TableroCarrera;
import junit.framework.TestCase;

public class TestJuegoCarrera extends TestCase {

	public void testCrearJuegoCarreraCorta() {
		
		
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
	
	public void testCrearJuegoCarreraLarga() {
		
		
		Jugador jugador = new Jugador("simba");
		Jugador jugador2 = new Jugador("pumba");
		Jugador jugador3 = new Jugador("simba");
		Jugador jugador4 = new Jugador("simba");
		
		List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(jugador);
		jugadores.add(jugador2);
		jugadores.add(jugador3);
		jugadores.add(jugador4);


		ITablero tablero = 	TableroCarrera.crearTableroPartidaLarga();	
		
		
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
