package ar.edu.ub.buscaminas.tablero;

import ar.edu.ub.buscaminas.excepciones.TableroException;

public class TableroCarrera{
	public static ITablero crearTableroPartidaCorta()  {
		try {
			return crearTablero(15,15, 10);
		} catch (TableroException e) {		
		}
		
		return null;
	}
	
	public static ITablero crearTableroPartidaLarga() {
		try {
			return crearTablero(21,21, 25);
		} catch (TableroException e) {			
		}
		
		return null;
	}

	private static ITablero crearTablero(int cantidadFilas, int cantidadColumnas, int porcentajeBombas) throws TableroException {
		Tablero tablero = new Tablero();
		
		do 
		{
			tablero.load(cantidadFilas,cantidadColumnas, porcentajeBombas);
			
			System.out.println();
		} 
		while( !tablero.existenTodosLosCaminos() );
						
		return tablero;
	}
/*
	private static boolean encontrarCaminos(Tablero tablero ) {
		return tablero.existeCaminoHorizontalAscendente() && tablero.existeCaminoHorizontalDescendente() && tablero.existeCaminoVerticalAscendente() && tablero.existeCaminoVerticalDescendente();
//		return TableroCarrera.encontrarCaminoVertical(tablero, cantidadColumnas / 2, cantidadFilas) && TableroCarrera.encontrarCaminoHorizontal(tablero, cantidadFilas / 2, cantidadColumnas);
	}
*/	
/*	
	private static boolean encontrarCaminoHorizontal(Tablero tablero, int filaInicial, int cantidadColumnas) {
		
		TreeSet<Casilla> casillasContiguas = new TreeSet<Casilla>(new CasillaComparador( CriterioOrdenamiento.FILA_ASC_COL_ASC ) );
		
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
		TreeSet<Casilla> casillasContiguas = new TreeSet<Casilla>(new CasillaComparador( CriterioOrdenamiento.COL_ASC_FILA_ASC ) );
		
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
*/
}
