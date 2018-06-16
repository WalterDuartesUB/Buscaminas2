package ar.edu.ub.buscaminas.juego;

import java.util.Collection;
import java.util.LinkedList;

import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.excepciones.JuegoException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.record.RecordJuego;
import ar.edu.ub.buscaminas.record.RecordJuegoSupervivenciaSingleplayer;
import ar.edu.ub.buscaminas.tablero.ITablero;

public class JuegoSupervivenciaSingleplayer extends Juego {
	private String nombreDificultad;
	public JuegoSupervivenciaSingleplayer(ITablero tablero, String nombreDificultad, Jugador jugador) {
		super( tablero, jugador );
		this.setNombreDificultad(nombreDificultad);
	}

	@Override
	public void elegiCasilla(CasillaBomba casilla) {		
		this.matarJugadorDeTurno();		
		this.mostrarPerdedor();
	}

	@Override
	public void elegiCasilla(CasillaBlanco casilla) {
		this.getTablero().mostrarBlancosAlrededor( casilla );
				
		if( this.getTablero().getCantidadBlancosYNumerosBocaAbajo() == 0 ) {
			this.mostrarGanador(this.getJugadorDeTurno());			
			this.getRecordJuegoRepository().add( new RecordJuegoSupervivenciaSingleplayer(this.getTablero().getNombreMapa(), this.getNombreDificultad(), this.getJugadorDeTurno(), super.getSegundosDuracionPartida() ));
		}

	}

	@Override
	public void elegiCasilla(CasillaNumero casilla) {		
		if( this.getTablero().getCantidadBlancosYNumerosBocaAbajo() == 0 )
			this.mostrarGanador(this.getJugadorDeTurno());	
	}

	@Override
	public boolean terminoJuego() {
		return ( this.getJugadores().size() == 0 ) || ( this.getTablero().getCantidadBlancosYNumerosBocaAbajo() == 0 );
	}

	public static int cantidadMinimaJugadores() {
		return 1;
	}

	public static int cantidadMaximaJugadores() {
		return 1;
	}
	
	@Override
	protected void validarJuego() {
		if( this.getJugadores().size() > JuegoSupervivenciaSingleplayer.cantidadMaximaJugadores() || this.getJugadores().size() < JuegoSupervivenciaSingleplayer.cantidadMinimaJugadores() )
			throw new JuegoException("No se puede iniciar un juego en modo supervivencia singleplayer con " + this.getJugadores().size() + ". El minimo es " + JuegoSupervivenciaSingleplayer.cantidadMinimaJugadores() + " y el maximo es " + JuegoSupervivenciaSingleplayer.cantidadMaximaJugadores() );
		
	}

	public String getNombreDificultad() {
		return nombreDificultad;
	}

	private void setNombreDificultad(String nombreDificultad) {
		if( nombreDificultad == null || nombreDificultad.isEmpty() )
			throw new JuegoException("No se puede crear un JuegoSupervivenciaSingleplayer con nombreDificultad null o vacio");
		
		this.nombreDificultad = nombreDificultad;
	}

	@Override
	protected Collection<RecordJuego> getRecords() {
		return new LinkedList<RecordJuego>();
	}
}
