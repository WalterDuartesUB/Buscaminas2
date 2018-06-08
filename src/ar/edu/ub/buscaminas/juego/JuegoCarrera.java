package ar.edu.ub.buscaminas.juego;

import ar.edu.ub.buscaminas.ITablero;
import ar.edu.ub.buscaminas.Jugador;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;

public class JuegoCarrera extends Juego {

	public JuegoCarrera(ITablero tablero, Jugador jugador, Jugador jugador2, Jugador jugador3, Jugador jugador4) {
		super( tablero );
		
		this.getJugadores().add(jugador);
		this.getJugadores().add(jugador2);
		this.getJugadores().add(jugador3);
		this.getJugadores().add(jugador4);
	}

	@Override
	public void elegiCasilla(CasillaBomba casilla) {
		// TODO Auto-generated method stub

	}

	@Override
	public void elegiCasilla(CasillaBlanco casilla) {
		// TODO Auto-generated method stub

	}

	@Override
	public void elegiCasilla(CasillaNumero casilla) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean terminoJuego() {
		// TODO Auto-generated method stub
		return false;
	}

}
