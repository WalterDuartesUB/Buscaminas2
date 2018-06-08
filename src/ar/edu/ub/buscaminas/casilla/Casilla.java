package ar.edu.ub.buscaminas.casilla;

import ar.edu.ub.buscaminas.listener.TableroListener;

public abstract class Casilla implements Comparable<Casilla> {
	
	private Coordenada coordenada;
	private String dibujo;
	
	private EstadoCasilla estado;
	
	public Casilla(Coordenada coordenada, String dibujo) {
		this.setCoordenada(coordenada);
		this.setDibujo(dibujo);
		this.setEstado( EstadoCasilla.BOCA_ABAJO );
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

	public void voltearBocaArriba() {
		this.setEstado( EstadoCasilla.BOCA_ARRIBA );		
	}
	
	@Override
	public int compareTo(Casilla o) {
		return this.getCoordenada().compareTo( o.getCoordenada());
	}
}
