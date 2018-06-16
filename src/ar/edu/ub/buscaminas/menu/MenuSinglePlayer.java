package ar.edu.ub.buscaminas.menu;

import java.util.Collection;
import java.util.HashMap;
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
import ar.edu.ub.buscaminas.juego.JuegoSupervivenciaSingleplayer;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.jugador.JugadoresPrinter;
import ar.edu.ub.buscaminas.listener.JuegoListener;
import ar.edu.ub.buscaminas.record.RecordJuego;
import ar.edu.ub.buscaminas.record.RecordJuegoRepository;
import ar.edu.ub.buscaminas.tablero.Tablero;

public class MenuSinglePlayer implements JuegoListener, CasillasPrinter, JugadoresPrinter {
	
	public enum DificultadesSinglePlayer{
		FACIL {
			@Override
			public int getPorcentajeBombas() {		
				return 10;
			}
		},
		MEDIO {
			@Override
			public int getPorcentajeBombas() {
				return 20;
			}
		},
		DIFICIL {
			@Override
			public int getPorcentajeBombas() {
				return 25;
			}
		};
		
		public abstract int getPorcentajeBombas();
	};
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private Consola consola;
	private String pathMapas;
	private Juego juego;
	private RecordJuegoRepository recordJuegoRepository;
	
	public MenuSinglePlayer(Consola consola, String pathMapas, RecordJuegoRepository recordJuegoRepository) {
		this.setPathMapas(pathMapas);
		this.setConsola(consola);
		this.setRecordJuegoRepository(recordJuegoRepository);
	}

	public void mostrar() {
		
		try {
			Jugador jugador = this.obtenerJugador();
			String pathMapa = this.obtenerPathMapaUsuario( );
			DificultadesSinglePlayer dificultad = this.obtenerDificultad();
			
			Tablero tablero = new Tablero();
			tablero.loadFromFile( pathMapa, dificultad.getPorcentajeBombas());
			
			this.setJuego(new JuegoSupervivenciaSingleplayer( tablero, dificultad.name(), jugador ));
			
			this.getJuego().setRecordJuegoRepository( this.getRecordJuegoRepository() );
			this.getJuego().setListener( this );
			this.getJuego().setJugadoresPrinter( this);
			this.getJuego().setCasillaPrinter( this );
			
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
			
		}catch (SeleccionDeTableroException | TableroException e) {
			this.getConsola().println( e.getMessage() );
			this.getConsola().println( "Enter para volver al menu principal" );
			this.getConsola().nextLine();
		}	
	}

	private Coordenada pedirCoordenada() {
		return this.getConsola().nextCoordenada( "Ingresa el par de coordenadas (fila columna) para descubrir una casilla: " );		
//		this.getConsola().println("Ingresa el par de coordenadas para descubrir una casilla: ");
//		return new Coordenada( this.getConsola().nextInt(), this.getConsola().nextInt());
	}

	private Jugador obtenerJugador() {
		this.getConsola().println("Ingresa tu alias: ");
		return new Jugador( this.getConsola().nextLine() );
	}

	private DificultadesSinglePlayer obtenerDificultad() {
		DificultadesSinglePlayer dificultad = null;
		Map<String, DificultadesSinglePlayer>  opcionesDificultad = new HashMap<String,DificultadesSinglePlayer>();
		
		//Armo un mapa con las dificultades
		for( DificultadesSinglePlayer dificultad1 : DificultadesSinglePlayer.values() )						
			opcionesDificultad.put( String.format("%d", opcionesDificultad.size() + 1), dificultad1 );		
		
		while( dificultad == null ){		
			this.getConsola().limpiarPantalla();
			this.getConsola().println("Elegi la dificultad en la que queres jugar: ");
			this.getConsola().println("--------------------------------------------");
			
			//Imprimo el menu
			for( String opcion : opcionesDificultad.keySet() )
				this.getConsola().println( opcion + " - " + opcionesDificultad.get( opcion ) );			
							
			//Me quedo con la dificultad
			dificultad = opcionesDificultad.get( this.getConsola().nextLine().toUpperCase() );
		}
		
//		return dificultad.getPorcentajeBombas();
		return dificultad;
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
	public void mostrarJugadores(Jugador jugadorDeTurno, Collection<Jugador> otrosJugadores, Collection<RecordJuego> records) {		
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
					this.getConsola().print( casilla.getDibujoCasilla().equals("X") ? BColor.RED : BColor.BLUE, FColor.WHITE, casilla.getDibujoCasilla() );
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
	public void mostrarPerdedor(Jugador jugador) {
		this.getJuego().imprimirEstadoJuego();
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

	private Juego getJuego() {
		return juego;
	}

	private void setJuego(Juego juego) {
		this.juego = juego;
	}

	private RecordJuegoRepository getRecordJuegoRepository() {
		return recordJuegoRepository;
	}

	private void setRecordJuegoRepository(RecordJuegoRepository recordJuegoRepository) {
		this.recordJuegoRepository = recordJuegoRepository;
	}		
}
