package ar.edu.ub.buscaminas.listener;

import java.util.Collection;

import ar.edu.ub.buscaminas.jugador.Jugador;

public interface JuegoListener {
	public void mostrarGanador( Jugador jugador );
	public void mostrarPerdedor( );
	public void mostrarEmpate( Collection<Jugador> jugadores );
}
