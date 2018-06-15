package ar.edu.ub.buscaminas.casilla.testcase;


import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.CoordenadaIsNullException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import junit.framework.TestCase;

public class TestCasillaBloqueada extends TestCase {

	public void testCrearCasillaBloqueadaConPosicionNull() {
		Coordenada coordenada = null;
		boolean    pudoCrearCasilla = false;
		
		try {
			new CasillaBloqueada(coordenada);
			pudoCrearCasilla = true;
		} catch (CoordenadaIsNullException e) {
			pudoCrearCasilla = false;
		}
		
		assertFalse( pudoCrearCasilla );
	}
	
	public void testCrearCasillaBloqueadaConValoresPositivos() {
		Coordenada coordenada = new Coordenada(1, 1);
		boolean    pudoCrearCasilla = false;
		
		try {
			new CasillaBloqueada(coordenada);
			pudoCrearCasilla = true;
		} catch (CoordenadaIsNullException e) {
			pudoCrearCasilla = false;
		}
		
		assertTrue( pudoCrearCasilla );
		
		
	}


	public void testEqualsCasillaBloqueadaMismaCasilla(){
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		CasillaBloqueada casilla2 = new CasillaBloqueada(coordenada);
		
		assertTrue(casilla1.equals(casilla2));
	}
	
	public void testEqualsCasillaBloqueadaDistintaCasilla(){
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		CasillaBloqueada casilla2 = new CasillaBloqueada(coordenada2);
		
		assertFalse(casilla1.equals(casilla2));
		
	}

	
	public void testEqualsCasillaBloqueadaOtroObjeto() {
		Coordenada coordenada = new Coordenada(1,1);
		
		CasillaBloqueada casilla = new CasillaBloqueada(coordenada);
		Jugador jugador = new Jugador("asd");
		
		assertFalse(casilla.equals(jugador));
	}
	
	public void testCompareCasillaBloqueadaMenor() {
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		CasillaBloqueada casilla2 = new CasillaBloqueada(coordenada2);
		
		assertEquals(-1, casilla1.compareTo(casilla2));
	}
	
	public void testCompareCasillaBloqueadaIgual() {
		Coordenada coordenada = new Coordenada(2,2);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		CasillaBloqueada casilla2 = new CasillaBloqueada(coordenada2);
		
		assertEquals(0, casilla1.compareTo(casilla2));
		
	}
	
	public void testCompareCasillaBloqueadaMayor() {
		Coordenada coordenada = new Coordenada(3,3);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		CasillaBloqueada casilla2 = new CasillaBloqueada(coordenada2);
		
		assertEquals(1, casilla1.compareTo(casilla2));

	
		
	}
	
	public void testEstaBocaAbajoCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		
		assertFalse(casilla1.estaBocaAbajo());

	}
	public void testEstaBocaAbajoCasillaBloqueadaDespuesDeVoltear() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		assertFalse(casilla1.estaBocaAbajo());

	}
	
	public void testEstaBocaArribaCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
	
		assertTrue(casilla1.estaBocaArriba());
	}
	
	public void testEstaBocaArribaCasillaBloqueadaDespuesDeVoltear() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		assertTrue(casilla1.estaBocaArriba());
	}
	
	public void testEstaBocaAbajoCasillaBloqueadaEstandoBocaArriba() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {

		}

		assertFalse(casilla1.estaBocaAbajo());
	}
	
	public void testGetClassCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
	
		
		assertSame(CasillaBloqueada.class, casilla1.getClass());
	
	}
	
	public void testGetCoordenadasCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
	
		
		assertEquals(coordenada, casilla1.getCoordenada());
	}
	
	public void testGetDibujoCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		
	
		
		assertEquals("X", casilla1.getDibujo());
	}
	public void testGetDibujoCasillaCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
	
		assertEquals("X", casilla1.getDibujoCasilla());
	}
	
	public void testGetJugadorCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(3,3);
		Jugador jugador = new Jugador("asd");
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		casilla1.setJugador(jugador);
		assertEquals(jugador, casilla1.getJugador());
		
	}
	
	public void testHashCodeCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		CasillaBloqueada casilla2 = new CasillaBloqueada(coordenada);
		
		assertTrue(casilla1.hashCode() == casilla2.hashCode());

	}
	
	
	
	public void testToStringCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		String tostring = "Casilla [coordenada=Coordenada [fila=1, columna=1], dibujo=X]";
		assertEquals(tostring, casilla1.toString());
		
	}
	
	public void testVoltearBocaArribaJugadorCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		Jugador jugador = new Jugador("asd");
		
		try {
			casilla1.voltearBocaArriba(jugador);
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(casilla1.estaBocaArriba());
	}
	
	public void testVoltearArribaCasillaYaArribaCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean pudoVoltear = false;
		
		try {
			casilla1.voltearBocaArriba();
			pudoVoltear = true;
		} catch (CoordenadaInvalidaException e) {
			pudoVoltear = false;
		}
		
		assertFalse(pudoVoltear);
		 
	}
	
	public void testEqualsMismoObjetoCasillaBloqueada() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBloqueada casilla1 = new CasillaBloqueada(coordenada);
		assertTrue(casilla1.equals(casilla1));
	}
	

}
