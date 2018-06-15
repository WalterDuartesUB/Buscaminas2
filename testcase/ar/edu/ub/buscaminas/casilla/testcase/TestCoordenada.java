package ar.edu.ub.buscaminas.casilla.testcase;


import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.jugador.Jugador;
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
	
	public void testCrearCoordenadaConUnaCoordenada() {
		Coordenada coordenada = new Coordenada(1,1);
		boolean pudoCrearCoordenada = false;
		
		try {
			new Coordenada(coordenada);
			pudoCrearCoordenada = true;
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
	
	public void testHasCodeCoordenada() {
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(1,1);
		
		assertTrue(coordenada.hashCode() == coordenada2.hashCode());
	}
	
	public void testEqualsCoordenadaMismaCasilla(){
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(1,1);
		
		assertTrue(coordenada.equals(coordenada2));
	}
	
	public void testEqualsCoordenadaDistintaCasilla(){
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(1,1);
		
		assertTrue(coordenada.equals(coordenada2));
		
	}

	
	public void testEqualsCoordenadaOtroObjeto() {
		Coordenada coordenada = new Coordenada(1,1);
		Jugador jugador = new Jugador("asd");
		
		assertFalse(coordenada.equals(jugador));
	}
	
	public void testEqualsMismoObjetoCoordenada() {
		Coordenada coordenada = new Coordenada(1,1);
		assertTrue(coordenada.equals(coordenada));
	}
	public void testEqualsObjetoNullCoordenada() {
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = null;
		
		assertFalse(coordenada.equals(coordenada2));
	}
	
	public void testEqualsDistintaFilaCoordenada() {
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(2,1);
		
		assertFalse(coordenada.equals(coordenada2));
	}
	public void testEqualsDistintaColumnaCoordenada() {
		Coordenada coordenada = new Coordenada(1,6);
		Coordenada coordenada2 = new Coordenada(1,1);
		
		assertFalse(coordenada.equals(coordenada2));
	}
	
	public void testCompareCoordenadaMenor() {
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(2,2);
	
		
		assertEquals(-1, coordenada.compareTo(coordenada2));
	}
	
	public void testCompareCoordenadaIgual() {
		Coordenada coordenada = new Coordenada(2,2);
		Coordenada coordenada2 = new Coordenada(2,2);

		
		assertEquals(0, coordenada.compareTo(coordenada2));
		
	}
	
	public void testCompareCoordenadaMayor() {
		Coordenada coordenada = new Coordenada(3,3);
		Coordenada coordenada2 = new Coordenada(2,2);

		
		assertEquals(1, coordenada.compareTo(coordenada2));

	}
	
	public void testToStringCoordenada() {
		Coordenada coordenada = new Coordenada(1, 1);

		String tostring = "Coordenada [fila=1, columna=1]";
		
		assertEquals(tostring, coordenada.toString());
		
	}
	
	public void testSumarCoordenada() {
		Coordenada coordenada = new Coordenada(1, 1);
		Coordenada coordenada2 = new Coordenada(6, 6);
		
		assertEquals(coordenada2, coordenada.sumar(5, 5));
		
	}
}
