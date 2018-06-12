package ar.edu.ub.buscaminas.juego;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.tablero.ITablero;

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
		//Pido un enter para cambiar de turno y ocultar la bomba?
		this.getTablero().ocultarCasilla( casilla );
		
		this.cambiarJugadorDeTurno();
	}

	@Override
	public void elegiCasilla(CasillaBlanco casilla) {
		this.elegiCasillaQueNoEsBomba( casilla );
	}

	@Override
	public void elegiCasilla(CasillaNumero casilla) {
		this.elegiCasillaQueNoEsBomba( casilla );
	}

	private void elegiCasillaQueNoEsBomba(Casilla casilla) {		
		//Si es de otro jugador, pongo boca abajo todas sus casillas elegidas
		this.getTablero().voltearTodasLasCasillasDelJugador( casilla.getJugador() );				
	}

	@Override
	public boolean terminoJuego() {
		// TODO Auto-generated method stub
		return false;
	}

}
