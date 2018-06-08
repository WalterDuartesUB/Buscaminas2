package ar.edu.ub.buscaminas.jugador;

public class Jugador {
	private String alias;

	public Jugador(String alias) {		
		this.setAlias(alias);
	}

	public String getAlias() {
		return alias;
	}

	private void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "Jugador [alias=" + this.getAlias() + "]";
	}
	
	
}
