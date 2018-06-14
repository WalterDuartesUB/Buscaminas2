package ar.edu.ub.buscaminas.menu;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import ar.edu.ub.buscaminas.Consola;
import ar.edu.ub.buscaminas.excepciones.SeleccionDeTableroException;

public class MenuMapas {
	public static String obtenerPathMapa( Consola consola, String directorioDeMapas ) throws SeleccionDeTableroException {
		File directorio = new File( directorioDeMapas );
		Map<String, String> nombresMapas = new HashMap<String, String>();
		String       pathMapaUsuario = null;
		int          posicion = 1;
		
		if( directorio.listFiles() == null )
			throw new SeleccionDeTableroException("No se pueden obtener los mapas para crear los tableros de la carpeta: " + directorioDeMapas );
		
		//Levanto los archivos del directorio
	    for (File file : directorio.listFiles()) {
	        if ( !file.isDirectory() && file.getName().endsWith(".mapa") ) {
	        	nombresMapas.put( String.format("%d", posicion), file.getName());
	        	posicion++;
	        }
	            	    	
	    }
	    
	    //Si no encontre ni uno, salgo
	    if( nombresMapas.size() == 0 )
			throw new SeleccionDeTableroException("No hay mapas para crear tableros en la carpeta: " + directorioDeMapas );
	    
	    //Pido al usuario que elija uno de la lista
		while( pathMapaUsuario == null ) {
			
			consola.println("Elegi el mapa en el que queres jugar: ");
			
			for( String opcion : nombresMapas.keySet() )
				consola.println( opcion + " - " + nombresMapas.get(opcion));
			
			pathMapaUsuario = nombresMapas.get( consola.nextLine() );
		}
		
	    return directorioDeMapas + pathMapaUsuario;
	}
}
