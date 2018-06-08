package ar.edu.ub.buscaminas;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.Coordenada;

public enum FabricaCasilla {
	CASILLA_BLOQUEADA {
		@Override
		public void addIn(Tablero tablero, int fila, int columna) {
			tablero.add( new CasillaBloqueada( new Coordenada( fila, columna ) ));
		}
	}, 
	CASILLA_BLANCA {
		@Override
		public void addIn(Tablero tablero, int fila, int columna) {
			tablero.add( new CasillaBlanco( new Coordenada( fila, columna ) ));
			
		}
	};

	public abstract void addIn(Tablero tablero, int fila, int columna);

}
