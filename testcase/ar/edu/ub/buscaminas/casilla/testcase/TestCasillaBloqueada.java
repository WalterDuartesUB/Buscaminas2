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
}
