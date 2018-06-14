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
	
	public void testEqualsCasillaBlancoMismaCasilla(){
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada);
		
		assertTrue(casilla1.equals(casilla2));
	}
	
	public void testEqualsCasillaBlancoDistintaCasilla(){
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada2);
		
		assertFalse(casilla1.equals(casilla2));
		
	}
	

}
