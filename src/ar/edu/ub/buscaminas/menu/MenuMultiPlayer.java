package ar.edu.ub.buscaminas.menu;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;

import ar.edu.ub.buscaminas.Consola;
import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillasPrinter;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.SeleccionDeTableroException;
import ar.edu.ub.buscaminas.excepciones.TableroException;
import ar.edu.ub.buscaminas.juego.Juego;
import ar.edu.ub.buscaminas.juego.JuegoCarrera;
import ar.edu.ub.buscaminas.juego.JuegoConquista;
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaMultiplayer;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.jugador.JugadoresPrinter;
import ar.edu.ub.buscaminas.listener.JuegoListener;
import ar.edu.ub.buscaminas.tablero.ITablero;
import ar.edu.ub.buscaminas.tablero.Tablero;
import ar.edu.ub.buscaminas.tablero.TableroCarrera;

public class MenuMultiPlayer implements JuegoListener, JugadoresPrinter, CasillasPrinter {
	private static final int PORCENTAJE_BOMBAS_SUPERVIVENCIA_MULTIPLAYER = 20;
	private static final int PORCENTAJE_BOMBAS_CONQUISTA_MULTIPLAYER = 20;
	private Consola consola;
	private List<Jugador> jugadores;
	private Map<Jugador, BColor> jugadoresColores;
	private List<BColor> colores;
	private Juego juego;
	private String pathMapas;
	public MenuMultiPlayer(Consola consola, String pathMapas) {
		this.setPathMapas(pathMapas);
		this.setConsola(consola);
		this.setJugadores( new LinkedList<Jugador>());
		this.setJugadoresColores( new HashMap<Jugador, BColor>() );		
		this.agregarColores();		
	}

	private void agregarColores() {
		this.setColores(new LinkedList<BColor>());
		
		this.getColores().add( BColor.BLUE );
		this.getColores().add( BColor.RED );
		this.getColores().add( BColor.CYAN );
		this.getColores().add( BColor.WHITE );
		this.getColores().add( BColor.BLACK );
		this.getColores().add( BColor.MAGENTA );
		this.getColores().add( BColor.YELLOW );
		this.getColores().add( BColor.MAGENTA );
	}

	public void mostrar() {
		try {
			this.getConsola().limpiarPantalla();
			String modoJuego = this.getModoJuego();
			
			//Modo supervivencia
			if( modoJuego.equals("S") )
			{
				this.obtenerJugadores( JuegoSupervivenciaMultiplayer.cantidadMinimaJugadores(), JuegoSupervivenciaMultiplayer.cantidadMaximaJugadores() );				
				Tablero tablero = new Tablero();
				tablero.loadFromFile( this.obtenerPathMapaUsuario( ), PORCENTAJE_BOMBAS_SUPERVIVENCIA_MULTIPLAYER);
				
				this.setJuego(new JuegoSupervivenciaMultiplayer( tablero, this.getJugadores() ));				
			}
			//Modo Conquista
			else if( modoJuego.equals("C") )
			{
				this.obtenerJugadores( JuegoConquista.cantidadMinimaJugadores(), JuegoConquista.cantidadMaximaJugadores() );
				Tablero tablero = new Tablero();
				tablero.loadFromFile( this.obtenerPathMapaUsuario( ), PORCENTAJE_BOMBAS_CONQUISTA_MULTIPLAYER);
				
				this.setJuego(new JuegoConquista( tablero, this.getJugadores() ));
			}
			//Modo Carrera
			else if( modoJuego.equals("R") )
			{
				this.obtenerAliasJugadores( JuegoCarrera.cantidadMaximaJugadores() );		
				ITablero tablero = 	TableroCarrera.crearTableroPartidaCorta();	
				this.setJuego(new JuegoCarrera( tablero, this.getJugadores() ));			
			}
			
			//Cableo las interfaces		
			this.getJuego().setListener( this );
			this.getJuego().setJugadoresPrinter( this);
			this.getJuego().setCasillaPrinter( this );
			
			this.jugarJuego();			
			
		}catch (SeleccionDeTableroException | TableroException e) {
			this.getConsola().println( e.getMessage() );
			this.getConsola().println( "Enter para volver al menu principal" );
			this.getConsola().nextLine();
		}		
	}

	private String getModoJuego() {
		Map<String, String> opcionesMenu = new HashMap<String,String>();
		boolean deboContinuar = true;
		String opcionElegida = "";
		
		//Agrego las opciones del menu
		opcionesMenu.put("S", "Supervivencia");
		opcionesMenu.put("C", "Conquista");
		opcionesMenu.put("R", "Carrera");
		
		while( deboContinuar ) {
			this.getConsola().limpiarPantalla();
			this.getConsola().println("Elegi el modo de Juego");
			this.getConsola().println("----------------------");
			
			for( String opcion : opcionesMenu.keySet() )
				this.getConsola().println( opcion + " - " + opcionesMenu.get(opcion));
	
			opcionElegida = this.getConsola().nextLine().toUpperCase();
			deboContinuar = !opcionesMenu.containsKey( opcionElegida );
		}
		
		return opcionElegida;
	}

	private void jugarJuego() {
		while( !this.getJuego().terminoJuego() )
		{			
			this.getJuego().imprimirEstadoJuego();
			
			try {
				this.getJuego().elegirCasilla( this.pedirCoordenada() );
			} catch (CoordenadaInvalidaException e) {
				this.getConsola().println( BColor.RED, FColor.WHITE, e.getMessage());
				this.getConsola().nextLine();
			}
		}
	}

