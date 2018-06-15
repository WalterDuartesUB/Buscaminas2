package ar.edu.ub.buscaminas.record;

import java.io.Serializable;

import ar.edu.ub.buscaminas.excepciones.RecordJuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;

public class RecordJuegoSupervivenciaSingleplayer extends RecordJuego implements Serializable, Comparable<RecordJuegoSupervivenciaSingleplayer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5995255441311982415L;
	private long tiempoPartida;
	private String dificultad;
	
	public RecordJuegoSupervivenciaSingleplayer(String nombreMapa, String dificultad, Jugador jugador,  long tiempoPartida ) {
		super("Supervivencia - SinglePlayer",  nombreMapa, jugador);
		this.setDificultad(dificultad);
		this.setTiempoPartida(tiempoPartida);
	}
	
	public long getTiempoPartida() {
		return tiempoPartida;
	}
	
	private void setTiempoPartida(long tiempoPartida) {
		if( tiempoPartida < 0 )
			throw new RecordJuegoException("No se puede crear un RecordJuegoSupervivenciaSinglePlayer con un tiempoPartida menor a cero");
		
		this.tiempoPartida = tiempoPartida;
	}

	public String getDificultad() {
		return dificultad;
	}

	private void setDificultad(String dificultad) {
		if( dificultad == null || dificultad.isEmpty() )
			throw new RecordJuegoException("No se puede crear un RecordJuego con un dificultad null o vacio");
		
		this.dificultad = dificultad;
	}

	@Override
	public String getIdRecordJuego() {
		return super.getIdRecordJuego() + " - " + this.getDificultad();
	}

	@Override
	public String toString() {		
		return this.getIdRecordJuego() + " - " + this.getJugador().getAlias() + " - " +  this.getTiempoPartida() + " segundos";
	}
	
	@Override
	public int compareTo(RecordJuegoSupervivenciaSingleplayer o) {
		return (int)(this.getTiempoPartida() - o.getTiempoPartida());
	}	
}
