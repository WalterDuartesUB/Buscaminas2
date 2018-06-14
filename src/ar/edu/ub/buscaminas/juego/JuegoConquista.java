package ar.edu.ub.buscaminas.juego;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.tablero.ITablero;

public class JuegoConquista extends Juego {

	private Map<Jugador, Integer> contadorBombas;
	
	public JuegoConquista(ITablero tablero, Collection<Jugador> jugadores) {
		super( tablero, jugadores );
		this.setContadorBombas(new HashMap<Jugador,Integer>());
		
		for( Jugador j : this.getJugadores() )
			this.getContadorBombas().put( j, new Integer(0) );
		
		this.validarJuego();
	}

	@Override
	public void elegiCasilla(CasillaBomba casilla) {
		this.contarBomba( this.getJugadorDeTurno() );
				
		this.evaluarEstadoJuego();
	}

	private boolean evaluarEstadoJuego() {
		int cantidadBombas = this.obtenerCantidadMaximaBombasDeJugadores();
		List<Jugador> jugadoresQueNoAlcanzanAlMaximo = new LinkedList<Jugador>();
		
		System.out.println(this.getContadorBombas());
		//Quito todos los jugadores que no tengan oportunidad de ganar el partido
		for( Jugador jugador : this.getJugadores() )
			if( this.getContadorBombas().get(jugador) + this.getTablero().getCantidadBombasBocaAbajo() < cantidadBombas ) {
				jugadoresQueNoAlcanzanAlMaximo.add( jugador );
				this.getContadorBombas().remove( jugador );
			}
		
		//Los "mato" de la partida
		this.getJugadores().removeAll( jugadoresQueNoAlcanzanAlMaximo );
				
		//Si queda un solo jugador, gano ese jugador
		if( this.getJugadores().size() == 1 )
		{
			this.mostrarGanador( this.getJugadorDeTurno() );
			return true;
		}
		//Si no hay mas bombas, es un empate entre los que quedaron
		else if( this.getTablero().getCantidadBombasBocaAbajo() == 0 ) 
		{
			this.mostrarEmpate( this.getJugadores() );
			return true;
		}
		
		return false;
	}

	private int obtenerCantidadMaximaBombasDeJugadores() {
		int cantidadBombas = 0;		
		for( Jugador jugador : this.getJugadores() )
			cantidadBombas = Math.max( cantidadBombas, this.getContadorBombas().get(jugador));		
		return cantidadBombas;
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
		return this.evaluarEstadoJuego();
	}

	private Map<Jugador, Integer> getContadorBombas() {
		return contadorBombas;
	}

	private void setContadorBombas(Map<Jugador, Integer> contadorBombas) {
		this.contadorBombas = contadorBombas;
	}

	public static int cantidadMinimaJugadores() {
		return 2;
	}

	public static int cantidadMaximaJugadores() {
		return 4;
	}

	@Override
	protected void validarJuego() {
		if( this.getJugadores().size() > JuegoConquista.cantidadMaximaJugadores() || this.getJugadores().size() < JuegoConquista.cantidadMinimaJugadores() )
			throw new JuegoException("No se puede iniciar un juego en modo conquista con " + this.getJugadores().size() + ". El minimo es " + JuegoConquista.cantidadMinimaJugadores() + " y el maximo es " + JuegoConquista.cantidadMaximaJugadores() );
		
	}	
}
