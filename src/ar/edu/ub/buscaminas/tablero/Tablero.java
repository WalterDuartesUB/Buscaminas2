package ar.edu.ub.buscaminas.tablero;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBloqueada;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaComparador;
import ar.edu.ub.buscaminas.casilla.CasillaComparador.CriterioOrdenamiento;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.casilla.CasillasPrinter;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.casilla.FabricaCasilla;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasilla;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBlancasONumerosBocaArribaDeJugador;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBlanco;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBlancosYNumeros;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBlancosYNumerosBocaAbajo;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBloqueadas;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBomba;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaBombasBocaAbajo;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaNumero;
import ar.edu.ub.buscaminas.casilla.checkers.CheckCasillaTomadaPorJugador;
import ar.edu.ub.buscaminas.excepciones.CoordenadaInvalidaException;
import ar.edu.ub.buscaminas.excepciones.TableroException;
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.listener.TableroListener;

public class Tablero implements ITablero {
	
	private static final int CANTIDAD_MINIMA_CASILLAS = 64;
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
	public void elegirCasilla(Jugador jugador, Coordenada coordenada) throws CoordenadaInvalidaException {
		Casilla casilla = this.getCasilla( coordenada );
		
		casilla.voltearBocaArriba();
		
		casilla.elegiCasilla( this.getListener() );
				
		casilla.setJugador( jugador );
	}

	private Casilla getCasilla(Coordenada coordenada) throws CoordenadaInvalidaException {
		if( this.getCasillas().get(coordenada) == null )
			throw new CoordenadaInvalidaException("La coordenada " + coordenada + " no existe en el tablero. ");
		
		return this.getCasillas().get(coordenada);
	}

	private void addCasilla(Casilla casilla) {
		this.getCasillas().put( casilla.getCoordenada(), casilla);		
	}
	
	protected void add( CasillaBomba casilla ) {
		this.addCasilla(casilla);
	}

	protected void add( CasillaBlanco casilla ) {
		this.addCasilla(casilla);
	}
	protected void add( CasillaNumero casilla ) {
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
		this.getPrinter().print(this.getCasillasAsList());		
	}

	private List<List<Casilla>> getCasillasAsList() {
		List<List<Casilla>> casillas = new LinkedList<List<Casilla>>();		
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
		return casillas;
	}

	public int getCantidadBombasBocaAbajo() {
		return this.obtenerCasillas( new CheckCasillaBombasBocaAbajo() ).size();
	}

	public int getCantidadBlancosYNumerosBocaAbajo() {
		return this.obtenerCasillas( new CheckCasillaBlancosYNumerosBocaAbajo() ).size();
	}

