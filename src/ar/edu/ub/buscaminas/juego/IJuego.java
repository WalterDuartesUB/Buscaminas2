package ar.edu.ub.buscaminas.juego;

import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;

public interface IJuego {
	public void    elegirCasilla( Coordenada coordenada ) throws CoordenadaInvalidaException;
	public boolean terminoJuego();
}
