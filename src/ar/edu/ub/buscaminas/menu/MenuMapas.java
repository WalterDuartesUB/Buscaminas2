package ar.edu.ub.buscaminas.menu;

import java.io.File;

import ar.edu.ub.buscaminas.Consola;

public class MenuMapas {
	public static String obtenerPathMapa( Consola consola, String pathMapa ) {
		File directorio = new File( pathMapa );
		
		consola.println("Elegi el mapa en el que queres jugar: ");
		
	    for (File file : directorio.listFiles())	    	
	        if ( !file.isDirectory()) 
	            consola.println(file.getName());	        	   
		
	    return consola.nextLine();
	}
}
