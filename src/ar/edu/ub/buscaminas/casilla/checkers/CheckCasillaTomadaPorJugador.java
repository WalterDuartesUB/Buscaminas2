package ar.edu.ub.buscaminas.casilla.checkers;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.jugador.Jugador;

public class CheckCasillaTomadaPorJugador implements CheckCasilla, CheckCasillaType {

	private Jugador jugador;
	
	public CheckCasillaTomadaPorJugador(Jugador jugador) {
		this.setJugador(jugador);
	}

	@Override
	public boolean test(Casilla casilla) {
		return casilla.testCasillaType(this);
	}

	@Override
	public boolean testCasillaType(CasillaBomba casilla) {
		return false;
	}

	@Override
	public boolean testCasillaType(CasillaNumero casilla) {
		return verificarJugador(casilla);
	}

	@Override
	public boolean testCasillaType(CasillaBloqueada casilla) {
		return false;
	}

	@Override
	public boolean testCasillaType(CasillaBlanco casilla) {
		return verificarJugador(casilla);
	}

	private boolean verificarJugador(Casilla casilla) {
		return this.getJugador().equals( casilla.getJugador() );
	}

	private Jugador getJugador() {
		return jugador;
	}

	private void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

}
