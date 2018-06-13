package ar.edu.ub.buscaminas;

import java.util.Collection;
import java.util.List;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillasPrinter;
import ar.edu.ub.buscaminas.tablero.ITablero;
import ar.edu.ub.buscaminas.tablero.TableroCarrera;

public class Aplicacion implements CasillasPrinter {
	
	public static void main(String[] args) {
		Consola consola = new Consola();
		
		ITablero tablero = TableroCarrera.crearTableroPartidaCorta();
		
		tablero.setPrinter(new Aplicacion());
		
		tablero.imprimir();
		
		//new MenuPrincipal( consola ).mostrar();
		
		
		consola.close();
		
		
	}

	@Override
	public void print(List<List<Casilla>> casillas) {		
		
		for( Collection<Casilla> filas : casillas ) {
				
			for( Casilla casilla : filas ) {
				System.out.print( "|" );			
				System.out.print( casilla.getDibujo() );
				
			}
			
			System.out.println();						
		}		
		
	}
	
	
/*implements JuegoListener, CasillasPrinter, JugadoresPrinter {	
	private Consola  consola;
	private Jugador jugador1 = new Jugador("Player 1");
	private Jugador jugador2 = new Jugador("Player 2");
	private Jugador jugador3 = new Jugador("Player 3");
	private Jugador jugador4 = new Jugador("Player 4");
	
	private Map<Jugador, BColor> jugadoresColores;
	
	public Aplicacion() {
		this.setConsola( new Consola() );
		
		this.setJugadoresColores( new HashMap<Jugador,BColor>());
		
		this.getJugadoresColores().put( null, BColor.BLACK );
		this.getJugadoresColores().put( this.getJugador1(), BColor.CYAN );
		this.getJugadoresColores().put( this.getJugador2(), BColor.GREEN );
		this.getJugadoresColores().put( this.getJugador3(), BColor.BLUE );
		this.getJugadoresColores().put( this.getJugador4(), BColor.MAGENTA );
		
	}
	
	public void modoConquista() {
		Aplicacion app = new Aplicacion();
		Tablero tablero = new Tablero();	
		Juego juego = new JuegoConquista( tablero, this.getJugador1(), this.getJugador2(),  new LinkedList<Jugador>() );
		
		tablero.loadFromFile("./mapas/de_aztec.mapa", 10);
				
		juego.setListener( app );
		juego.setJugadoresPrinter( app );
		juego.setCasillaPrinter( app );
		
		while( !juego.terminoJuego() )
		{			
			juego.imprimirEstadoJuego();
			Coordenada coordenada = app.pedirCoordenada();			
			try {
				juego.elegirCasilla( coordenada );
			} catch (CoordenadaInvalidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		juego.imprimirEstadoJuego();
	}
	
	public void modoSupervivenciaMultiplayer() {
		Aplicacion app = new Aplicacion();
		Tablero tablero = new Tablero();				
				
		List<Jugador> jugadores = new LinkedList<Jugador>();
		jugadores.add( this.getJugador3() );
		Juego juego = new JuegoSupervivenciaMultiplayer( tablero, this.getJugador1(), this.getJugador2(), jugadores );
		
		tablero.loadFromFile("./mapas/de_aztec.mapa", 15);
		
		juego.setListener( app );
		juego.setJugadoresPrinter( app );
		juego.setCasillaPrinter( app );
		
		while( !juego.terminoJuego() )
		{			
			juego.imprimirEstadoJuego();
			Coordenada coordenada = app.pedirCoordenada();			
			try {
				juego.elegirCasilla( coordenada );
			} catch (CoordenadaInvalidaException e) {
				e.printStackTrace();
			}
		}
		
		juego.imprimirEstadoJuego();
	}
	
	public void modoSupervivencia() {
		Aplicacion app = new Aplicacion();
		Tablero tablero = new Tablero();				
			
		List<Jugador> jugadores = new LinkedList<Jugador>();
		jugadores.add( this.getJugador3() );
		Juego juego = new JuegoSupervivenciaMultiplayer( tablero, this.getJugador1(), this.getJugador2(), jugadores );
		
		tablero.loadFromFile("./mapas/de_aztec.mapa", 15);
		
		juego.setListener( app );
		juego.setJugadoresPrinter( app );
		juego.setCasillaPrinter( app );
		
		while( !juego.terminoJuego() )
		{			
			juego.imprimirEstadoJuego();
			Coordenada coordenada = app.pedirCoordenada();			
			try {
				juego.elegirCasilla( coordenada );
			} catch (CoordenadaInvalidaException e) {
				e.printStackTrace();
			}
		}
		
		juego.imprimirEstadoJuego();
	}
	
	public void modoCarrera() {
		Aplicacion app = new Aplicacion();
		ITablero tablero = TableroCarrera.crearTableroPartidaCorta();					
		Juego juego = new JuegoCarrera( tablero, this.getJugador1(),this.getJugador2(),this.getJugador3(),this.getJugador4() );
		
		juego.setListener( app );
		juego.setJugadoresPrinter( app );
		juego.setCasillaPrinter( app );
		
		while( !juego.terminoJuego() )
		{			
			juego.imprimirEstadoJuego();
			Coordenada coordenada = app.pedirCoordenada();
		
			try {
				juego.elegirCasilla( coordenada );
			} catch (CoordenadaInvalidaException e) {
				e.printStackTrace();
			}
		}
		
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

	public Consola getConsola() {
		return consola;
	}

	public void setConsola(Consola consola) {
		this.consola = consola;
	}

	@Override
	public void mostrarJugadores(Jugador jugadorDeTurno, Collection<Jugador> otrosJugadores) {
		this.getConsola().println( "Jugador de turno: " + jugadorDeTurno.getAlias() );
		this.getConsola().println( otrosJugadores );
		
	}

	public Jugador getJugador4() {
		return jugador4;
	}

	public void setJugador4(Jugador jugador4) {
		this.jugador4 = jugador4;
	}

	public Jugador getJugador3() {
		return jugador3;
	}

	public void setJugador3(Jugador jugador3) {
		this.jugador3 = jugador3;
	}

	public Jugador getJugador2() {
		return jugador2;
	}

	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}

	public Jugador getJugador1() {
		return jugador1;
	}

	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}

	public Map<Jugador, BColor> getJugadoresColores() {
		return jugadoresColores;
	}

	public void setJugadoresColores(Map<Jugador, BColor> jugadoresColores) {
		this.jugadoresColores = jugadoresColores;
	}
*/

}
