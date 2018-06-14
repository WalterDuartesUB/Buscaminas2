package ar.edu.ub.buscaminas.tablero;

import java.util.Collection;
import java.util.Map;
import java.util.Queue;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillasPrinter;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.listener.TableroListener;

public interface ITablero {
	public void setListener( TableroListener listener );
	public void imprimir( );
	public void setPrinter( CasillasPrinter printer );
	public int getCantidadBombasBocaAbajo();
	public int getCantidadBlancosYNumerosBocaAbajo();
		
	public void elegirCasilla( Jugador jugador, Coordenada coordenada ) throws CoordenadaInvalidaException;	
	public void ocultarCasilla(Casilla casilla) throws CoordenadaInvalidaException;
	
	public void mostrarBlancosAlrededor( CasillaBlanco casilla);
	public void voltearTodasLasCasillasDelJugador(Jugador jugador, Casilla casilla);
	public Collection<Coordenada> obtenerCoordenadasDeCasillasContiguasDelJugador(Jugador jugador);
	public boolean existeCaminoParaElJugador(Coordenada coordenada);
	public Coordenada getCoordenadaMedioSuperior();
	public Coordenada getCoordenadaMedioDerecha();	
	public Coordenada getCoordenadaMedioInferior();
	public Coordenada getCoordenadaMedioIzquierda();
	public Map<Jugador, Integer> obtenerCantidadCasillasDescubiertasPorJugador(Collection<Jugador> jugadores);
}
