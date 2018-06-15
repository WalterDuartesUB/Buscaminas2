package ar.edu.ub.buscaminas.casilla.testcase;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.casilla.EstadoCasilla;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.CoordenadaIsNullException;
import ar.edu.ub.buscaminas.jugador.Jugador;
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
	
	public void testCrearCasillaBlancoConValoresPositivos() {
		Coordenada coordenada = new Coordenada(1, 1);
		boolean    pudoCrearCasilla = false;
		
		try {
			new CasillaBlanco(coordenada);
			pudoCrearCasilla = true;
		} catch (CoordenadaIsNullException e) {
			pudoCrearCasilla = false;
		}
		
		assertTrue( pudoCrearCasilla );
		
		
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

	
	public void testEqualsCasillaBlancoOtroObjeto() {
		Coordenada coordenada = new Coordenada(1,1);
		
		CasillaBlanco casilla = new CasillaBlanco(coordenada);
		Jugador jugador = new Jugador("asd");
		
		assertFalse(casilla.equals(jugador));
	}
	
	public void testCompareCasillaBlancoMenor() {
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada2);
		
		assertEquals(-1, casilla1.compareTo(casilla2));
	}
	
	public void testCompareCasillaBlancoIgual() {
		Coordenada coordenada = new Coordenada(2,2);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada2);
		
		assertEquals(0, casilla1.compareTo(casilla2));
		
	}
	
	public void testCompareCasillaBlancoMayor() {
		Coordenada coordenada = new Coordenada(3,3);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada2);

	
		
	}
	
	public void testEstaBocaAbajoCasillaBlanca() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		
		assertTrue(casilla1.estaBocaAbajo());

	}
	public void testEstaBocaAbajoCasillaBlancaDespuesDeVoltear() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		assertFalse(casilla1.estaBocaAbajo());

	}
	
	public void testEstaBocaArribaCasillaBlanca() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
	
	
		
		assertFalse(casilla1.estaBocaArriba());
	}
	
	public void testEstaBocaArribaCasillaBlancaDespuesDeVoltear() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		assertTrue(casilla1.estaBocaArriba());
	}
	
	public void testEstaBocaAbajoCasillaBlancaEstandoBocaArriba() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {

		}

		assertFalse(casilla1.estaBocaAbajo());
	}
	
	public void testGetClassCasillaBlanca() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
	
		
		assertSame(CasillaBlanco.class, casilla1.getClass());
	
	}
	
	public void testGetCoordenadasCasillaBlanda() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
	
		
		assertEquals(coordenada, casilla1.getCoordenada());
	}
	
	public void testGetDibujoCasillaBlanca() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		
	
		
		assertEquals(" ", casilla1.getDibujo());
	}
	public void testGetDibujoCasillaCasillaBlanca() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
	
		assertEquals("?", casilla1.getDibujoCasilla());
	}
	
	public void testGetJugadorCasillaBlanca() {
		Coordenada coordenada = new Coordenada(3,3);
		Jugador jugador = new Jugador("asd");
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		casilla1.setJugador(jugador);
		assertEquals(jugador, casilla1.getJugador());
		
	}
	
	public void testHashCodeCasillaBlanca() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada);
		
		assertTrue(casilla1.hashCode() == casilla2.hashCode());

	}
	
	
	public void testTestCasillaTypeCasillaBlanca() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);

		
		/*
		 * ??
		 */
	}
	
	public void testToStringCasillaBlanca() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		String tostring = "Casilla [coordenada=Coordenada [fila=1, columna=1], dibujo= ]";
		assertEquals(tostring, casilla1.toString());
		
	}
	
	public void testVoltearBocaArribaJugadorCasillaBlanca() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		Jugador jugador = new Jugador("asd");
		
		try {
			casilla1.voltearBocaArriba(jugador);
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(casilla1.estaBocaArriba());
	}
	
	public void testVoltearArribaCasillaYaArriba() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
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
	
	public void testEqualsMismoObjetoCasillaBlanca() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		assertTrue(casilla1.equals(casilla1));
	}
	

}