	private Map<Coordenada,Casilla> getBlancosYNumeros() {
		return this.obtenerCasillasAsMap( new CheckCasillaBlancosYNumeros() );
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
	
	public void loadFromFile(String pathMapa, int porcentajeBombas ) throws TableroException {
		this.clean();
		
		try {
			List<String> lineas = Files.readAllLines( Paths.get( pathMapa ) );
			Map<String, FabricaCasilla> handlers = new HashMap<String, FabricaCasilla>();
			
			handlers.put("_", FabricaCasilla.CASILLA_BLANCA );
			handlers.put("X", FabricaCasilla.CASILLA_BLOQUEADA );
			
			for( int fila = 0; fila < lineas.size(); fila++ ){
				String linea = lineas.get(fila);
				for( int columna = 0; columna < linea.length(); columna++ ){
					
					if( handlers.get(String.format("%c", linea.charAt(columna) ) ) == null )
						throw new TableroException("El caracter " + linea.charAt(columna) + "no es valido para cargar un mapa.");
					
					this.addCasilla( handlers.get(String.format("%c", linea.charAt(columna) ) ).createInstance( fila,columna ) );
				}
			}
			
			this.validarTablero();
			
			///////////////////////////////////////////////////////////////////
			//Pongo las bombas en el mapa
			
			this.ponerBombas( this.getBlancos(), porcentajeBombas);
			
		} catch (IOException e) {
			throw new TableroException( "Ocurrio un problema al tratar de cargar el mapa desde el archivo " + pathMapa );
		}
		
	}
	
	private void validarTablero()  throws TableroException {
		List<List<Casilla>> casillas = this.getCasillasAsList();
		
		int cantidadColumnas = 0;		
		int cantidadCasillas = 0;
		int cantidadFilas = casillas.size();
		
		for( List<Casilla> filas : casillas ){
			cantidadColumnas = Math.max( cantidadColumnas, filas.size() );
			cantidadCasillas += filas.size();
		}
		
		if( cantidadColumnas > cantidadFilas * 2 )
			throw new TableroException("La cantidad de columnas no puede superar el 100% de la cantidad de filas");
		
		if(  cantidadFilas > cantidadColumnas* 2 )
			throw new TableroException("La cantidad de filas no puede superar el 100% de la cantidad de columnas");
		
		if( cantidadCasillas < CANTIDAD_MINIMA_CASILLAS)
			throw new TableroException("La cantidad de casillas no puede ser menor que " + CANTIDAD_MINIMA_CASILLAS );
	}

	private void clean() {
		this.setCasillas( new HashMap<Coordenada,Casilla>());
	}

	protected void load( int cantidadFilas, int cantidadColumnas, int porcentajeBombas ) throws TableroException {
		this.clean();
		
		if( cantidadFilas < 0 )
			throw new TableroException( "La cantidad de filas para crear el tablero debe ser mayor que 0");
		
		if( cantidadColumnas < 0 )
			throw new TableroException( "La cantidad de columnas para crear el tablero debe ser mayor que 0");
		
		if( porcentajeBombas < 0  || porcentajeBombas > 100 )
			throw new TableroException( "El porcentaje de bombas para crear el tablero debe ser mayor que 0 y menor que 100");		
		
		//Agrego blancos en una matriz cuadrada
		for( int fila = 0; fila < cantidadFilas; fila++)
			for( int columna = 0; columna < cantidadColumnas; columna++)
				this.addCasilla( FabricaCasilla.CASILLA_BLANCA.createInstance( fila, columna) );
				
		this.validarTablero();
		
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
		return this.getCasillasAlrededor(casillaCentral, this.getCasillas() );	
	}
	
	protected List<Casilla> getCasillasAlrededor(Casilla casillaCentral, Map<Coordenada,Casilla> todasLasCasillas) {
		List<Casilla> casillas = new LinkedList<Casilla>();
		
		for( int fila = -1; fila < 2; fila ++)
			for( int columna = -1; columna < 2; columna++) {
				Coordenada coordenadaCasilla = new Coordenada( casillaCentral.getCoordenada() ).sumar(fila, columna);
				
				if( todasLasCasillas.containsKey( coordenadaCasilla ) )
					casillas.add(  todasLasCasillas.get( coordenadaCasilla ) ); 
			}
		
		casillas.remove( casillaCentral );
		
		return casillas;
	}

	public void add(CasillaBloqueada casilla) {
		this.addCasilla(casilla);		
	}

	protected Casilla getCasilla(int fila, int columna) throws CoordenadaInvalidaException {
		return this.getCasilla( new Coordenada( fila, columna ));
	}

	@Override
	public void ocultarCasilla(Casilla casilla) throws CoordenadaInvalidaException {
		this.getCasilla( casilla.getCoordenada() ).voltearBocaAbajo();		
	}

	@Override
	public void voltearTodasLasCasillasDelJugador(Jugador jugador, Casilla casillaTomada ) {
		List<Casilla> casillasDelJugador = new LinkedList<Casilla>( this.getCasillas().values() );
		
		casillasDelJugador.remove( casillaTomada );
		
		for( Casilla casilla : casillasDelJugador )
			if( jugador.equals( casilla.getJugador() )) {
				casilla.voltearBocaAbajo();
			}
	}
	
	private List<Casilla> obtenerCasillas( CheckCasilla tester){
		return new LinkedList<Casilla>( this.obtenerCasillasAsMap(tester).values() );
	}
	
	private Map<Coordenada, Casilla> obtenerCasillasAsMap( CheckCasilla tester){
		Map<Coordenada, Casilla> casillasEncontradas = new HashMap<Coordenada, Casilla>();
		
		for( Casilla casilla : this.getCasillas().values() )
			if( tester.test(casilla))
				casillasEncontradas.put( casilla.getCoordenada(), casilla);	
		
		return casillasEncontradas;
	}

	@Override
	public Collection<Coordenada> obtenerCoordenadasDeCasillasContiguasDelJugador(Jugador jugador) {
		Collection<Coordenada> coordenadas = new LinkedList<Coordenada>();
		
		Collection<Casilla> casillasElegidasPorElJugador = this.getCasillasBlancasONumerosBocaArribaDeJugador( jugador );
		Collection<Casilla> casillasContiguasElegidasPorElJugador = this.getCasillasAlrededor( casillasElegidasPorElJugador );
		
		casillasContiguasElegidasPorElJugador.removeAll( casillasElegidasPorElJugador );
		
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
	
	public boolean existenTodosLosCaminos() {
		return  this.existeCaminoVerticalDescendente() && this.existeCaminoVerticalAscendente() && this.existeCaminoHorizontalDescendente() && this.existeCaminoHorizontalAscendente();
	}
	
	
	private Map<Coordenada, Casilla> getCasillasDelJugador(Jugador jugador) {
		return this.obtenerCasillasAsMap( new CheckCasillaTomadaPorJugador( jugador ) );
	}

	private boolean existeCaminoVerticalDescendente() {		
		return this.existeCaminoDesde(new Coordenada( 0, this.getCasillasAsList().get(0).size() / 2), new CasillaComparador( CriterioOrdenamiento.FILA_DESC_COL_DESC ),  new CasillaComparador( CriterioOrdenamiento.FILA_ASC ) );
	}

	private boolean existeCaminoVerticalAscendente() {		
		List<List<Casilla>> casillas = this.getCasillasAsList();
		return this.existeCaminoDesde(new Coordenada( casillas.size() - 1, casillas.get(0).size() / 2), new CasillaComparador( CriterioOrdenamiento.FILA_ASC_COL_ASC),  new CasillaComparador( CriterioOrdenamiento.FILA_ASC ) );
	}
	
	private boolean existeCaminoHorizontalDescendente() {		
		return this.existeCaminoDesde(new Coordenada( this.getCasillasAsList().size() / 2, 0), new CasillaComparador( CriterioOrdenamiento.COL_DESC_FILA_DESC ),  new CasillaComparador( CriterioOrdenamiento.COL_ASC ) );
	}
	
	private boolean existeCaminoHorizontalAscendente() {		
		List<List<Casilla>> casillas = this.getCasillasAsList();
		return this.existeCaminoDesde(new Coordenada( casillas.size() / 2, casillas.get(0).size() - 1), new CasillaComparador( CriterioOrdenamiento.COL_ASC_FILA_ASC ),  new CasillaComparador( CriterioOrdenamiento.COL_ASC ) );
	}
	
	private boolean existeCaminoDesde( Coordenada coordenada, Comparator<Casilla> comparadorDeCasillas, Comparator<Casilla> comparadorCasillaLLegada ) {
		return this.existeCaminoDesde(coordenada, this.getBlancosYNumeros(), comparadorDeCasillas, comparadorCasillaLLegada);
	}
	
	private boolean existeCaminoDesde( Coordenada coordenada, Map<Coordenada, Casilla> casillasDondeBuscarCamino, Comparator<Casilla> comparadorDeCasillas, Comparator<Casilla> comparadorCasillaLLegada ) {
		
		try {
			return this.existeCaminoDesde( this.getCasilla(coordenada), casillasDondeBuscarCamino, comparadorDeCasillas, comparadorCasillaLLegada );
		} catch (CoordenadaInvalidaException e) {		
		}
		
		return false;
	}
	
	private boolean existeCaminoDesde( Casilla casilla, Map<Coordenada, Casilla> casillasDondeBuscarCamino, Comparator<Casilla> comparadorDeCasillas, Comparator<Casilla> comparadorCasillaLLegada ) {
		
		Map<Coordenada, Casilla> casillasBlancosONumeros = this.getBlancosYNumeros();
		
		//Si la coordenada inicial no es un blanco o un numero, no lo dejo continuar
		if( !casillasBlancosONumeros.containsValue( casilla ) )
			return false;
				
		TreeSet<Casilla> caminoDeCasillas = this.obtenerTodasLasCasillasContiguas( casilla, casillasDondeBuscarCamino, comparadorDeCasillas );
		TreeSet<Casilla> casillasTableroConCriterio = new TreeSet<Casilla>( comparadorDeCasillas );
		
		casillasTableroConCriterio.addAll( casillasBlancosONumeros.values() );
		
		return comparadorCasillaLLegada.compare( caminoDeCasillas.first(), casillasTableroConCriterio.first() ) == 0;
	}

	private TreeSet<Casilla> obtenerTodasLasCasillasContiguas(Casilla casilla, Map<Coordenada, Casilla> casillasDondeBuscarCamino, Comparator<Casilla> comparadorDeCasillas) {
		TreeSet<Casilla> caminoDeCasillas = new TreeSet<Casilla>(comparadorDeCasillas);
		
		this.obtenerTodasLasCasillasContiguas(casilla, casillasDondeBuscarCamino, caminoDeCasillas);		
		
		return caminoDeCasillas;
	}

	private void obtenerTodasLasCasillasContiguas(Casilla casilla, Map<Coordenada, Casilla> casillasDondeBuscarCamino, TreeSet<Casilla> caminoDeCasillas) {
		if( !caminoDeCasillas.add( casilla ) )
			return;
		
		caminoDeCasillas.add( casilla );
		
		List<Casilla> casillasAlrededor = this.getCasillasAlrededor(casilla, casillasDondeBuscarCamino );
				
		for( Casilla casillaBlanco : casillasAlrededor ){			
			this.obtenerTodasLasCasillasContiguas( casillaBlanco, casillasDondeBuscarCamino, caminoDeCasillas );
		}
		
	}

	@Override
	public boolean existeCaminoParaElJugador(Coordenada coordenada) {
		//Pido la casilla de la coordenada		
		try {
			Casilla casilla = this.getCasilla(coordenada);
				
			//Pido todas las casillas del jugador de esa coordenada
			Map<Coordenada, Casilla> casillasJugador = this.getCasillasDelJugador( casilla.getJugador() );
					
			//Segun la orientacion de la coordenada, salgo a buscar el camino		
			if( coordenada.getFila() == 0 )
				return this.existeCaminoDesde(coordenada, casillasJugador, new CasillaComparador( CriterioOrdenamiento.FILA_DESC_COL_DESC ),  new CasillaComparador( CriterioOrdenamiento.FILA_ASC ) );
			else if( coordenada.getColumna() == 0 )
				return this.existeCaminoDesde(coordenada, casillasJugador, new CasillaComparador( CriterioOrdenamiento.COL_DESC_FILA_DESC ),  new CasillaComparador( CriterioOrdenamiento.COL_ASC ) );
			else if( coordenada.getColumna() < coordenada.getFila() )
				return this.existeCaminoDesde(coordenada, casillasJugador, new CasillaComparador( CriterioOrdenamiento.FILA_ASC_COL_ASC ),  new CasillaComparador( CriterioOrdenamiento.FILA_ASC ) );
			
			return this.existeCaminoDesde(coordenada, casillasJugador, new CasillaComparador( CriterioOrdenamiento.COL_ASC_FILA_ASC ),  new CasillaComparador( CriterioOrdenamiento.COL_ASC ) );		
		} catch (CoordenadaInvalidaException e) {		
		}
		
		return false;
	}	
}
