package ar.edu.ub.buscaminas.casilla;

import ar.edu.ub.buscaminas.tablero.Tablero;

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
