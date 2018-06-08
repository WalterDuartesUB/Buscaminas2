package ar.edu.ub.buscaminas;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillasPrinter;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.juego.Juego;
import ar.edu.ub.buscaminas.juego.JuegoCarrera;
import ar.edu.ub.buscaminas.juego.JuegoConquista;
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaMultiplayer;
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaSingleplayer;
import ar.edu.ub.buscaminas.listener.JuegoListener;

public class Aplicacion implements JuegoListener, CasillasPrinter, JugadoresPrinter {
	private IConsola  consola;
	
	public Aplicacion() {
		this.setConsola( new Consola() );
	}
	
	public void modoConquista() {
		Aplicacion app = new Aplicacion();
		Tablero tablero = new Tablero();
		
//		Juego juego = new JuegoSupervivenciaMultiplayer( tablero, new Jugador("Player 1"), new Jugador("Player 2"), new LinkedList<Jugador>() );		
//		Juego juego = new JuegoSupervivenciaSingleplayer( tablero, new Jugador("Player 1"));			
		Juego juego = new JuegoConquista( tablero, new Jugador("Player 1"), new Jugador("Player 2"),  new LinkedList<Jugador>() );
		
		tablero.loadFromFile("./mapas/cs_assault.mapa", 80);
				
		juego.setListener( app );
		juego.setJugadoresPrinter( app );
		juego.setCasillaPrinter( app );
		
		while( !juego.terminoJuego() )
		{			
			juego.imprimirEstadoJuego();
			Coordenada coordenada = app.pedirCoordenada();			
			juego.elegirCasilla( coordenada );
		}
		
		juego.imprimirEstadoJuego();
	}
	
	public void modoSupervivencia() {
		Aplicacion app = new Aplicacion();
		Tablero tablero = new Tablero();		
		
//		Juego juego = new JuegoSupervivenciaMultiplayer( tablero, new Jugador("Player 1"), new Jugador("Player 2"), new LinkedList<Jugador>() );		
		Juego juego = new JuegoSupervivenciaSingleplayer( tablero, new Jugador("Player 1"));			
		
		tablero.loadFromFile("./mapas/de_aztec.mapa", 80);
		
		juego.setListener( app );
		juego.setJugadoresPrinter( app );
		juego.setCasillaPrinter( app );
		
		while( !juego.terminoJuego() )
		{			
			juego.imprimirEstadoJuego();
			Coordenada coordenada = app.pedirCoordenada();			
			juego.elegirCasilla( coordenada );
		}
		
		juego.imprimirEstadoJuego();
	}
	
	public void modoCarrera() {
		Aplicacion app = new Aplicacion();
		ITablero tablero = TableroCarrera.crearTableroPartidaCorta();					
		Juego juego = new JuegoCarrera( tablero, new Jugador("Player 1"), new Jugador("Player 2"),  new Jugador("Player 3"), new Jugador("Player 4") );
		
		juego.setListener( app );
		juego.setJugadoresPrinter( app );
		juego.setCasillaPrinter( app );
		/*
		while( !juego.terminoJuego() )
		{			
			juego.imprimirEstadoJuego();
			Coordenada coordenada = app.pedirCoordenada();			
			juego.elegirCasilla( coordenada );
		}
		*/
		juego.imprimirEstadoJuego();
	}
	
	public static void main(String[] args) {
		Aplicacion app = new Aplicacion();
		app.modoCarrera();
	}

	private Coordenada pedirCoordenada() {
		return new Coordenada( this.getConsola().nextInt(), this.getConsola().nextInt());
	}

	@Override
	public void mostrarGanador(Jugador jugador) {
		this.getConsola().println( "Ganador: " + jugador );		
	}

	@Override
	public void mostrarPerdedor() {
		this.getConsola().println( "Perdiste" );
		
	}

	@Override
	public void mostrarEmpate(Collection<Jugador> jugadores) {
		this.getConsola().println( "Empate:"  + jugadores );		
	}

	@Override
	public void print(Collection<Collection<Casilla>> casillas) {
		this.getConsola().limpiarPantalla();
		
		for( Collection<Casilla> filas : casillas ) {
				
			for( Casilla casilla : filas ) {
				this.getConsola().print( "|" );
				this.getConsola().print( casilla.getDibujoCasilla() );
				
			}
			this.getConsola().print( "|" );
			this.getConsola().println();
			for( int posicion = 0; posicion < filas.size()*2+1; posicion++ )
				this.getConsola().print( "-" );
			this.getConsola().println();
			
		}
	}

	public IConsola getConsola() {
		return consola;
	}

	public void setConsola(IConsola consola) {
		this.consola = consola;
	}

	@Override
	public void mostrarJugadores(Jugador jugadorDeTurno, Collection<Jugador> otrosJugadores) {
		this.getConsola().println( "Jugador de turno: " + jugadorDeTurno.getAlias() );
		this.getConsola().println( otrosJugadores );
		
	}


}
