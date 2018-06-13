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
import ar.edu.ub.buscaminas.excepciones.TableroException;
import ar.edu.ub.buscaminas.juego.Juego;
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaMultiplayer;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.jugador.JugadoresPrinter;
import ar.edu.ub.buscaminas.listener.JuegoListener;
import ar.edu.ub.buscaminas.tablero.Tablero;

public class MenuMultiPlayer implements JuegoListener, JugadoresPrinter, CasillasPrinter {
	private static final int PORCENTAJE_BOMBAS_SUPERVIVENCIA_MULTIPLAYER = 20;
	private Consola consola;
	private List<Jugador> jugadores;
	private Map<Jugador, BColor> jugadoresColores;
	private List<BColor> colores;
	private Juego juego;
	public MenuMultiPlayer(Consola consola) {
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
		this.getConsola().limpiarPantalla();		
		this.obtenerJugadores();
		String pathMapa = this.getPathMapa();
		int porcentajeBombas = PORCENTAJE_BOMBAS_SUPERVIVENCIA_MULTIPLAYER;
		
		Tablero tablero = new Tablero();		
		setJuego(new JuegoSupervivenciaMultiplayer( tablero, this.getJugadores() ));
		
		try {
			tablero.loadFromFile( pathMapa, porcentajeBombas);
		} catch (TableroException e) {
			e.printStackTrace();
		}
		
		this.getJuego().setListener( this );
		this.getJuego().setJugadoresPrinter( this);
		this.getJuego().setCasillaPrinter( this );
		
		while( !this.getJuego().terminoJuego() )
		{			
			this.getJuego().imprimirEstadoJuego();
			
			try {
				this.getJuego().elegirCasilla( this.pedirCoordenada() );
			} catch (CoordenadaInvalidaException e) {
				this.getConsola().print( BColor.RED, FColor.WHITE, e.getMessage());
				this.getConsola().nextLine();
			}
		}			
		
	}

	private Coordenada pedirCoordenada() {
		this.getConsola().println("Ingresa el par de coordenadas para descubrir una casilla: ");
		return new Coordenada( this.getConsola().nextInt() - 1, this.getConsola().nextInt() - 1);
	}

	private String getPathMapa() {
		this.getConsola().println("Elegi el mapa en el que quieren jugar: ");
		return this.getConsola().nextLine();
	}

	private void obtenerJugadores() {
		this.getConsola().println("Ingresa la cantidad de jugadores que van a jugar: ");
		int cantidadJugadores = this.getConsola().nextInt();
		
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
	public void print(Collection<Collection<Casilla>> casillas) {
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

	public void setColores(List<BColor> colores) {
		this.colores = colores;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

}
