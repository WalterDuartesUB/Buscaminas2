package ar.edu.ub.buscaminas.menu;

import java.util.Collection;
import java.util.List;

import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;

import ar.edu.ub.buscaminas.Consola;
import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillasPrinter;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.TableroException;
import ar.edu.ub.buscaminas.juego.Juego;
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaSingleplayer;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.jugador.JugadoresPrinter;
import ar.edu.ub.buscaminas.listener.JuegoListener;
import ar.edu.ub.buscaminas.tablero.Tablero;

public class MenuSinglePlayer implements JuegoListener, CasillasPrinter, JugadoresPrinter {
	private Consola consola;
	private String pathMapas;
	
	public MenuSinglePlayer(Consola consola, String pathMapas) {
		this.setPathMapas(pathMapas);
		this.setConsola(consola);
	}

	public void mostrar() {
		Jugador jugador = this.obtenerJugador();
		String pathMapa = this.getPathMapa();
		int porcentajeBombas = this.obtenerPorcentajeBombas();
		
		Tablero tablero = new Tablero();		
		Juego juego = new JuegoSupervivenciaSingleplayer( tablero, jugador );
		
		try {
			tablero.loadFromFile( pathMapa, porcentajeBombas);
		} catch (TableroException e) {
			e.printStackTrace();
		}
		
		juego.setListener( this );
		juego.setJugadoresPrinter( this);
		juego.setCasillaPrinter( this );
		
		while( !juego.terminoJuego() )
		{			
			juego.imprimirEstadoJuego();
			
			try {
				juego.elegirCasilla( this.pedirCoordenada() );
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

	private Jugador obtenerJugador() {
		return new Jugador( "Player 1" );
	}

	private int obtenerPorcentajeBombas() {
		this.getConsola().println("Elegi la dificultad en la que queres jugar: ");
		return this.getConsola().nextInt();
	}

	private String getPathMapa() {
		this.getConsola().println("Elegi el mapa en el que queres jugar: ");
		return this.getConsola().nextLine();
	}

	private Consola getConsola() {
		return consola;
	}

	private void setConsola(Consola consola) {
		this.consola = consola;
	}

	@Override
	public void mostrarJugadores(Jugador jugadorDeTurno, Collection<Jugador> otrosJugadores) {		
	}

	@Override
	public void print(List<List<Casilla>> casillas) {
		this.getConsola().limpiarPantalla();
		
		for( Collection<Casilla> filas : casillas ) {
				
			for( Casilla casilla : filas ) {
				this.getConsola().print( "|" );
				
				if( casilla.getJugador() != null )
					this.getConsola().print( BColor.BLUE, FColor.WHITE, casilla.getDibujoCasilla() );
				else
					this.getConsola().print( casilla.getDibujoCasilla() );
				
			}
			
			this.getConsola().print( "|" );
			
			//Quiebre de columna para el tablero
			this.getConsola().println();
			
			for( int posicion = 0; posicion < filas.size()*2+1; posicion++ )
				this.getConsola().print( "-" );
			this.getConsola().println();			
			
		}
		
	}

	@Override
	public void mostrarGanador(Jugador jugador) {
		this.getConsola().println( "Ganaste " + jugador.getAlias() );
		this.getConsola().nextLine();
	}

	@Override
	public void mostrarPerdedor() {
		this.getConsola().println( "Perdiste" );
		this.getConsola().nextLine();
	}

	@Override
	public void mostrarEmpate(Collection<Jugador> jugadores) {		
	}

	private String getPathMapas() {
		return pathMapas;
	}

	private void setPathMapas(String pathMapas) {
		this.pathMapas = pathMapas;
	}

}
