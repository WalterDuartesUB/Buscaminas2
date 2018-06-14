package ar.edu.ub.buscaminas.record;

import java.io.Serializable;

import ar.edu.ub.buscaminas.excepciones.RecordJuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;

public abstract class RecordJuego implements Comparable<RecordJuego>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 441996293214779047L;
	private Jugador jugador;
	private String modoDeJuego;
	private String nombreMapa;
	
	public RecordJuego(String modoDeJuego, String nombreMapa, Jugador jugador) {
		this.setModoDeJuego(modoDeJuego);
		this.setJugador(jugador);
		this.setNombreMapa(nombreMapa);
	}

	public Jugador getJugador() {
		return jugador;
	}

	private void setJugador(Jugador jugador) {
		if( jugador == null )
			throw new RecordJuegoException("No se puede crear un RecordJuego con un Jugador null");
		
		this.jugador = jugador;
	}

	public String getModoDeJuego() {
		return modoDeJuego;
	}

	private void setModoDeJuego(String modoDeJuego) {
		if( modoDeJuego == null || modoDeJuego.isEmpty())
			throw new RecordJuegoException("No se puede crear un RecordJuego con un modoDeJuego null o vacio");
		
		this.modoDeJuego = modoDeJuego;
	}

	public String getNombreMapa() {
		return nombreMapa;
	}

	private void setNombreMapa(String nombreMapa) {
		if( nombreMapa == null || nombreMapa.isEmpty())
			throw new RecordJuegoException("No se puede crear un RecordJuego con un nombreMapa null o vacio");		
		this.nombreMapa = nombreMapa;
	}

	@Override
	public int compareTo(RecordJuego otroRecord) {
		return this.getIdRecordJuego().compareTo( otroRecord.getIdRecordJuego() );
	}

	public String getIdRecordJuego() {
		return this.getModoDeJuego() + " - " + this.getNombreMapa();
	}
}
