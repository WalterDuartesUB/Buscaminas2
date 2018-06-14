package ar.edu.ub.buscaminas.casilla.testcase;

import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaIsNullException;
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
}
