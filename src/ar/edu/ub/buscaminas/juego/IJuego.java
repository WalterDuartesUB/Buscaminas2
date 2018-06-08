package ar.edu.ub.buscaminas.juego;

import ar.edu.ub.buscaminas.casilla.Coordenada;

public interface IJuego {
	public void    elegirCasilla( Coordenada coordenada );
	public boolean terminoJuego();
}
