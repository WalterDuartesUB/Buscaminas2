package ar.edu.ub.buscaminas.casilla;

import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaType;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.listener.TableroListener;

public class CasillaBloqueada extends Casilla {

	public CasillaBloqueada(Coordenada coordenada) {
		super(coordenada, "X");
		
		//TODO cambiar esto
		try {
			this.voltearBocaArriba();
		} catch (CoordenadaInvalidaException e) {
		}
	}

	@Override
	public void elegiCasilla(TableroListener listener) throws CoordenadaInvalidaException {
		listener.elegiCasilla(this);
	}

	@Override
	public boolean testCasillaType(CheckCasillaType test) {
		return test.testCasillaType(this);
	}
}
