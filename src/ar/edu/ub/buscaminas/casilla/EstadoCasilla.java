package ar.edu.ub.buscaminas.casilla;

public enum EstadoCasilla {
	BOCA_ARRIBA {
		@Override
		public String getDibujo(String dibujo) {
			return dibujo;
		}
	},
	BOCA_ABAJO {
		@Override
		public String getDibujo(String dibujo) {
			return "?";
		}
	},
	MARCADA {
		@Override
		public String getDibujo(String dibujo) {
			return "F";
		}
	};
	
	public abstract String getDibujo( String dibujo );
}
