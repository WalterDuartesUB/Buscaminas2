package ar.edu.ub.buscaminas.menu;

import ar.edu.ub.buscaminas.Consola;

public class MenuPrincipal {
	private Consola consola;
	private String pathMapas;
	public MenuPrincipal(Consola consola, String pathMapas) {
		this.setConsola(consola);
		this.setPathMapas(pathMapas);
	}

	public void mostrar() {

		boolean deboContinuar = true;
		
		while( deboContinuar )
		{
			String opcionUsuario = this.pedirOpcionUsuario();			
			
			if( opcionUsuario.equals( "S" ) )			
				new MenuSinglePlayer( this.getConsola(), this.getPathMapas() ).mostrar();			
			else if( opcionUsuario.equals( "M" ) )			
				new MenuMultiPlayer( this.getConsola(), this.getPathMapas() ).mostrar();		
			else if( opcionUsuario.equals( "R" ) )
			{
				//TODO implementar el menu de records				
			}
			else
				deboContinuar = false;
		}
		
	}

	private String pedirOpcionUsuario() {
		String opcionUsuario = "";
		boolean deboContinuar = true;
		
		while( deboContinuar )
		{
			this.getConsola().limpiarPantalla();
			this.getConsola().println( "Menu Principal");
			this.getConsola().println( "--------------");
			this.getConsola().println( "S - Jugar una partida Singleplayer");
			this.getConsola().println( "M - Jugar una partida Multiplayer local");
			this.getConsola().println( "R - Ver los records");
			this.getConsola().println( "Q - Salir de la aplicacion");
			
			opcionUsuario = this.getConsola().nextLine().toUpperCase();			
			if( opcionUsuario.equals("S") || opcionUsuario.equals("R") || opcionUsuario.equals("Q") || opcionUsuario.equals("M") )
				deboContinuar = false;
		}
		
		return opcionUsuario;
	}

	private Consola getConsola() {
		return consola;
	}

	private void setConsola(Consola consola) {
		this.consola = consola;
	}

	private String getPathMapas() {
		return pathMapas;
	}

	private void setPathMapas(String pathMapas) {
		this.pathMapas = pathMapas;
	}

}
