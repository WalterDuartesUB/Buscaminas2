package ar.edu.ub.buscaminas.casilla;

import ar.edu.ub.buscaminas.listener.TableroListener;

public class CasillaNumero extends Casilla {

	public CasillaNumero(Coordenada coordenada, int cantidad) {
		super( coordenada, String.valueOf( cantidad ) );
	}

	public CasillaNumero(Casilla casilla, int cantidad) {
		this( casilla.getCoordenada(), cantidad);
	}

	@Override
	public void elegiCasilla(TableroListener listener) {
		listener.elegiCasilla(this);		
	}

}
