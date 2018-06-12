package ar.edu.ub.buscaminas.juego;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.tablero.ITablero;
import ar.edu.ub.buscaminas.tablero.Tablero;

public class JuegoConquista extends Juego {

	private Map<Jugador, Integer> contadorBombas;
	
	public JuegoConquista(ITablero tablero, Jugador jugador, Jugador jugador2, Collection<Jugador> jugadores) {
		super(tablero);
		this.setContadorBombas(new HashMap<Jugador,Integer>());
		
		this.getJugadores().add( jugador );
		this.getJugadores().add( jugador2 );
		this.getJugadores().addAll( jugadores );
		
		for( Jugador j : this.getJugadores() )
			this.getContadorBombas().put( j, new Integer(0) );
		
	}

	@Override
	public void elegiCasilla(CasillaBomba casilla) {
		this.contarBomba( this.getJugadorDeTurno() );
		
		System.out.println("Bombas Restantes: " + this.getTablero().getCantidadBombasBocaAbajo());
		
		
		if( this.getTablero().getCantidadBombasBocaAbajo() == 0 )
			this.mostrarGanador( this.getJugadorDeTurno() );
	}

	private void contarBomba(Jugador jugador) {
		this.getContadorBombas().put(jugador, this.getContadorBombas().get(jugador) + 1);		
	}

	@Override
	public void elegiCasilla(CasillaBlanco casilla) {
		this.getTablero().mostrarBlancosAlrededor( casilla );
		this.cambiarJugadorDeTurno();
	}

	@Override
	public void elegiCasilla(CasillaNumero casilla) {
		this.cambiarJugadorDeTurno();
	}

	@Override
	public boolean terminoJuego() {
		return ( this.getTablero().getCantidadBombasBocaAbajo() == 0 );
	}

	public Map<Jugador, Integer> getContadorBombas() {
		return contadorBombas;
	}

	public void setContadorBombas(Map<Jugador, Integer> contadorBombas) {
		this.contadorBombas = contadorBombas;
	}

}
