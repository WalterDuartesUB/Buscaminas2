package ar.edu.ub.buscaminas.casilla.testcase;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaType;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.CoordenadaIsNullException;
import ar.edu.ub.buscaminas.listener.TableroListener;
import junit.framework.TestCase;

public class TestCasillaBomba extends TestCase {

	
	// crear casilla bomba con posicion null
	
	public void testCrearCasillaBombaConPosicionNull() {
		Coordenada coordenada = null;
		boolean    pudoCrearCasilla = false;
		
		try {
			new CasillaBomba( coordenada );
			pudoCrearCasilla = true;
		} catch (CoordenadaIsNullException e) {
			pudoCrearCasilla = false;
		}
		
		assertFalse( pudoCrearCasilla );
	}
	
	public void testCrearCasillaBombaConPosicionNegativa() {
		Coordenada coordenada = new Coordenada(-5, -5);
		boolean pudoCrearCasilla = false;
		
		try {
			new CasillaBomba(coordenada);
			pudoCrearCasilla = true;
		}catch (Exception e) {
			pudoCrearCasilla = false;
		}
		
		assertFalse(pudoCrearCasilla);
	}
	
	public void testCrearCasillaBombaFueraDelRango() {
		
	}
	
	public void testCrearCasillaBombaDondeHayUnaCasillaConUnaBomba() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		boolean pudoCrearCasilla = false;

		CasillaBomba casillaBomba = new CasillaBomba(coordenada);
		
		try {
			casillaBomba = new CasillaBomba(coordenada);
			pudoCrearCasilla = true;
		}catch(Exception e) {
			pudoCrearCasilla = false;
		}
		assertFalse(pudoCrearCasilla);
		
	}
	
	public void testCrearCasillaBombaDondeHayUnaCasillaBloqueada() {
		/*
		Coordenada coordenada = new Coordenada(1,1);
		boolean pudoCrearCasilla = false;
		
		CasillaBloqueada casillaBloqueada = new CasillaBloqueada(coordenada);
		
		try {
			Casilla casilla = casillaBloqueada;
			casilla = new CasillaBomba(coordenada);
			pudoCrearCasilla= true;
		}catch(Exception e ) {
			pudoCrearCasilla = false;
		}
		assertFalse(pudoCrearCasilla);
		
		*/
	}
}