	private Coordenada pedirCoordenada() {
		this.getConsola().println("Ingresa el par de coordenadas para descubrir una casilla: ");
		return new Coordenada( this.getConsola().nextInt(), this.getConsola().nextInt());
	}

	private String obtenerPathMapaUsuario() throws SeleccionDeTableroException {
		return MenuMapas.obtenerPathMapa( this.getConsola(), this.getPathMapas());
	}

	private void obtenerJugadores( int cantidadMinimaJugadores, int cantidadMaximaJugadores ) {
		int cantidadJugadores = 0;
		
		while( cantidadJugadores < cantidadMinimaJugadores || cantidadJugadores > cantidadMaximaJugadores ){
			this.getConsola().println("Ingresa la cantidad de jugadores que van a jugar(min:" + cantidadMinimaJugadores + ",max: " + cantidadMaximaJugadores+ "): ");
			cantidadJugadores = this.getConsola().nextInt();
		}
		
		this.obtenerAliasJugadores(cantidadJugadores);
				
	}

	private void obtenerAliasJugadores(int cantidadJugadores) {
		for( int posicion = 0; posicion < cantidadJugadores; posicion++) {
			this.getConsola().println("Ingresa el alias para el Jugador " + ( posicion + 1 ) + ": " );
			Jugador jugador = new Jugador( this.getConsola().nextLine() );
			this.getJugadoresColores().put( jugador , this.getColores().get( posicion ) );
			this.getJugadores().add( jugador );
		}
	}

	private Consola getConsola() {
		return consola;
	}

	private void setConsola(Consola consola) {
		this.consola = consola;
	}

	@Override
	public void print(List<List<Casilla>> casillas) {
		this.getConsola().limpiarPantalla();
		int nroFila = 0;
		int posicion = 0;
		
		//Imprimo el encabezado de las columnas
		this.getConsola().print("    |");
		for( posicion = 0; posicion < casillas.get(0).size(); posicion++ )
			this.getConsola().print( String.format("%3d|", posicion) );		
		this.getConsola().println();
		
		//Imprimo un separador
		for( posicion = 0; posicion < casillas.get(0).size()*4+5; posicion++ )
			this.getConsola().print( "-" );
		this.getConsola().println();
		
		//Imprimo las casillas
		for( Collection<Casilla> filas : casillas ) {
			this.getConsola().print( String.format("%3d", nroFila) );	
			for( Casilla casilla : filas ) {
				this.getConsola().print( " | " );
				
				if( casilla.getDibujoCasilla().equals("X") )
					this.getConsola().print( BColor.RED, FColor.BLACK, casilla.getDibujoCasilla() );
				else if( this.getJugadoresColores().get( casilla.getJugador() ) != null )
					this.getConsola().print( this.getJugadoresColores().get( casilla.getJugador() ), FColor.WHITE, casilla.getDibujoCasilla() );
				else
					this.getConsola().print( casilla.getDibujoCasilla() );
				
			}
			
			this.getConsola().print( " |" );
			
			//Imprimo un separador
			this.getConsola().println();
			
			for( posicion = 0; posicion < filas.size()*4+5; posicion++ )
				this.getConsola().print( "-" );
			this.getConsola().println();			
			
			nroFila++;
		}
	}

	@Override
	public void mostrarJugadores(Jugador jugadorDeTurno, Collection<Jugador> otrosJugadores) {
		this.getConsola().println("Turno del jugador: " + jugadorDeTurno.getAlias() );
	}

	@Override
	public void mostrarGanador(Jugador jugador) {
		this.getJuego().imprimirEstadoJuego();
		this.getConsola().println("Gano:" + jugador.getAlias() );
		this.getConsola().println("Enter para volver al menu principal");
		this.getConsola().nextLine();
	}

	@Override
	public void mostrarPerdedor( Jugador jugador ) {
		this.getJuego().imprimirEstadoJuego();
		this.getConsola().println("Murio el jugador:" + jugador.getAlias() );
		this.getConsola().println("Enter pasar al proximo turno");
		this.getConsola().nextLine();
	}

	@Override
	public void mostrarEmpate(Collection<Jugador> jugadores) {
		this.getJuego().imprimirEstadoJuego();
		this.getConsola().println("Empataron los siguientes jugadores: ");
		for( Jugador jugador : jugadores )
			this.getConsola().println( jugador.getAlias() );
		this.getConsola().println("Enter para volver al menu principal");
		this.getConsola().nextLine();
	}

	private List<Jugador> getJugadores() {
		return jugadores;
	}

	private void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	private Map<Jugador, BColor> getJugadoresColores() {
		return jugadoresColores;
	}

	private void setJugadoresColores(Map<Jugador, BColor> jugadoresColores) {
		this.jugadoresColores = jugadoresColores;
	}

	public List<BColor> getColores() {
		return colores;
	}

	private void setColores(List<BColor> colores) {
		this.colores = colores;
	}

	private Juego getJuego() {
		return juego;
	}

	private void setJuego(Juego juego) {
		this.juego = juego;
	}

	private String getPathMapas() {
		return pathMapas;
	}

	private void setPathMapas(String pathMapas) {
		this.pathMapas = pathMapas;
	}

	@Override
	public void pedirCambioDeTurno() {
		this.getJuego().imprimirEstadoJuego();
		this.getConsola().println("Enter para pasar al proximo turno");
		this.getConsola().nextLine();
	}

}
