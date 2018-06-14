package ar.edu.ub.buscaminas.menu;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ar.edu.ub.buscaminas.Consola;

public class MenuMapas {
	public static String obtenerPathMapa( Consola consola, String directorioDeMapas ) {
		File directorio = new File( directorioDeMapas );
		Map<String, String> nombresMapas = new HashMap<String, String>();
		String       pathMapaUsuario = null;
		int          posicion = 1;
		
		//Levanto los archivos del directorio
	    for (File file : directorio.listFiles()) {
	        if ( !file.isDirectory()) {
	        	nombresMapas.put( String.format("%d", posicion), file.getName());
	        	posicion++;
	        }
	            	    	
	    }
	    
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
