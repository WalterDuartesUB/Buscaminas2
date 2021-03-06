package ar.edu.ub.buscaminas.jugador;

import java.io.Serializable;

import ar.edu.ub.buscaminas.excepciones.JugadorException;

public class Jugador implements Serializable, Comparable<Jugador>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3618930793479323961L;
	private String alias;

	public Jugador(String alias) {		
		this.setAlias(alias);
	}

	public Jugador(Jugador jugador) {
		this( jugador.getAlias() );
	}

	public String getAlias() {
		return alias;
	}

	private void setAlias(String alias) {
		if( alias == null || alias.isEmpty() || alias == " " )
			throw new JugadorException("El alias del Jugador no puede ser null o vacio");
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "Jugador [alias=" + this.getAlias() + "]";
	}

	@Override
	public int hashCode() {
		return this.getAlias().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if( !( obj instanceof Jugador ) )
			return false;
		
		Jugador other = (Jugador) obj;
		return this.getAlias().equals( other.getAlias() );
	}

	@Override
	public int compareTo(Jugador arg0) {
		return this.getAlias().compareTo( arg0.getAlias() );
	}
	
	
}
