package ar.edu.ub.buscaminas.casilla.testcase;


import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaIsNullException;
import junit.framework.TestCase;

public class TestCasillaNumero extends TestCase {
	
	public void testCrearCasillaNumeroConPosicionNull() {
		Coordenada coordenada = null;
		boolean    pudoCrearCasilla = false;
		
		try {
			new CasillaNumero(coordenada,1);
			pudoCrearCasilla = true;
		} catch (CoordenadaIsNullException e) {
			pudoCrearCasilla = false;
		}
		
		assertFalse( pudoCrearCasilla );
	}
	
	public void testCrearCasillaNumeroConPosicionNegativa() {
		Coordenada coordenada = new Coordenada(-5, -5);
		boolean pudoCrearCasilla = false;
		
		try {
			new CasillaNumero(coordenada,1);
			pudoCrearCasilla = true;
		}catch (Exception e) {
			pudoCrearCasilla = false;
		}
		
		assertFalse(pudoCrearCasilla);
	}
	
	public void testCrearCasillaNumeroConNumero0() {
		Coordenada coordenada = new Coordenada(1, 1);
		boolean pudoCrearCasilla = false;
		
		try {
			new CasillaNumero(coordenada, 0);
			pudoCrearCasilla = true;
		}catch (Exception e ) {
			pudoCrearCasilla = false;
		}
		assertFalse(pudoCrearCasilla);
	}
	
	public void testCrearCasillaNumeroConNumeroNegativo() {
		Coordenada coordenada = new Coordenada(1, 1);
		boolean pudoCrearCasilla = false;
		
		try {
			new CasillaNumero(coordenada, -5);
			pudoCrearCasilla = true;
		}catch(Exception e ) {
			pudoCrearCasilla = false;
		}
		assertFalse(pudoCrearCasilla);
	}
	
	public void testCrearCasillaNumeroConNumeroPositivo() {
		Coordenada coordenada = new Coordenada(1,1);
		boolean pudoCrearCasilla = false;
	try {
		new CasillaNumero(coordenada, 2);
		pudoCrearCasilla= true;
	}catch(Exception e) {
		pudoCrearCasilla = false;
	}
	assertTrue(pudoCrearCasilla);

	}
	

}
