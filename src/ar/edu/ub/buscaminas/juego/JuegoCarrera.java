package ar.edu.ub.buscaminas.juego;

import java.util.Collection;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.tablero.ITablero;

public class JuegoCarrera extends Juego {

	public JuegoCarrera(ITablero tablero, Collection<Jugador> jugadores) {
		super( tablero );
		
		this.getJugadores().addAll( jugadores );
		
		//TODO el tablero deberia proveer un metodo para elegir "en la mitad" de cada borde basado en una lista de jugadores
		try {
			this.getTablero().elegirCasilla(this.getJugadorDeTurno(), new Coordenada(0,2));
			this.cambiarJugadorDeTurno();
			this.getTablero().elegirCasilla(this.getJugadorDeTurno(), new Coordenada(2,4));
			this.cambiarJugadorDeTurno();
			this.getTablero().elegirCasilla(this.getJugadorDeTurno(), new Coordenada(4,2));
			this.cambiarJugadorDeTurno();
			this.getTablero().elegirCasilla(this.getJugadorDeTurno(), new Coordenada(2,0));
			this.cambiarJugadorDeTurno();			
		} catch (CoordenadaInvalidaException e) {
		}
	}

	@Override
	public void elegiCasilla(CasillaBomba casilla) {
		//Pido un enter para cambiar de turno y ocultar la bomba?
		try {
			this.getTablero().ocultarCasilla( casilla );
		} catch (CoordenadaInvalidaException e) {
			e.printStackTrace();
		}
		
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
		if( !this.getJugadorDeTurno().equals( casilla.getJugador() ) && casilla.getJugador() != null )			
			this.getTablero().voltearTodasLasCasillasDelJugador( casilla.getJugador(), casilla );
	}

	@Override
	public boolean terminoJuego() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void validarCoordenadaCasilla(Coordenada coordenada) throws CoordenadaInvalidaException {
		super.validarCoordenadaCasilla(coordenada);
		
		if( !this.getTablero().obtenerCoordenadasDeCasillasContiguasDelJugador( this.getJugadorDeTurno() ).contains( coordenada ) )
			throw new CoordenadaInvalidaException("No se puede elegir la coordenada " + coordenada + ". Debe elegir alguna coordenada contiguar a las elegidas por el jugador de turno." );
		
	}
}
