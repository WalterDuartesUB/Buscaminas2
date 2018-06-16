package ar.edu.ub.buscaminas.juego;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.record.RecordJuego;
import ar.edu.ub.buscaminas.tablero.ITablero;

public class JuegoCarrera extends Juego {
	
	private Map<Jugador, Coordenada> coordenadaInicialJugadores;

	public JuegoCarrera(ITablero tablero, Collection<Jugador> jugadores) {
		super( tablero, jugadores );
		
		this.setCoordenadaInicialJugadores( new HashMap<Jugador,Coordenada>());
		
		try {
			this.getCoordenadaInicialJugadores().put(this.getJugadorDeTurno(), this.getTablero().getCoordenadaMedioSuperior() );					
			this.cambiarJugadorDeTurno();
			this.getCoordenadaInicialJugadores().put(this.getJugadorDeTurno(), this.getTablero().getCoordenadaMedioDerecha() );					
			this.cambiarJugadorDeTurno();
			this.getCoordenadaInicialJugadores().put(this.getJugadorDeTurno(), this.getTablero().getCoordenadaMedioInferior());					
			this.cambiarJugadorDeTurno();
			this.getCoordenadaInicialJugadores().put(this.getJugadorDeTurno(), this.getTablero().getCoordenadaMedioIzquierda() );					
			this.cambiarJugadorDeTurno();

			for( Jugador jugador : this.getCoordenadaInicialJugadores().keySet() )
				this.getTablero().elegirCasilla( jugador, this.getCoordenadaInicialJugadores().get(jugador),false );
			
		} catch (CoordenadaInvalidaException e) {
		}
	}

	@Override
	public void elegiCasilla(CasillaBomba casilla) {		
		this.mostrarPedirCambioDeTurno();
		
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
		
		//Fuerzo el jugador a la casilla para evaluar si gane
		casilla.setJugador( this.getJugadorDeTurno() );
		
		if( this.terminoJuego() )
			this.mostrarGanador( this.getJugadorDeTurno() );
	}

	@Override
	public boolean terminoJuego() {
		
		return this.getTablero().existeCaminoParaElJugador( this.getCoordenadaInicialJugadores().get( this.getJugadorDeTurno() ) );
	}

	@Override
	protected void validarCoordenadaCasilla(Coordenada coordenada) throws CoordenadaInvalidaException {
		super.validarCoordenadaCasilla(coordenada);
		
		if( !this.getTablero().obtenerCoordenadasDeCasillasContiguasDelJugador( this.getJugadorDeTurno() ).contains( coordenada ) )
			throw new CoordenadaInvalidaException("No se puede elegir la coordenada " + coordenada + ". Debe elegir alguna coordenada contiguar a las elegidas por el jugador de turno." );
		
	}

	private Map<Jugador, Coordenada> getCoordenadaInicialJugadores() {
		return coordenadaInicialJugadores;
	}

	private void setCoordenadaInicialJugadores(Map<Jugador, Coordenada> coordenadaInicialJugadores) {
		this.coordenadaInicialJugadores = coordenadaInicialJugadores;
	}
	
	public static int cantidadMinimaJugadores() {
		return 4;
	}

	public static int cantidadMaximaJugadores() {
		return 4;
	}

	@Override
	protected void validarJuego() {
		if( this.getJugadores().size() > JuegoCarrera.cantidadMaximaJugadores() || this.getJugadores().size() < JuegoCarrera.cantidadMinimaJugadores() )
			throw new JuegoException("No se puede iniciar un juego en modo carrera con " + this.getJugadores().size() + ". El minimo es " + JuegoCarrera.cantidadMinimaJugadores() + " y el maximo es " + JuegoCarrera.cantidadMaximaJugadores() );
		
	}

	@Override
	protected Collection<RecordJuego> getRecords() {
		return new LinkedList<RecordJuego>();
	}
}
