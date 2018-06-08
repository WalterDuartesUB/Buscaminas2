package ar.edu.ub.buscaminas.juego;

import ar.edu.ub.buscaminas.ITablero;
import ar.edu.ub.buscaminas.Jugador;
import ar.edu.ub.buscaminas.Tablero;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;

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
}
