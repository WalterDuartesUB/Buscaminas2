package ar.edu.ub.buscaminas.jugador;

import java.util.Collection;

import ar.edu.ub.buscaminas.record.RecordJuego;

public interface JugadoresPrinter {
	public void mostrarJugadores(Jugador jugadorDeTurno, Collection<Jugador> otrosJugadores, Collection<RecordJuego> records );
}
