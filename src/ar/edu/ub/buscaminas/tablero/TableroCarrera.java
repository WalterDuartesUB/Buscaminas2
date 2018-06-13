package ar.edu.ub.buscaminas.tablero;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaComparadorHorizontal;
import ar.edu.ub.buscaminas.casilla.CasillaComparadorVertical;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;

public class TableroCarrera{
	public static ITablero crearTableroPartidaCorta() {
		return crearTablero(5,5, 10);
	}
	
	public static ITablero crearTableroPartidaLarga() {
		return crearTablero(21,21, 25);
	}

	private static ITablero crearTablero(int cantidadFilas, int cantidadColumnas, int porcentajeBombas) {
		Tablero tablero = new Tablero();
				
		do 
		{
			tablero.load(cantidadFilas,cantidadColumnas, porcentajeBombas);			
		} 
		while( !TableroCarrera.encontrarCaminos( tablero, cantidadFilas, cantidadColumnas ) );
						
		return tablero;
	}

	private static boolean encontrarCaminos(Tablero tablero, int cantidadFilas, int cantidadColumnas) {
		return TableroCarrera.encontrarCaminoVertical(tablero, cantidadColumnas / 2, cantidadFilas) && TableroCarrera.encontrarCaminoHorizontal(tablero, cantidadFilas / 2, cantidadColumnas);
	}
	
	private static boolean encontrarCaminoHorizontal(Tablero tablero, int filaInicial, int cantidadColumnas) {
		
		TreeSet<Casilla> casillasContiguas = new TreeSet<Casilla>(new CasillaComparadorVertical());
		
		try {
			TableroCarrera.obtenerTodasLasCasillasBlancasONumerosContiguas(tablero, tablero.getCasilla( filaInicial, 0), casillasContiguas);
		} catch (CoordenadaInvalidaException e) {
			e.printStackTrace();
		}
		
		if( casillasContiguas.last() == null )
			return false;
		
		return casillasContiguas.last().getCoordenada().getColumna() == (cantidadColumnas - 1);
	}

	private static boolean encontrarCaminoVertical(Tablero tablero, int columnaInicial, int cantidadFilas) {
		TreeSet<Casilla> casillasContiguas = new TreeSet<Casilla>(new CasillaComparadorHorizontal());
		
		try {
			TableroCarrera.obtenerTodasLasCasillasBlancasONumerosContiguas(tablero, tablero.getCasilla( 0, columnaInicial), casillasContiguas);
		} catch (CoordenadaInvalidaException e) {
			e.printStackTrace();
		}
		
		if( casillasContiguas.last() == null )
			return false;
		
		return casillasContiguas.last().getCoordenada().getFila() == (cantidadFilas - 1);
	}

	private static void obtenerTodasLasCasillasBlancasONumerosContiguas(Tablero tablero, Casilla casilla, Set<Casilla> casillasProbadas) {			
		if( !casillasProbadas.add( casilla ) )
			return;
		
		List<Casilla> casillas = tablero.getCasillasBlancasONumerosAlrededor(casilla);
		
		for( Casilla casillaBlanco : casillas )			
			TableroCarrera.obtenerTodasLasCasillasBlancasONumerosContiguas( tablero, casillaBlanco, casillasProbadas );
	}
}
