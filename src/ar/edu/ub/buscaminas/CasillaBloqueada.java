package ar.edu.ub.buscaminas;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.listener.TableroListener;

public class CasillaBloqueada extends Casilla {

	public CasillaBloqueada(Coordenada coordenada) {
		super(coordenada, "X");
		this.voltearBocaArriba();
	}

	@Override
	public void elegiCasilla(TableroListener listener) {
		
	}
}
