package ar.edu.ub.buscaminas.record;

import ar.edu.ub.buscaminas.excepciones.RecordJuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;

public class RecordJuegoSupervivenciaMultiplayer extends RecordJuego {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6575198882362517009L;
	private int tiempoPartida;
	
	public RecordJuegoSupervivenciaMultiplayer(String nombreMapa, Jugador jugador,  int tiempoPartida ) {
		super("Supervivencia - Multiplayer",  nombreMapa, jugador);		
		this.setTiempoPartida(tiempoPartida);
	}
	
	public int getTiempoPartida() {
		return tiempoPartida;
	}
	
	private void setTiempoPartida(int tiempoPartida) {
		if( tiempoPartida < 0 )
			throw new RecordJuegoException("No se puede crear un RecordJuegoSupervivenciaMultiplayer con un tiempoPartida menor a cero");
		
		this.tiempoPartida = tiempoPartida;
	}
	@Override
	public String toString() {		
		return this.getIdRecordJuego() + " - " + this.getTiempoPartida() + " segundos";
	}
}