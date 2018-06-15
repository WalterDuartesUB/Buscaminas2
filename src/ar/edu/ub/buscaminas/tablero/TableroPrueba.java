package ar.edu.ub.buscaminas.tablero;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.casilla.Coordenada;

public class TableroPrueba extends Tablero {
	public TableroPrueba() {
		super();
		
		for( int fila = 0; fila < 8; fila ++ )
			for( int columna = 0; columna < 8; columna++ )
				this.add( new CasillaBlanco( new Coordenada( fila, columna)));
				
		//Pongo bombas donde me conviene
		this.add( new CasillaBomba( new Coordenada(0,0)));
		this.add( new CasillaBomba( new Coordenada(3,3)));
	}
}
