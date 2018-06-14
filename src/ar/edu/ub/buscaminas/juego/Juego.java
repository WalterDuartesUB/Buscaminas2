package ar.edu.ub.buscaminas.juego;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.CasillasPrinter;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.jugador.JugadoresPrinter;
import ar.edu.ub.buscaminas.listener.JuegoListener;
import ar.edu.ub.buscaminas.listener.TableroListener;
import ar.edu.ub.buscaminas.tablero.ITablero;

public abstract class Juego implements TableroListener, IJuego {

	private ITablero tablero;
	private JuegoListener listener;
	private JugadoresPrinter	jugadoresPrinter;
	private Queue<Jugador> jugadores;
	
	protected Juego(ITablero tablero) {
		this.setTablero(tablero);
		this.setJugadores( new ConcurrentLinkedQueue<Jugador>());
		this.getTablero().setListener( this );		
	}
	
	public Juego(ITablero tablero, Jugador jugador ) {
		this( tablero );
		this.agregarJugador( jugador );
	}

	public Juego(ITablero tablero, Collection<Jugador> jugadores) {
		this( tablero );
		this.agregarJugadores(jugadores);
	}

	public ITablero getTablero() {
		return tablero;
	}

	private void setTablero(ITablero tablero) {
		this.tablero = tablero;
	}

	private JuegoListener getListener() {
		return listener;
	}

	public void setListener(JuegoListener listener) {
		this.listener = listener;
	}

	public Queue<Jugador> getJugadores() {
		return jugadores;
	}

	private void setJugadores(Queue<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	@Override
	public void elegirCasilla(Coordenada coordenada) throws CoordenadaInvalidaException {
		
		this.validarCoordenadaCasilla( coordenada );
		
		this.getTablero().elegirCasilla( this.getJugadorDeTurno(), coordenada );
	}

	protected void validarCoordenadaCasilla(Coordenada coordenada) throws CoordenadaInvalidaException {
		
	}

	protected void matarJugadorDeTurno() {
		this.getJugadores().poll();
	}

	protected void cambiarJugadorDeTurno() {
		this.getJugadores().add( this.getJugadores().poll() );		
	}

	public void setCasillaPrinter(CasillasPrinter printer) {
		this.getTablero().setPrinter(printer);
		
	}

	public void imprimirEstadoJuego() {
		this.getTablero().imprimir();
		this.getJugadoresPrinter().mostrarJugadores(this.getJugadorDeTurno(), this.getJugadores() );
	}

	protected void mostrarPerdedor( ) {
		this.getListener().mostrarPerdedor( this.getJugadorDeTurno() );		
	}

	protected Jugador getJugadorDeTurno() {
		return this.getJugadores().peek();
	}

	protected void mostrarGanador(Jugador jugador) {
		this.getListener().mostrarGanador(jugador);		
	}

	protected void mostrarEmpate(Collection<Jugador> jugadores) {
		this.getListener().mostrarEmpate(jugadores);		
	}

	public JugadoresPrinter getJugadoresPrinter() {
		return jugadoresPrinter;
	}

	public void setJugadoresPrinter(JugadoresPrinter jugadoresPrinter) {
		this.jugadoresPrinter = jugadoresPrinter;
	}
	
	protected abstract void validarJuego();
	
	protected void agregarJugadores( Collection<Jugador> jugadores) {
		if( jugadores == null || jugadores.size() == 0)
			throw new JuegoException("No se puede crear un Juego sin jugadores");
		
		for( Jugador jugador : jugadores )
			this.agregarJugador( jugador );
	}
	
	private void agregarJugador(Jugador jugador) {
		if( jugadores == null )
			throw new JuegoException("No se puede crear un Juego con un jugador null");
		
		this.jugadores.add(jugador);
	}

	public void mostrarPedirCambioDeTurno() {
		this.getListener().pedirCambioDeTurno();		
	}
	
	public void elegiCasilla(CasillaBloqueada casillaBloqueada) throws CoordenadaInvalidaException	{
		throw new CoordenadaInvalidaException("No se puede elegir una casilla bloqueada");
	}
}
