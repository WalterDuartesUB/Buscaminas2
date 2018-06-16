package ar.edu.ub.buscaminas.casilla;

import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaType;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.CoordenadaIsNullException;
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
		this.voltearBocaAbajo();
	}

	protected Casilla(Coordenada coordenada, String dibujo, EstadoCasilla estadoCasilla) {
		this(coordenada, dibujo);
		this.setEstado(estadoCasilla);
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

	private void setCoordenada(Coordenada coordenada) {
		if( coordenada == null )
			throw new CoordenadaIsNullException("No se puede setear como coordenada null");
		this.coordenada = coordenada;
	}

	public abstract void elegiCasilla(TableroListener listener) throws CoordenadaInvalidaException;

	public String getDibujo() {
		return dibujo;
	}
	
	public String getDibujoCasilla() {
		return this.getEstado().getDibujo( this.getDibujo() );
	}

	private void setDibujo(String dibujo) {
		this.dibujo = dibujo;
	}

	private EstadoCasilla getEstado() {
		return estado;
	}

	private void setEstado(EstadoCasilla estado) {
		this.estado = estado;
	}

	public void voltearBocaArriba() throws CoordenadaInvalidaException {
		this.voltearBocaArriba(false);
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

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public void voltearBocaAbajo() {
		this.setEstado( EstadoCasilla.BOCA_ABAJO );
		this.setJugador( null );
	}

	public abstract boolean testCasillaType( CheckCasillaType test );

	public boolean estaBocaAbajo() {
		return this.getEstado() == EstadoCasilla.BOCA_ABAJO;
	}

	public boolean estaBocaArriba() {
		return this.getEstado() == EstadoCasilla.BOCA_ARRIBA;
	}

	public void voltearBocaArriba(Jugador jugador) throws CoordenadaInvalidaException {
		this.voltearBocaArriba();
		this.setJugador(jugador);		
	}

	public void voltearBocaArriba(boolean magico) throws CoordenadaInvalidaException{
		if( this.estaBocaArriba() && magico )
			throw new CoordenadaInvalidaException("No se puede voltear una casilla que ya esta boca arriba");
		
		this.setEstado( EstadoCasilla.BOCA_ARRIBA );	
		
	}
}
