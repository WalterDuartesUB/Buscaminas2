package ar.edu.ub.buscaminas.listener;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;

public interface TableroListener {
	public void elegiCasilla(CasillaBomba casilla);
	public void elegiCasilla(CasillaBlanco casilla);
	public void elegiCasilla(CasillaNumero casilla);	
}
