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
				String pathMapa = this.obtenerPathMapaUsuario( );
				int porcentajeBombas = PORCENTAJE_BOMBAS_SUPERVIVENCIA_MULTIPLAYER;
				
				Tablero tablero = new Tablero();		
				this.setJuego(new JuegoSupervivenciaMultiplayer( tablero, this.getJugadores() ));
				
				try {
					tablero.loadFromFile( pathMapa, porcentajeBombas);
				} catch (TableroException e) {
					e.printStackTrace();
				}
			}
			//Modo Conquista
			else if( modoJuego.equals("C") )
			{
				this.obtenerJugadores( JuegoConquista.cantidadMinimaJugadores(), JuegoConquista.cantidadMaximaJugadores() );
				String pathMapa = this.obtenerPathMapaUsuario( );
				int porcentajeBombas = PORCENTAJE_BOMBAS_CONQUISTA_MULTIPLAYER;
				
				Tablero tablero = new Tablero();		
				this.setJuego(new JuegoConquista( tablero, this.getJugadores() ));
				
				try {
					tablero.loadFromFile( pathMapa, porcentajeBombas);
				} catch (TableroException e) {
					e.printStackTrace();
				}			
			}
			//Modo Carrera
			else if( modoJuego.equals("R") )
			{
				this.obtenerJugadores( JuegoCarrera.cantidadMinimaJugadores(), JuegoCarrera.cantidadMaximaJugadores() );		
				ITablero tablero = 	TableroCarrera.crearTableroPartidaCorta();	
				this.setJuego(new JuegoCarrera( tablero, this.getJugadores() ));			
			}
			
			//Cableo las interfaces		
			this.getJuego().setListener( this );
			this.getJuego().setJugadoresPrinter( this);
			this.getJuego().setCasillaPrinter( this );
			
			this.jugarJuego();			
			
		}catch (SeleccionDeTableroException e) {
			this.getConsola().println( e.getMessage() );
			this.getConsola().println( "Enter para volver al menu principal" );
			this.getConsola().nextLine();
		}		
	}

	private String getModoJuego() {
		this.getConsola().println("Elegi el modo de Juego");		
		this.getConsola().println("S - Supervivencia");		
		this.getConsola().println("C - Conquista");		
		this.getConsola().println("R - Carrera");		
		return this.getConsola().nextLine().toUpperCase();
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
		return new Coordenada( this.getConsola().nextInt() - 1, this.getConsola().nextInt() - 1);
	}

	private String obtenerPathMapaUsuario() throws SeleccionDeTableroException {
		return MenuMapas.obtenerPathMapa( this.getConsola(), this.getPathMapas());
	}

	private void obtenerJugadores( int cantidadMinimaJugadores, int cantidadMaximaJugadores ) {
		this.getConsola().println("Ingresa la cantidad de jugadores que van a jugar(min:" + cantidadMinimaJugadores + ",max: " + cantidadMaximaJugadores+ "): ");
		int cantidadJugadores = this.getConsola().nextInt();
		
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
		
		for( Collection<Casilla> filas : casillas ) {
				
			for( Casilla casilla : filas ) {
				this.getConsola().print( "|" );
				
				if( this.getJugadoresColores().get( casilla.getJugador() ) != null )
					this.getConsola().print( this.getJugadoresColores().get( casilla.getJugador() ), FColor.WHITE, casilla.getDibujoCasilla() );
				else
					this.getConsola().print( casilla.getDibujoCasilla() );
				
			}
			
			this.getConsola().print( "|" );
			
			//Quiebre de columna
			this.getConsola().println();
			
			for( int posicion = 0; posicion < filas.size()*2+1; posicion++ )
				this.getConsola().print( "-" );
			this.getConsola().println();			
			
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
		this.getConsola().nextLine();
	}

	@Override
	public void mostrarPerdedor() {		
	}

	@Override
	public void mostrarEmpate(Collection<Jugador> jugadores) {
		this.getJuego().imprimirEstadoJuego();
		this.getConsola().println("Empataron los siguientes jugadores: ");
		for( Jugador jugador : jugadores )
			this.getConsola().println( jugador );
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

}
