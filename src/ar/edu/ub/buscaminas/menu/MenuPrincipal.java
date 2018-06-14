package ar.edu.ub.buscaminas.menu;
import ar.edu.ub.buscaminas.Consola;
import ar.edu.ub.buscaminas.record.RecordJuegoRepository;

public class MenuPrincipal {
	private Consola consola;
	private String pathMapas;
	private RecordJuegoRepository recordJuegoRepository;
	public MenuPrincipal(Consola consola, String pathMapas, RecordJuegoRepository recordJuegoRepository) {
		this.setConsola(consola);
		this.setPathMapas(pathMapas);
		this.setRecordJuegoRepository(recordJuegoRepository);
	}

	public void mostrar() {

		boolean deboContinuar = true;
		
		//Hago la carga de los records
		this.getRecordJuegoRepository().load();
		
		while( deboContinuar )
		{
			String opcionUsuario = this.pedirOpcionUsuario();			
			
			if( opcionUsuario.equals( "S" ) )			
				new MenuSinglePlayer( this.getConsola(), this.getPathMapas(), this.getRecordJuegoRepository() ).mostrar();			
			else if( opcionUsuario.equals( "M" ) )			
				new MenuMultiPlayer( this.getConsola(), this.getPathMapas(), this.getRecordJuegoRepository() ).mostrar();		
			else if( opcionUsuario.equals( "R" ) )
			{				
				//TODO mover a una clase propia	
				this.getConsola().limpiarPantalla();
				this.getConsola().println("Records");
				this.getConsola().println("-------");
				this.getRecordJuegoRepository().print( this.getConsola() );
				this.getConsola().println("Enter para volver al menu principal");
				this.getConsola().nextLine();
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

	private RecordJuegoRepository getRecordJuegoRepository() {
		return recordJuegoRepository;
	}

	private void setRecordJuegoRepository(RecordJuegoRepository recordJuegoRepository) {
		this.recordJuegoRepository = recordJuegoRepository;
	}

}
