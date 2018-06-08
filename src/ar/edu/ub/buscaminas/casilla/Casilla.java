package ar.edu.ub.buscaminas.casilla;

import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.listener.TableroListener;

public abstract class Casilla implements Comparable<Casilla> {
	
	private Coordenada coordenada;
	private String dibujo;	
	private EstadoCasilla estado;
	private Jugador jugador;
	
	public Casilla(Coordenada coordenada, String dibujo) {
		this.setCoordenada(coordenada);
		this.setDibujo(dibujo);
		this.setEstado( EstadoCasilla.BOCA_ABAJO );
	}

	@Override
	public int hashCode() {		
		return this.getCoordenada().hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if( this == obj )
			return true;
		if( !(obj instanceof Casilla) )
			return false;
		
		Casilla otraCasilla = ( Casilla ) obj;		
		return this.getCoordenada().equals( otraCasilla.getCoordenada() );
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public abstract void elegiCasilla(TableroListener listener);

	public String getDibujo() {
		return dibujo;
	}
	
	public String getDibujoCasilla() {
		return this.getEstado().getDibujo( this.getDibujo() );
	}

	public void setDibujo(String dibujo) {
		this.dibujo = dibujo;
	}

	public EstadoCasilla getEstado() {
		return estado;
	}

	public void setEstado(EstadoCasilla estado) {
		this.estado = estado;
	}

	public void voltearBocaArriba(Jugador jugador) {
		this.setJugador(jugador);
		this.setEstado( EstadoCasilla.BOCA_ARRIBA );		
	}
	
	@Override
	public int compareTo(Casilla o) {
		return this.getCoordenada().compareTo( o.getCoordenada());
	}

	@Override
	public String toString() {
		return "Casilla [coordenada=" + coordenada + ", dibujo=" + dibujo + "]";
	}

	public Jugador getJugador() {
		return jugador;
	}

	private void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

}
