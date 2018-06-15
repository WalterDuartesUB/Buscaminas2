package ar.edu.ub.buscaminas.tablero;

import ar.edu.ub.buscaminas.excepciones.TableroException;

public class TableroCarrera{
	public static ITablero crearTableroPartidaCorta()  {
		try {
			return crearTablero( "Carrera Corta", 15,15, 10);
		} catch (TableroException e) {		
		}
		
		return null;
	}
	
	public static ITablero crearTableroPartidaLarga() {
		try {
			return crearTablero("Carrera Larga", 21,21, 25);
		} catch (TableroException e) {			
		}
		
		return null;
	}

	private static ITablero crearTablero(String nombreMapa, int cantidadFilas, int cantidadColumnas, int porcentajeBombas) throws TableroException {
		Tablero tablero = new Tablero();
		
		do 
		{
			tablero.load(cantidadFilas,cantidadColumnas, porcentajeBombas);
			tablero.setNombreMapa(nombreMapa);			
		} 
		while( !tablero.existenTodosLosCaminos() );
						
		return tablero;
	}
}
