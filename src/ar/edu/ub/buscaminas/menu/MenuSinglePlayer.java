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
import ar.edu.ub.buscaminas.excepciones.SeleccionDeTableroException;
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
		
		try {
			Jugador jugador = this.obtenerJugador();
			String pathMapa = this.obtenerPathMapaUsuario( );
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
			
		}catch (SeleccionDeTableroException e) {
			this.getConsola().println( e.getMessage() );
			this.getConsola().println( "Enter para volver al menu principal" );
			this.getConsola().nextLine();
		}	
	}

	private Coordenada pedirCoordenada() {
		this.getConsola().println("Ingresa el par de coordenadas para descubrir una casilla: ");
		return new Coordenada( this.getConsola().nextInt(), this.getConsola().nextInt());
	}

	private Jugador obtenerJugador() {
		return new Jugador( "Player 1" );
	}

	private int obtenerPorcentajeBombas() {
		this.getConsola().println("Elegi la dificultad en la que queres jugar: ");
		return this.getConsola().nextInt();
	}

	private String obtenerPathMapaUsuario() throws SeleccionDeTableroException {		
		return MenuMapas.obtenerPathMapa(this.getConsola(), this.getPathMapas() );
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
				if( casilla.getJugador() != null )
					this.getConsola().print( BColor.BLUE, FColor.WHITE, casilla.getDibujoCasilla() );
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

	public static int cantidadMinimaJugadores() {
		return 1;
	}

	public static int cantidadMaximaJugadores() {
		return 1;
	}

	@Override
	public void pedirCambioDeTurno() {

		
	}		
}
