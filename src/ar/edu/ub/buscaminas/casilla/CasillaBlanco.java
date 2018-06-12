package ar.edu.ub.buscaminas.casilla;

import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaType;
import ar.edu.ub.buscaminas.listener.TableroListener;

public class CasillaBlanco extends Casilla {

	public CasillaBlanco(Coordenada coordenada) {
		super( coordenada, " " );
	}

	@Override
	public void elegiCasilla(TableroListener listener) {
		listener.elegiCasilla(this);		
	}

	@Override
	public boolean testCasillaType(CheckCasillaType test) {
		return test.testCasillaType(this);
	}
}
