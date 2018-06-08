package ar.edu.ub.buscaminas.casilla;

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
