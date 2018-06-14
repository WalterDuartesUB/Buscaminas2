package ar.edu.ub.buscaminas.casilla;


public enum FabricaCasilla {
	CASILLA_BLOQUEADA {
		@Override
		public Casilla createInstance( int fila, int columna) {
			return new CasillaBloqueada( new Coordenada( fila, columna ) );
		}
	}, 
	CASILLA_BLANCA {
		@Override
		public Casilla createInstance(int fila, int columna) {
			return new CasillaBlanco( new Coordenada( fila, columna ) );			
		}
	};

	public abstract Casilla createInstance(int fila, int columna);

}
