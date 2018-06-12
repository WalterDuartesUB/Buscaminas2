package ar.edu.ub.buscaminas.casilla.checkers;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;

public class CheckCasillaBlanco implements CheckCasilla, CheckCasillaType {

	@Override
	public boolean test(Casilla casilla) {
		return casilla.testCasillaType(this);
	}

	@Override
	public boolean testCasillaType(CasillaBomba casilla) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean testCasillaType(CasillaNumero casilla) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean testCasillaType(CasillaBloqueada casilla) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean testCasillaType(CasillaBlanco casilla) {
		// TODO Auto-generated method stub
		return true;
	}

}
