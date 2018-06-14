package ar.edu.ub.buscaminas.record;

import ar.edu.ub.buscaminas.excepciones.RecordJuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;

public class RecordJuegoConquista extends RecordJuego {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2961815999023337857L;
	private int cantidadBombas;
	public RecordJuegoConquista(String nombreMapa, Jugador jugador, int cantidadBombas) {
		super("Conquista", nombreMapa, jugador);
		this.setCantidadBombas(cantidadBombas);
	}
	public int getCantidadBombas() {
		return cantidadBombas;
	}
	private void setCantidadBombas(int cantidadBombas) {
		if( cantidadBombas < 0 )
			throw new RecordJuegoException("No se puede crear un RecordJuegoConquista con un cantidadBombas menor a cero");
		
		this.cantidadBombas = cantidadBombas;
	}
	@Override
	public String toString() {		
		return this.getIdRecordJuego() + " - " + this.getCantidadBombas() + " bombas";
	}
}
