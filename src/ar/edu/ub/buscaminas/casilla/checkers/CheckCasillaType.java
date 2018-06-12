package ar.edu.ub.buscaminas.casilla.checkers;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;

public interface CheckCasillaType {

	boolean testCasillaType(CasillaBomba casilla);

	boolean testCasillaType(CasillaNumero casilla);

	boolean testCasillaType(CasillaBloqueada casilla);

	boolean testCasillaType(CasillaBlanco casilla);

}