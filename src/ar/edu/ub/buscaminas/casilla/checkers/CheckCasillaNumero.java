package ar.edu.ub.buscaminas.casilla.checkers;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;

public class CheckCasillaNumero implements CheckCasilla, CheckCasillaType  {

	@Override
	public boolean test(Casilla casilla) {
		return casilla.testCasillaType(this);
	}

	@Override
	public boolean testCasillaType(CasillaBomba casilla) {
		
		return false;
	}

	@Override
	public boolean testCasillaType(CasillaNumero casilla) {
		
		return true;
	}

	@Override
	public boolean testCasillaType(CasillaBloqueada casilla) {
		
		return false;
	}

	@Override
	public boolean testCasillaType(CasillaBlanco casilla) {
		
		return false;
	}

}
