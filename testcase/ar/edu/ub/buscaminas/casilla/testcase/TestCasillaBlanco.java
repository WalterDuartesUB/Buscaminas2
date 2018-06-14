package ar.edu.ub.buscaminas.casilla.testcase;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaIsNullException;
import junit.framework.TestCase;

public class TestCasillaBlanco extends TestCase {
	
	public void testCrearCasillaBlancoConPosicionNull() {
		Coordenada coordenada = null;
		boolean    pudoCrearCasilla = false;
		
		try {
			new CasillaBlanco(coordenada);
			pudoCrearCasilla = true;
		} catch (CoordenadaIsNullException e) {
			pudoCrearCasilla = false;
		}
		
		assertFalse( pudoCrearCasilla );
	}
	
	public void testCrearCasillaBlancoConPosicionNegativa() {
		Coordenada coordenada = new Coordenada(-5, -5);
		boolean pudoCrearCasilla = false;
		
		try {
			new CasillaBlanco(coordenada);
			pudoCrearCasilla = true;
		}catch (Exception e) {
			pudoCrearCasilla = false;
		}
		
		assertFalse(pudoCrearCasilla);
	}

}
