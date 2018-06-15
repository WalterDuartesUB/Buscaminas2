package ar.edu.ub.buscaminas.casilla.testcase;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.Coordenada;
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
		
		assertEquals(1, casilla1.compareTo(casilla2));
	
		
	}
	
	public void testEstaBocaAbajoCasillaBlanca() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		
		assertTrue(casilla1.estaBocaAbajo());
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
	
	public void testEstaBocaAbajoCasillaBlancaDespuesDeVoltear() {
		Coordenada coordenada = new Coordenada(3,3);
		
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		try {
			casilla1.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		casilla1.voltearBocaAbajo();
		
	

		assertTrue(casilla1.estaBocaAbajo());
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
	

}
