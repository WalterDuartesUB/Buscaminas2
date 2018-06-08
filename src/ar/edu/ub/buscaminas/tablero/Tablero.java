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
import ar.edu.ub.buscaminas.jugador.Jugador;
import ar.edu.ub.buscaminas.listener.TableroListener;

public class Tablero implements ITablero {
	private Map<Coordenada,Casilla> casillas;
	private List<CasillaBomba> bombas;
	private List<CasillaBlanco> blancos;
	private List<CasillaBloqueada> bloqueadas;
	private List<CasillaNumero> numeros;
	
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
				
		this.getBombas().remove( casilla );
		this.getBlancos().remove( casilla );
	}

	private Casilla getCasilla(Coordenada coordenada) {
		return this.getCasillas().get(coordenada);
	}

	private void addCasilla(Casilla casilla) {
		this.getCasillas().put( casilla.getCoordenada(), casilla);		
	}
	
	public void add( CasillaBomba casilla ) {
		this.getBombas().add(casilla);
		this.addCasilla(casilla);
	}

	public void add( CasillaBlanco casilla ) {
		this.getBlancos().add(casilla);
		this.addCasilla(casilla);
	}
	public void add( CasillaNumero casilla ) {
		this.getNumeros().add(casilla);
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
		return this.getBombas().size();
	}

	public int getCantidadBlancosYNumerosBocaAbajo() {
		return this.getBlancos().size() + this.getNumeros().size();
	}

	private List<CasillaBomba> getBombas() {
		return bombas;
	}

	private void setBombas(LinkedList<CasillaBomba> linkedList) {
		this.bombas = linkedList;
	}

	private List<CasillaBlanco> getBlancos() {
		return blancos;
	}

	private void setBlancos(List<CasillaBlanco> blancos) {
		this.blancos = blancos;
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
					handlers.get(String.format("%c", linea.charAt(columna) ) ).addIn( this, fila,columna );
				}
			}
			
			///////////////////////////////////////////////////////////////////
			//Pongo las bombas en el mapa
			
			this.ponerBombas(porcentajeBombas);			
			this.ponerNumeros();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void clean() {
		this.setCasillas( new HashMap<Coordenada,Casilla>());	
		this.setBombas(new LinkedList<CasillaBomba>());
		this.setBlancos(new LinkedList<CasillaBlanco>());
		this.setBloqueadas(new LinkedList<CasillaBloqueada>());
		this.setNumeros(new LinkedList<CasillaNumero>());				
	}

	protected void load( int cantidadFilas, int cantidadColumnas, int porcentajeBombas ) {
		this.clean();
		
		//Agrego blancos en una matriz cuadrada
		for( int fila = 0; fila < cantidadFilas; fila++)
			for( int columna = 0; columna < cantidadColumnas; columna++)
				FabricaCasilla.CASILLA_BLANCA.addIn( this, fila, columna);
				
		this.ponerBombas(porcentajeBombas);			
		this.ponerNumeros();		
	}

	private void ponerBombas(int porcentajeBombas) {
		int cantidadBombas = this.getCasillas().size() * porcentajeBombas / 100;
//		Random rand = new Random();
		
		while( this.getCantidadBombasBocaAbajo() < cantidadBombas )
		{
			int index = ThreadLocalRandom.current().nextInt( this.getBlancos().size() );
			this.add( new CasillaBomba( this.getBlancos().get(index) ) );
			this.getBlancos().remove(index);				
			
		}
	}

	private void ponerNumeros() {
		///////////////////////////////////////////////////////////////////
		//Pongo los numeros alrededor de mis bombas
		
		for( CasillaBomba casilla : this.getBombas() ) {
			List<Casilla> casillasAlrededor = this.getCasillasAlrededor( casilla );
			
			casillasAlrededor.removeAll( this.getBloqueadas() );
			casillasAlrededor.removeAll( this.getBombas() );
			casillasAlrededor.remove( null );
			
			for( Casilla c : casillasAlrededor ) {
				this.add( new CasillaNumero( c, this.getCantidadBombasAlrededor(c) ) );
				this.getBlancos().remove(c);						
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
		this.getBloqueadas().add( casilla );
		this.addCasilla(casilla);		
	}

	private List<CasillaBloqueada> getBloqueadas() {
		return bloqueadas;
	}

	private void setBloqueadas(List<CasillaBloqueada> bloqueadas) {		
		this.bloqueadas = bloqueadas;
	}

	private List<CasillaNumero> getNumeros() {
		return numeros;
	}

	private void setNumeros(List<CasillaNumero> numeros) {
		this.numeros = numeros;
	}

	protected Casilla getCasilla(int fila, int columna) {
		return this.getCasilla( new Coordenada( fila, columna ));
	}
}
