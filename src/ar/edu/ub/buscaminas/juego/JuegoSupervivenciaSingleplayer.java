package ar.edu.ub.buscaminas.juego;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.tablero.ITablero;
import ar.edu.ub.buscaminas.tablero.Tablero;

public class JuegoSupervivenciaSingleplayer extends Juego {
	public JuegoSupervivenciaSingleplayer(ITablero tablero, Jugador jugador) {
		super( tablero );
		this.getJugadores().add(jugador);
	}

	@Override
	public void elegiCasilla(CasillaBomba casilla) {		
		this.matarJugadorDeTurno();		
		this.mostrarPerdedor();
	}

	@Override
	public void elegiCasilla(CasillaBlanco casilla) {
		this.getTablero().mostrarBlancosAlrededor( casilla );
				
		if( this.getTablero().getCantidadBlancosYNumerosBocaAbajo() == 0 )
			this.mostrarGanador(this.getJugadorDeTurno());			

	}

	@Override
	public void elegiCasilla(CasillaNumero casilla) {		
		if( this.getTablero().getCantidadBlancosYNumerosBocaAbajo() == 0 )
			this.mostrarGanador(this.getJugadorDeTurno());	
	}

	@Override
	public boolean terminoJuego() {
		return ( this.getJugadores().size() == 0 ) || ( this.getTablero().getCantidadBombasBocaAbajo() == 0 );
	}

	public static int cantidadMinimaJugadores() {
		return 1;
	}

	public static int cantidadMaximaJugadores() {
		return 1;
	}
	
	@Override
	protected void validarJuego() {
		if( this.getJugadores().size() < JuegoSupervivenciaSingleplayer.cantidadMaximaJugadores() || this.getJugadores().size() > JuegoSupervivenciaSingleplayer.cantidadMinimaJugadores() )
			throw new JuegoException("No se puede iniciar un juego en modo supervivencia singleplayer con " + this.getJugadores().size() + ". El minimo es " + JuegoSupervivenciaSingleplayer.cantidadMinimaJugadores() + " y el maximo es " + JuegoSupervivenciaSingleplayer.cantidadMaximaJugadores() );
		
	}
}
