package ar.edu.ub.buscaminas.casilla.testcase;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaComparador;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.casilla.CasillaComparador.CriterioOrdenamiento;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.CoordenadaIsNullException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import junit.framework.TestCase;

public class TestCasillaComparador extends TestCase {

	public void testCrearCasillaComparador() {
	
		boolean pudoCrear = false;
		
		try {
			new CasillaComparador(CriterioOrdenamiento.COL_ASC);
			pudoCrear= true;
		}catch(Exception e ) {
			pudoCrear = false;
		}
		
		assertTrue(pudoCrear);
		
		/*
	



		 COL_ASC 
		 */
	}
	
	public void testCompareCasillaComparadorFILA_ASC() {
		Coordenada coordenada = new Coordenada(1,5);
		Coordenada coordenada2 = new Coordenada(4,9);
		int diferencia = -3;
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada2);
		
		CasillaComparador comparador = new CasillaComparador(CriterioOrdenamiento.FILA_ASC);

		
		assertEquals(diferencia, comparador.compare(casilla1, casilla2) );
	}
	public void testCompareCasillaComparadorFILA_ASC_COL_ASC () {
		Coordenada coordenada = new Coordenada(1,5);
		Coordenada coordenada2 = new Coordenada(4,9);
		int diferencia = -3;
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada2);
		
		CasillaComparador comparador = new CasillaComparador(CriterioOrdenamiento.FILA_ASC_COL_ASC );
	
		
		assertEquals(diferencia, comparador.compare(casilla1, casilla2) );
	}
	
	public void testCompareCasillaComparadorFILA_ASC_COL_ASCConComparadorFilaIgual0 () {
		Coordenada coordenada = new Coordenada(1,5);
		Coordenada coordenada2 = new Coordenada(1,9);
		int diferencia = -4;
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada2);
		
		CasillaComparador comparador = new CasillaComparador(CriterioOrdenamiento.FILA_ASC_COL_ASC );
	
		assertEquals(diferencia, comparador.compare(casilla1, casilla2) );
	}
	public void testCompareCasillaComparadorFILA_DESC_COL_DESC() {
		Coordenada coordenada = new Coordenada(1,5);
		Coordenada coordenada2 = new Coordenada(4,9);
		int diferencia = 3;
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada2);
		
		CasillaComparador comparador = new CasillaComparador(CriterioOrdenamiento.FILA_DESC_COL_DESC);

		
		assertEquals(diferencia, comparador.compare(casilla1, casilla2) );
	}
	public void testCompareCasillaComparadorCOL_ASC_FILA_ASC () {
		Coordenada coordenada = new Coordenada(1,5);
		Coordenada coordenada2 = new Coordenada(4,5);
		int diferencia = -3;
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada2);
		
		CasillaComparador comparador = new CasillaComparador(CriterioOrdenamiento.COL_ASC_FILA_ASC );

		
		assertEquals(diferencia, comparador.compare(casilla1, casilla2) );
	}
	public void testCompareCasillaComparadorCOL_DESC_FILA_DESC() {
		Coordenada coordenada = new Coordenada(1,5);
		Coordenada coordenada2 = new Coordenada(4,9);
		int diferencia = 4;
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada2);
		
		CasillaComparador comparador = new CasillaComparador(CriterioOrdenamiento.COL_DESC_FILA_DESC);
		
		
		assertEquals(diferencia, comparador.compare(casilla1, casilla2) );
	}
	public void testCompareCasillaComparadorCOL_ASC() {
		Coordenada coordenada = new Coordenada(1,5);
		Coordenada coordenada2 = new Coordenada(4,9);
		int diferencia = -4;
		CasillaBlanco casilla1 = new CasillaBlanco(coordenada);
		CasillaBlanco casilla2 = new CasillaBlanco(coordenada2);
		
		CasillaComparador comparador = new CasillaComparador(CriterioOrdenamiento.COL_ASC);
		
		
		
		assertEquals(diferencia, comparador.compare(casilla1, casilla2) );
	}
}
