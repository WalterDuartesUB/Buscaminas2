package ar.edu.ub.buscaminas.tablero;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.casilla.CasillasPrinter;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.casilla.FabricaCasilla;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasilla;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBlancasONumerosBocaArribaDeJugador;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBlanco;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBlancosYNumerosBocaAbajo;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBloqueadas;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBomba;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBombasBocaAbajo;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaNumero;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.listener.TableroListener;

public class Tablero implements ITablero {
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	
	private Map<Coordenada,Casilla> casillas;
	private TableroListener listener;
	private CasillasPrinter printer;
	
	public Tablero() {
		this.clean();
	}

	private Map<Coordenada,Casilla> getCasillas() {
		return casillas;
	}

	private void setCasillas(Map<Coordenada,Casilla> casillas) {
		this.casillas = casillas;
	}

	private TableroListener getListener() {
		return listener;
	}

	@Override
	public void setListener(TableroListener listener) {
		this.listener = listener;
	}

	public void mostrarBlancosAlrededor(CasillaBlanco casilla) {
		mostrarBlancosAlrededor(casilla.getJugador(),casilla, new TreeSet<Casilla>());
	}

	private void mostrarBlancosAlrededor(Jugador jugador,Casilla casilla, Set<Casilla> casillasProbadas) {			
		if( !casillasProbadas.add( casilla ) )
			return;
		
		casilla.voltearBocaArriba(jugador);
		
		if( this.getNumeros().contains( casilla ) )		
			return;	
		
		List<Casilla> casillasAlrededor = this.getCasillasAlrededor(casilla);
		
		List<Casilla> casillasBlancosYNumeros = new LinkedList<Casilla>();
		casillasBlancosYNumeros.addAll( this.getBlancos());
		casillasBlancosYNumeros.addAll( this.getNumeros());
		
		casillasAlrededor.retainAll( casillasBlancosYNumeros );
		
		for( Casilla casillaBlanco : casillasAlrededor ){			
			this.mostrarBlancosAlrededor( jugador, casillaBlanco, casillasProbadas );
		}

	}

	@Override
	public void elegirCasilla(Jugador jugador, Coordenada coordenada) {
		Casilla casilla = this.getCasilla( coordenada );
		
		casilla.voltearBocaArriba(jugador );
		
		casilla.elegiCasilla( this.getListener() );
	}

	private Casilla getCasilla(Coordenada coordenada) {
		return this.getCasillas().get(coordenada);
	}

	private void addCasilla(Casilla casilla) {
		this.getCasillas().put( casilla.getCoordenada(), casilla);		
	}
	
	public void add( CasillaBomba casilla ) {
		this.addCasilla(casilla);
	}

	public void add( CasillaBlanco casilla ) {
		this.addCasilla(casilla);
	}
	public void add( CasillaNumero casilla ) {
		this.addCasilla(casilla);
	}	
	
	private CasillasPrinter getPrinter() {
		return printer;
	}

	@Override
	public void setPrinter(CasillasPrinter printer) {
		this.printer = printer;
	}

	@Override
	public void imprimir() {
		Collection<Collection<Casilla>> casillas = new LinkedList<Collection<Casilla>>();		
		int filaAnterior = -1;
		List<Casilla> fila = null;
		TreeSet<Coordenada> coordenadas = new TreeSet<Coordenada>(this.getCasillas().keySet());
		
		for( Coordenada coordenada : coordenadas )
		{
			if( filaAnterior != coordenada.getFila() ) {
				fila = new LinkedList<Casilla>();
				casillas.add( fila );
				filaAnterior = coordenada.getFila();
			}
			
			fila.add( this.getCasillas().get( coordenada ) );
		}
				
		this.getPrinter().print(casillas);
		
	}

	public int getCantidadBombasBocaAbajo() {
		return this.obtenerCasillas( new CheckCasillaBombasBocaAbajo() ).size();
	}

	public int getCantidadBlancosYNumerosBocaAbajo() {
		return this.obtenerCasillas( new CheckCasillaBlancosYNumerosBocaAbajo() ).size();
	}

	private List<Casilla> getBombas() {
		return this.obtenerCasillas( new CheckCasillaBomba() );
	}

	private List<Casilla> getBlancos() {
		return this.obtenerCasillas( new CheckCasillaBlanco() );
	}

	private List<Casilla> getBloqueadas() {
		return this.obtenerCasillas( new CheckCasillaBloqueadas() );
	}
	private List<Casilla> getNumeros() {
		return this.obtenerCasillas( new CheckCasillaNumero() );
	}
	
	private List<Casilla> getCasillasBlancasONumerosBocaArribaDeJugador(Jugador jugador) {
		return this.obtenerCasillas( new CheckCasillaBlancasONumerosBocaArribaDeJugador( jugador ) );
	}	
	
