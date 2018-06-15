


package ar.edu.ub.buscaminas.casilla.testcase;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaType;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.CoordenadaIsNullException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.listener.TableroListener;
import junit.framework.TestCase;

public class TestCasillaBomba extends TestCase {


	public void testCrearCasillaBombaConPosicionNull() {
		Coordenada coordenada = null;
		boolean    pudoCrearCasilla = false;
		
		try {
			new CasillaBomba(coordenada);
			pudoCrearCasilla = true;
		} catch (CoordenadaIsNullException e) {
			pudoCrearCasilla = false;
		}
		
		assertFalse( pudoCrearCasilla );
	}
	
	
	
	public void testCrearCasillaBombaConValoresPositivos() {
		Coordenada coordenada = new Coordenada(1, 1);
		boolean    pudoCrearCasilla = false;
		
		try {
			new CasillaBomba(coordenada);
			pudoCrearCasilla = true;
		} catch (CoordenadaIsNullException e) {
			pudoCrearCasilla = false;
		}
		
		assertTrue( pudoCrearCasilla );
		
		
	}
	public void testPonerBombaEnCasillaBlanca() {
		Coordenada coordenada = new Coordenada(1, 1);
		boolean    pudoCrearCasilla = false;
		

		CasillaBomba casillaBomba = new CasillaBomba(new CasillaBlanco(coordenada));
		
		assertEquals(coordenada, casillaBomba.getCoordenada());
		
		try {
			new CasillaBomba(new CasillaBlanco(coordenada));
			pudoCrearCasilla = true;
		} catch (CoordenadaIsNullException e) {
			pudoCrearCasilla = false;
		}
		
		assertTrue( pudoCrearCasilla );
	}

	public void testEqualsCasillaBombaMismaCasilla(){
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		CasillaBomba casilla2 = new CasillaBomba(coordenada);
		
		assertTrue(casilla1.equals(casilla2));
	}
	
	public void testEqualsCasillaBombaDistintaCasilla(){
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		CasillaBomba casilla2 = new CasillaBomba(coordenada2);
		
		assertFalse(casilla1.equals(casilla2));
		
	}

	
	public void testEqualsCasillaBombaOtroObjeto() {
		Coordenada coordenada = new Coordenada(1,1);
		
		CasillaBomba casilla = new CasillaBomba(coordenada);
		Jugador jugador = new Jugador("asd");
		
		assertFalse(casilla.equals(jugador));
	}
	
	public void testCompareCasillaBombaMenor() {
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		CasillaBomba casilla2 = new CasillaBomba(coordenada2);
		
		assertEquals(-1, casilla1.compareTo(casilla2));
	}
	
	public void testCompareCasillaBombaIgual() {
		Coordenada coordenada = new Coordenada(2,2);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		CasillaBomba casilla2 = new CasillaBomba(coordenada2);
		
		assertEquals(0, casilla1.compareTo(casilla2));
		
	}
	
	public void testCompareCasillaBombaMayor() {
		Coordenada coordenada = new Coordenada(3,3);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		CasillaBomba casilla2 = new CasillaBomba(coordenada2);

		assertEquals(1, casilla1.compareTo(casilla2));
		
	}
	
	public void testEstaBocaAbajoCasillaBomba() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		
		assertTrue(casilla1.estaBocaAbajo());

	}
	public void testEstaBocaAbajoCasillaBombaDespuesDeVoltear() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		assertFalse(casilla1.estaBocaAbajo());

	}
	
	public void testEstaBocaArribaCasillaBomba() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
	
	
		
		assertFalse(casilla1.estaBocaArriba());
	}
	
	public void testEstaBocaArribaCasillaBombaDespuesDeVoltear() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		assertTrue(casilla1.estaBocaArriba());
	}
	
	public void testEstaBocaAbajoCasillaBombaEstandoBocaArriba() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {

		}

		assertFalse(casilla1.estaBocaAbajo());
	}
	
	public void testGetClassCasillaBomba() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
	
		
		assertSame(CasillaBomba.class, casilla1.getClass());
	
	}
	
	public void testGetCoordenadasCasillaBomba() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
	
		
		assertEquals(coordenada, casilla1.getCoordenada());
	}
	
	public void testGetDibujoCasillaBomba() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		
	
		
		assertEquals("*", casilla1.getDibujo());
	}
	public void testGetDibujoCasillaCasillaBomba() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
	
		assertEquals("?", casilla1.getDibujoCasilla());
	}
	
	public void testGetJugadorCasillaBomba() {
		Coordenada coordenada = new Coordenada(3,3);
		Jugador jugador = new Jugador("asd");
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		casilla1.setJugador(jugador);
		assertEquals(jugador, casilla1.getJugador());
		
	}
	
	public void testHashCodeCasillaBomba() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		CasillaBomba casilla2 = new CasillaBomba(coordenada);
		
		assertTrue(casilla1.hashCode() == casilla2.hashCode());

	}
	
	
	
	public void testToStringCasillaBomba() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		String tostring = "Casilla [coordenada=Coordenada [fila=1, columna=1], dibujo=*]";
		assertEquals(tostring, casilla1.toString());
		
	}
	
	public void testVoltearBocaArribaJugadorCasillaBomba() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		Jugador jugador = new Jugador("asd");
		
		try {
			casilla1.voltearBocaArriba(jugador);
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(casilla1.estaBocaArriba());
	}
	
	public void testVoltearArribaCasillaBombaYaArriba() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
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
	
	public void testEqualsMismoObjetoCasillaBomba() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBomba casilla1 = new CasillaBomba(coordenada);
		assertTrue(casilla1.equals(casilla1));
	}
	

}
