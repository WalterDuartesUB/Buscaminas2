package ar.edu.ub.buscaminas.record;

import ar.edu.ub.buscaminas.excepciones.RecordJuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;

public class RecordJuegoSupervivenciaSinglePlayer extends RecordJuego {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5995255441311982415L;
	private int tiempoPartida;
	private String dificultad;
	
	public RecordJuegoSupervivenciaSinglePlayer(String nombreMapa, String dificultad, Jugador jugador,  int tiempoPartida ) {
		super("Supervivencia - SinglePlayer",  nombreMapa, jugador);
		this.setDificultad(dificultad);
		this.setTiempoPartida(tiempoPartida);
	}
	
	public int getTiempoPartida() {
		return tiempoPartida;
	}
	
	private void setTiempoPartida(int tiempoPartida) {
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
		return this.getIdRecordJuego() + " - " + this.getTiempoPartida() + " segundos";
	}
}