	public void loadFromFile(String pathMapa, int porcentajeBombas ) {
		this.clean();
		
		try {
			List<String> lineas = Files.readAllLines( Paths.get( pathMapa ) );
			Map<String, FabricaCasilla> handlers = new HashMap<String, FabricaCasilla>();
			
			handlers.put("_", FabricaCasilla.CASILLA_BLANCA );
			handlers.put("X", FabricaCasilla.CASILLA_BLOQUEADA );
			
			for( int fila = 0; fila < lineas.size(); fila++ ){
				String linea = lineas.get(fila);
				for( int columna = 0; columna < linea.length(); columna++ ){
					this.addCasilla( handlers.get(String.format("%c", linea.charAt(columna) ) ).createInstance( fila,columna ) );
				}
			}
			
			///////////////////////////////////////////////////////////////////
			//Pongo las bombas en el mapa
			
			this.ponerBombas( this.getBlancos(), porcentajeBombas);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void clean() {
		this.setCasillas( new HashMap<Coordenada,Casilla>());
	}

	protected void load( int cantidadFilas, int cantidadColumnas, int porcentajeBombas ) {
		this.clean();
		
		//Agrego blancos en una matriz cuadrada
		for( int fila = 0; fila < cantidadFilas; fila++)
			for( int columna = 0; columna < cantidadColumnas; columna++)
				this.addCasilla( FabricaCasilla.CASILLA_BLANCA.createInstance( fila, columna) );
				
		this.ponerBombas( this.getBlancos(), porcentajeBombas);		
	}

	private void ponerBombas( List<Casilla> blancos, int porcentajeBombas) {
		int cantidadBombas = this.getCasillas().size() * porcentajeBombas / 100;
		
		while( this.getCantidadBombasBocaAbajo() < cantidadBombas )
		{
			int index = ThreadLocalRandom.current().nextInt( this.getBlancos().size() );
			this.add( new CasillaBomba( this.getBlancos().get(index) ) );			
		}
		
		//Pongo los blancos en el mapa
		this.ponerNumeros( blancos );
	}

	private void ponerNumeros( List<Casilla> blancos) {
		///////////////////////////////////////////////////////////////////
		//Pongo los numeros alrededor de mis bombas
		
		for( Casilla casilla : this.getBombas() ) {
			List<Casilla> casillasAlrededor = this.getCasillasAlrededor( casilla );
			
			casillasAlrededor.removeAll( this.getBloqueadas() );
			casillasAlrededor.removeAll( this.getBombas() );
			casillasAlrededor.remove( null );
			
			for( Casilla c : casillasAlrededor ) {
				this.add( new CasillaNumero( c, this.getCantidadBombasAlrededor(c) ) );						
			}
		}
	}

	private int getCantidadBombasAlrededor(Casilla casilla) {
		List<Casilla> casillasAlrededor = this.getCasillasAlrededor( casilla );
		casillasAlrededor.retainAll( this.getBombas());
		return casillasAlrededor.size();
	}

	protected List<Casilla> getCasillasBlancasONumerosAlrededor(Casilla casilla ) {
		List<Casilla> casillasAlrededor = this.getCasillasAlrededor( casilla );
		casillasAlrededor.removeAll(this.getBombas());
		casillasAlrededor.removeAll(this.getBloqueadas());
		return casillasAlrededor;
	}
	
	protected List<Casilla> getCasillasAlrededor(Casilla casillaCentral) {
		List<Casilla> casillas = new LinkedList<Casilla>();
		
		for( int fila = -1; fila < 2; fila ++)
			for( int columna = -1; columna < 2; columna++)
				if( this.getCasillas().get( new Coordenada(casillaCentral.getCoordenada()).sumar(fila, columna) ) != null )
					casillas.add(  this.getCasillas().get( new Coordenada(casillaCentral.getCoordenada()).sumar(fila, columna) ) ); 
		
		casillas.remove( casillaCentral );
		
		return casillas;
	}

	public void add(CasillaBloqueada casilla) {
		this.addCasilla(casilla);		
	}

	protected Casilla getCasilla(int fila, int columna) {
		return this.getCasilla( new Coordenada( fila, columna ));
	}

	@Override
	public void ocultarCasilla(Casilla casilla) {
		this.getCasilla( casilla.getCoordenada() ).voltearBocaAbajo();		
	}

	@Override
	public void voltearTodasLasCasillasDelJugador(Jugador jugador) {		
		for( Casilla casilla : this.getCasillas().values() )
			if( jugador.equals( casilla.getJugador() )) {
				casilla.voltearBocaAbajo();
			}
	}
	
	private List<Casilla> obtenerCasillas( CheckCasilla tester){
		List<Casilla> casillasEncontradas = new LinkedList<Casilla>();
		
		for( Casilla casilla : this.getCasillas().values() )
			if( tester.test(casilla))
				casillasEncontradas.add(casilla);	
		
		return casillasEncontradas;
	}

	@Override
	public Collection<Coordenada> obtenerCoordenadasDeCasillasContiguasDelJugador(Jugador jugador) {
		Collection<Coordenada> coordenadas = new LinkedList<Coordenada>();
		
		Collection<Casilla> casillasElegidasPorElJugador = this.getCasillasBlancasONumerosBocaArribaDeJugador( jugador );
		Collection<Casilla> casillasContiguasElegidasPorElJugador = this.getCasillasAlrededor( casillasElegidasPorElJugador );
		
		casillasContiguasElegidasPorElJugador.removeAll( casillasElegidasPorElJugador );
				
//		Set<Casilla> casillasElegibles = new TreeSet<Casilla>(  );
//		casillasElegibles.addAll( this.getCasillasAlrededor( casillasElegibles ) );
		
		for( Casilla casilla : casillasContiguasElegidasPorElJugador )			
			coordenadas.add( casilla.getCoordenada() );
		
		return coordenadas;
	}

	private Collection<Casilla> getCasillasAlrededor(Collection<Casilla> casillas) {
		Collection<Casilla> casillasAlrededor = new TreeSet<Casilla>();
		
		for( Casilla casilla : casillas )
			casillasAlrededor.addAll( this.getCasillasAlrededor(casilla) );
		
		return casillasAlrededor;
	}
}
