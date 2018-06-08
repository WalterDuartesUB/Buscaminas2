package ar.edu.ub.buscaminas.juego;

import java.util.List;

import ar.edu.ub.buscaminas.Jugador;
import ar.edu.ub.buscaminas.Tablero;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;

public class JuegoSupervivenciaMultiplayer extends Juego{

	public JuegoSupervivenciaMultiplayer(Tablero tablero, Jugador jugador, Jugador jugador2, List<Jugador> jugadores) {
		super( tablero );
		this.getJugadores().add(jugador);
		this.getJugadores().add(jugador2);
		this.getJugadores().addAll( jugadores );
	}

	@Override
	public void elegiCasilla(CasillaBomba casilla) {		
		this.matarJugadorDeTurno();
		
		if( this.getJugadores().size() == 1 )
			this.mostrarGanador( this.getJugadorDeTurno() );
	}

	@Override
	public void elegiCasilla(CasillaBlanco casilla) {
		this.getTablero().mostrarBlancosAlrededor( casilla );
		this.cambiarJugadorDeTurno();
		
		if( this.getTablero().getCantidadBombasBocaAbajo() == 0 )
			this.mostrarEmpate( this.getJugadores() );
	}

	@Override
	public void elegiCasilla(CasillaNumero casilla) {
		this.cambiarJugadorDeTurno();
		
		if( this.getTablero().getCantidadBombasBocaAbajo() == 0 )
			this.mostrarEmpate( this.getJugadores() );		
	}

	@Override
	public boolean terminoJuego() {
		return ( this.getJugadores().size() == 1 ) || ( this.getTablero().getCantidadBombasBocaAbajo() == 0 );
	}

}