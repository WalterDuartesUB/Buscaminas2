package ar.edu.ub.buscaminas;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.casilla.Coordenada;

public class TableroPrueba extends Tablero {
	public TableroPrueba() {
		super();
		
		this.add( new CasillaBomba( new Coordenada(0,0)));
		this.add( new CasillaBlanco( new Coordenada(0,1)));
		this.add( new CasillaBlanco( new Coordenada(0,2)));
		this.add( new CasillaBlanco( new Coordenada(0,3)));
		this.add( new CasillaBlanco( new Coordenada(0,4)));
		this.add( new CasillaBlanco( new Coordenada(0,5)));
		this.add( new CasillaBlanco( new Coordenada(0,6)));
		this.add( new CasillaNumero( new Coordenada(1,1), 2));
	}
}
