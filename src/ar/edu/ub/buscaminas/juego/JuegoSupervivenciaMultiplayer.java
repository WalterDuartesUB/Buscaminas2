package ar.edu.ub.buscaminas.juego;

import java.util.List;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.tablero.ITablero;

public class JuegoSupervivenciaMultiplayer extends Juego{

	public JuegoSupervivenciaMultiplayer(ITablero tablero, List<Jugador> jugadores) {
		super( tablero, jugadores );
	}

	@Override
	public void elegiCasilla(CasillaBomba casilla) {		
		super.mostrarPerdedor();
		
		this.matarJugadorDeTurno();
		
		if( this.getJugadores().size() == 1 )
			this.mostrarGanador( this.getJugadorDeTurno() );
	}

	@Override
	public void elegiCasilla(CasillaBlanco casilla) {
		this.getTablero().mostrarBlancosAlrededor( casilla );
		this.cambiarJugadorDeTurno();
		
		if( this.getTablero().getCantidadBlancosYNumerosBocaAbajo() == 0 )
			this.mostrarEmpate( this.getJugadores() );
	}

	@Override
	public void elegiCasilla(CasillaNumero casilla) {
		this.cambiarJugadorDeTurno();
		
		if( this.getTablero().getCantidadBlancosYNumerosBocaAbajo() == 0 )
			this.mostrarEmpate( this.getJugadores() );		
	}

	@Override
	public boolean terminoJuego() {
		return ( this.getJugadores().size() == 1 ) || ( this.getTablero().getCantidadBombasBocaAbajo() == 0 );
	}
	
	public static int cantidadMinimaJugadores() {
		return 2;
	}

	public static int cantidadMaximaJugadores() {
		return 8;
	}

	@Override
	protected void validarJuego() {
		if( this.getJugadores().size() > JuegoSupervivenciaMultiplayer.cantidadMaximaJugadores() || this.getJugadores().size() < JuegoSupervivenciaMultiplayer.cantidadMinimaJugadores() )
			throw new JuegoException("No se puede iniciar un juego en modo supervivencia multiplayer con " + this.getJugadores().size() + ". El minimo es " + JuegoSupervivenciaMultiplayer.cantidadMinimaJugadores() + " y el maximo es " + JuegoSupervivenciaMultiplayer.cantidadMaximaJugadores() );
		
	}		
}
