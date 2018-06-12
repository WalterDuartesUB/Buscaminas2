package ar.edu.ub.buscaminas.tablero;

import java.util.Collection;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillasPrinter;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.listener.TableroListener;

public interface ITablero {
	public void setListener( TableroListener listener );
	public void imprimir( );
	public void setPrinter( CasillasPrinter printer );
	public int getCantidadBombasBocaAbajo();
	public int getCantidadBlancosYNumerosBocaAbajo();
		
	public void elegirCasilla( Jugador jugador, Coordenada coordenada );	
	public void ocultarCasilla(Casilla casilla);
	
	public void mostrarBlancosAlrededor( CasillaBlanco casilla);
	public void voltearTodasLasCasillasDelJugador(Jugador jugador);
	public Collection<Coordenada> obtenerCoordenadasDeCasillasContiguasDelJugador(Jugador jugador);
}
