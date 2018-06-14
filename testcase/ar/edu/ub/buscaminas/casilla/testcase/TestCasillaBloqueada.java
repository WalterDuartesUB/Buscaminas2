package ar.edu.ub.buscaminas.casilla.testcase;

import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaIsNullException;
import junit.framework.TestCase;

public class TestCasillaBloqueada extends TestCase {

	// crear casilla bloqueada con posicion null
	
	public void testCrearCasillaBloqueadaConPosicionNull() {
		Coordenada coordenada = null;
		boolean    pudoCrearCasilla = false;
		
		try {
			new CasillaBloqueada( coordenada );
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
			new CasillaBloqueada( coordenada );
			pudoCrearCasilla = true;
		}catch (Exception e) {
			pudoCrearCasilla = false;
		}
		
		assertFalse(pudoCrearCasilla);
	}
	
	public void testEqualsMismaCasillaBloqueada(){
		Coordenada coordenada = new Coordenada (1,1);
		CasillaBloqueada casillabloqueada = new CasillaBloqueada(coordenada);
		CasillaBloqueada casillabloqueada2 = new CasillaBloqueada(coordenada);
		
		assertTrue(casillabloqueada.equals(casillabloqueada2));
	}
	
	public void testEqualsDistintaCasillaBloqueada(){
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBloqueada casillabloqueada = new CasillaBloqueada(coordenada);
		CasillaBloqueada casillabloqueada2 = new CasillaBloqueada(coordenada);
		
		assertFalse(casillabloqueada.equals(casillabloqueada2));
		
	}
}
