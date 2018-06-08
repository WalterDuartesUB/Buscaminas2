package ar.edu.ub.buscaminas.casilla;

import ar.edu.ub.buscaminas.listener.TableroListener;

public class CasillaBomba extends Casilla {

	public CasillaBomba(Coordenada coordenada) {
		super( coordenada, "*" );
	}

	public CasillaBomba(Casilla casilla ) {
		this( casilla.getCoordenada() );
	}

	@Override
	public void elegiCasilla(TableroListener listener) {
		listener.elegiCasilla(this);
	}

}
