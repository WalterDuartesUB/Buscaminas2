package ar.edu.ub.buscaminas.juego;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.record.RecordJuego;
import ar.edu.ub.buscaminas.record.RecordJuegoSupervivenciaMultiplayer;
import ar.edu.ub.buscaminas.tablero.ITablero;

public class JuegoSupervivenciaMultiplayer extends Juego{

	public JuegoSupervivenciaMultiplayer(ITablero tablero, List<Jugador> jugadores) {
		super( tablero, jugadores );
	}

	@Override
	public void elegiCasilla( CasillaBomba casilla) {		
		this.mostrarPerdedor();
		
		this.matarJugadorDeTurno();
		
		if( this.getJugadores().size() == 1 ) {
			this.mostrarGanador( this.getJugadorDeTurno() );
			this.getRecordJuegoRepository().add( new RecordJuegoSupervivenciaMultiplayer(this.getTablero().getNombreMapa(), this.getJugadorDeTurno(), super.getSegundosDuracionPartida() ));
		}
	}

	@Override
	public void elegiCasilla(CasillaBlanco casilla) {
		casilla.setJugador(this.getJugadorDeTurno());
		this.getTablero().mostrarBlancosAlrededor( casilla );
		this.cambiarJugadorDeTurno();
	}

	private boolean evaluarEstadoJuego() {				
		if( this.getTablero().getCantidadBlancosYNumerosBocaAbajo() == 0 )		
		{			
			Map<Jugador, Integer> casillasDescubiertasPorJugador = this.getTablero().obtenerCantidadCasillasDescubiertasPorJugador( this.getJugadores() );			
			int cantidadMaximaCasillas = this.obtenerCantidadMaximoCasillasDescubiertas( casillasDescubiertasPorJugador );
			
			//Quito todos los jugadores que no sean el que tiene el maximo
			for( Jugador jugador : casillasDescubiertasPorJugador.keySet() )
				if( casillasDescubiertasPorJugador.get(jugador) < cantidadMaximaCasillas )
					this.getJugadores().remove( jugador );
			
			//Si queda un solo jugador, es el que gano
			if( this.getJugadores().size() == 1 ) {
				this.mostrarGanador( this.getJugadorDeTurno() );
				this.getRecordJuegoRepository().add( new RecordJuegoSupervivenciaMultiplayer(this.getTablero().getNombreMapa(), this.getJugadorDeTurno(), super.getSegundosDuracionPartida() ));				
			}
			else
				this.mostrarEmpate( this.getJugadores() );
			
			return true;
		}
		
		return false;
	}

	private int obtenerCantidadMaximoCasillasDescubiertas(Map<Jugador, Integer> casillasDescubiertasPorJugador) {

		int cantidadCasillas = 0;
		for( Jugador jugador : casillasDescubiertasPorJugador.keySet() )
			cantidadCasillas = Math.max( cantidadCasillas, casillasDescubiertasPorJugador.get(jugador) );
		return cantidadCasillas;
	}

	@Override
	public void elegiCasilla(CasillaNumero casilla) {
		this.cambiarJugadorDeTurno();
	}

	@Override
	public boolean terminoJuego() {
		return ( this.getJugadores().size() == 1 ) || ( this.evaluarEstadoJuego() );
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

	@Override
	protected Collection<RecordJuego> getRecords() {
		return new LinkedList<RecordJuego>();
	}		
}
