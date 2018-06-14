package ar.edu.ub.buscaminas.listener;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;

public interface TableroListener {
	public void elegiCasilla(CasillaBomba casilla);
	public void elegiCasilla(CasillaBlanco casilla);
	public void elegiCasilla(CasillaNumero casilla);
	public void elegiCasilla(CasillaBloqueada casillaBloqueada) throws CoordenadaInvalidaException;	
}
