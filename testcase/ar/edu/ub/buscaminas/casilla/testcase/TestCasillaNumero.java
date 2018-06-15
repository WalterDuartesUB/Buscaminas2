package ar.edu.ub.buscaminas.casilla.testcase;


import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.CoordenadaIsNullException;
import ar.edu.ub.buscaminas.jugador.Jugador;
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

	
	public void testCrearCasillaNumeroConValoresPositivos() {
		Coordenada coordenada = new Coordenada(1, 1);
		boolean    pudoCrearCasilla = false;
		
		try {
			new CasillaNumero(coordenada,1);
			pudoCrearCasilla = true;
		} catch (CoordenadaIsNullException e) {
			pudoCrearCasilla = false;
		}
		
		assertTrue( pudoCrearCasilla );
		
		
	}
	public void testCasillaNumeroEnCasillaBlanca() {
		Coordenada coordenada = new Coordenada(1, 1);
		boolean    pudoCrearCasilla = false;
		

		CasillaNumero casillaBomba = new CasillaNumero(new CasillaBlanco(coordenada),1);
		
		assertEquals(coordenada, casillaBomba.getCoordenada());
		
		try {
			new CasillaBomba(new CasillaBlanco(coordenada));
			pudoCrearCasilla = true;
		} catch (CoordenadaIsNullException e) {
			pudoCrearCasilla = false;
		}
		
		assertTrue( pudoCrearCasilla );
	}

	public void testEqualsCasillaNumeroMismaCasilla(){
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		CasillaNumero casilla2 = new CasillaNumero(coordenada,1);
		
		assertTrue(casilla1.equals(casilla2));
	}
	
	public void testEqualsCasillaNumeroDistintaCasilla(){
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		CasillaNumero casilla2 = new CasillaNumero(coordenada2,1);
		
		assertFalse(casilla1.equals(casilla2));
		
	}

	

	
	public void testCompareCasillaNumeroMenor() {
		Coordenada coordenada = new Coordenada(1,1);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		CasillaNumero casilla2 = new CasillaNumero(coordenada2,1);
		
		assertEquals(-1, casilla1.compareTo(casilla2));
	}
	
	public void testCompareCasillaNumeroIgual() {
		Coordenada coordenada = new Coordenada(2,2);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		CasillaNumero casilla2 = new CasillaNumero(coordenada2,1);
		
		assertEquals(0, casilla1.compareTo(casilla2));
		
	}
	
	public void testCompareCasillaNumeroMayor() {
		Coordenada coordenada = new Coordenada(3,3);
		Coordenada coordenada2 = new Coordenada(2,2);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		CasillaNumero casilla2 = new CasillaNumero(coordenada2,1);
		
		assertEquals(1, casilla1.compareTo(casilla2));

	
		
	}
	
	public void testEstaBocaAbajoCasillaNumero() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		
		assertTrue(casilla1.estaBocaAbajo());

	}
	public void testEstaBocaAbajoCasillaNumeroDespuesDeVoltear() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		assertFalse(casilla1.estaBocaAbajo());

	}
	
	public void testEstaBocaArribaCasillaNumero() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
	
	
		
		assertFalse(casilla1.estaBocaArriba());
	}
	
	public void testEstaBocaArribaCasillaBlancaDespuesDeVoltear() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		assertTrue(casilla1.estaBocaArriba());
	}
	
	public void testEstaBocaAbajoCasillaNumeroEstandoBocaArriba() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {

		}

		assertFalse(casilla1.estaBocaAbajo());
	}
	
	public void testGetClassCasillaNumero() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
	
		
		assertSame(CasillaNumero.class, casilla1.getClass());
	
	}
	
	public void testGetCoordenadasCasillaNumero() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
	
		
		assertEquals(coordenada, casilla1.getCoordenada());
	}
	
	public void testGetDibujoCasillaNumero() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		
	
		
		assertEquals("1", casilla1.getDibujo());
	}
	public void testGetDibujoCasillaCasillaNumero() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
	
		assertEquals("?", casilla1.getDibujoCasilla());
	}
	
	public void testGetJugadorCasillaNumero() {
		Coordenada coordenada = new Coordenada(3,3);
		Jugador jugador = new Jugador("asd");
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		casilla1.setJugador(jugador);
		assertEquals(jugador, casilla1.getJugador());
		
	}
	
	public void testHashCodeCasillaNumero() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		CasillaNumero casilla2 = new CasillaNumero(coordenada,1);
		
		assertTrue(casilla1.hashCode() == casilla2.hashCode());

	}
	
	
	
	public void testToStringCasillaNumero() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		String tostring = "Casilla [coordenada=Coordenada [fila=1, columna=1], dibujo=1]";
		assertEquals(tostring, casilla1.toString());
		
	}
	
	public void testVoltearBocaArribaJugadorCasillaNumero() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		Jugador jugador = new Jugador("asd");
		
		try {
			casilla1.voltearBocaArriba(jugador);
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(casilla1.estaBocaArriba());
	}
	
	public void testVoltearArribaCasillaNumero() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
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
	
	public void testEqualsMismoObjetoCasillaNumero() {
		Coordenada coordenada = new Coordenada(1, 1);
		
		CasillaNumero casilla1 = new CasillaNumero(coordenada,1);
		assertTrue(casilla1.equals(casilla1));
	}
	

}
