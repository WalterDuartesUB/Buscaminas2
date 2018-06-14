package ar.edu.ub.buscaminas.juego;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import ar.edu.ub.buscaminas.Aplicacion;
import ar.edu.ub.buscaminas.casilla.CasillasPrinter;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.jugador.JugadoresPrinter;
import ar.edu.ub.buscaminas.listener.JuegoListener;
import ar.edu.ub.buscaminas.listener.TableroListener;
import ar.edu.ub.buscaminas.tablero.ITablero;
import ar.edu.ub.buscaminas.tablero.Tablero;

public abstract class Juego implements TableroListener, IJuego {

	private ITablero tablero;
	private JuegoListener listener;
	private JugadoresPrinter	jugadoresPrinter;
	private Queue<Jugador> jugadores;
	
	public Juego(ITablero tablero) {
		this.setTablero(tablero);
		this.setJugadores( new ConcurrentLinkedQueue<Jugador>());
		this.getTablero().setListener( this );		
	}
	
	public Juego(Tablero tablero, Collection<Jugador> jugadores ) {				
		this.getJugadores().addAll(jugadores);	
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

	protected void mostrarPerdedor() {
		this.getListener().mostrarPerdedor();		
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
}
