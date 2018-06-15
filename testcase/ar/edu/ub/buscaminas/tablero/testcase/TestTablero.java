package ar.edu.ub.buscaminas.tablero.testcase;

import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.TableroException;
import ar.edu.ub.buscaminas.menu.MenuSinglePlayer.DificultadesSinglePlayer;
import ar.edu.ub.buscaminas.tablero.Tablero;
import junit.framework.TestCase;

public class TestTablero extends TestCase {
	
	public void testCrearTablero() {
		
		String pathMapa = "./mapas/cs_assaut.mapa";
		boolean pudoCrear = false;
		try {
			Tablero tablero = new Tablero();
			tablero.loadFromFile( pathMapa, DificultadesSinglePlayer.FACIL.getPorcentajeBombas());
			pudoCrear = true;
		} catch (TableroException e) {
			pudoCrear = false;
		}
		
		assertTrue(pudoCrear);
	}
	
	public void testCrearTableroPathMapNull() {
		
		String pathMapa = null;
		boolean pudoCrear = false;
		try {
			Tablero tablero = new Tablero();
			tablero.loadFromFile( pathMapa, DificultadesSinglePlayer.FACIL.getPorcentajeBombas());
			pudoCrear = true;
		} catch (TableroException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	
	public void testCrearTableroPathMapVacio() {
		
		String pathMapa = "";
		boolean pudoCrear = false;
		try {
			Tablero tablero = new Tablero();
			tablero.loadFromFile( pathMapa, DificultadesSinglePlayer.FACIL.getPorcentajeBombas());
			pudoCrear = true;
		} catch (TableroException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	
	public void testCrearTableroBombasMenor0() {
		
		String pathMapa ="./mapas/cs_assaut.mapa";
		boolean pudoCrear = false;
		try {
			Tablero tablero = new Tablero();
			tablero.loadFromFile( pathMapa, -4);
			pudoCrear = true;
		} catch (TableroException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	
	public void testCrearTableroBombasMayor100() {
		
		String pathMapa ="./mapas/cs_assaut.mapa";
		boolean pudoCrear = false;
		Tablero tablero = new Tablero();
		try {
			tablero.loadFromFile( pathMapa, 110);
			pudoCrear = true;
		} catch (TableroException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	
	public void testCrearTableroMapaDatosInvalidos() {
		
		String pathMapa ="./mapas/test.mapa";
		boolean pudoCrear = false;
		try {
			Tablero tablero = new Tablero();
			tablero.loadFromFile( pathMapa, 50);
			pudoCrear = true;
		} catch (TableroException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	
	public void testCrearTableroMapaInexistente() {
		
		String pathMapa ="./mapas/tes2t.mapa";
		boolean pudoCrear = false;
		try {
			Tablero tablero = new Tablero();
			tablero.loadFromFile( pathMapa, 50);
			pudoCrear = true;
		} catch (TableroException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	
	public void testCrearTableroMapaAnchoMayor100Alto() {
		
		String pathMapa ="./mapas/test2.mapa";
		boolean pudoCrear = false;
		try {
			Tablero tablero = new Tablero();
			tablero.loadFromFile( pathMapa, 50);
			pudoCrear = true;
		} catch (TableroException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	public void testCrearTableroMapaAltoMayor100Ancho() {
		
		String pathMapa ="./mapas/test3.mapa";
		boolean pudoCrear = false;
		try {
			Tablero tablero = new Tablero();
			tablero.loadFromFile( pathMapa, 50);
			pudoCrear = true;
		} catch (TableroException e) {
			pudoCrear = false;
		}
		
		assertFalse(pudoCrear);
	}
	public void testCrearTableroMapaMenor64Casilla() {
		
		String pathMapa ="./mapas/test4.mapa";
		boolean pudoCrear = false;
		Tablero tablero = new Tablero();
		try {
			tablero.loadFromFile( pathMapa, 10);
			pudoCrear = true;
		} catch (TableroException e) {
			pudoCrear = false;
		}

		assertFalse(pudoCrear);
	}
	

	

}
