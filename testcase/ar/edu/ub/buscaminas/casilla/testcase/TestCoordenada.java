package ar.edu.ub.buscaminas.casilla.testcase;

import ar.edu.ub.buscaminas.casilla.Coordenada;
import junit.framework.TestCase;

public class TestCoordenada extends TestCase {

	public void testCrearCoordenadanull () {
		Coordenada coordenada = null;
		boolean pudoCrearCoordenada = false;
		
		try {
			new Coordenada(coordenada);
			pudoCrearCoordenada = true;
		}catch(Exception e ) {
			pudoCrearCoordenada = false;
		}
		
		assertFalse(pudoCrearCoordenada);
	}
	
	public void testCrearCoordenadaNegativa() {
		boolean pudoCrearCoordenada = false;
		
		try {
			new Coordenada(-4, -4);
			pudoCrearCoordenada = true;
		}catch(Exception e ) {
			pudoCrearCoordenada = false;
		}
		assertFalse(pudoCrearCoordenada);
	}
	
	public void testCrearCoordenadaValoresPositivo() {
		boolean pudoCrearCoordenada = false;
		try {
			new Coordenada(1,1);
			pudoCrearCoordenada=true;
		}catch(Exception e) {
			pudoCrearCoordenada = false;
		}
		
		assertTrue(pudoCrearCoordenada);
	}
	
	public void testCoordenadaGetColumna () {
		int columna = 5;
		Coordenada coordenada = new Coordenada(2, 5);
		
		assertEquals(columna, coordenada.getColumna());
	}
	
	public void testCDoordenadaGetFila() {
		int fila = 4;
		Coordenada coordenada = new Coordenada (4,5);
		
		assertEquals(fila, coordenada.getFila());

	}
}
