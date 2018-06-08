package ar.edu.ub.buscaminas;

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

import ar.edu.ub.buscaminas.casilla.Casilla;
import ar.edu.ub.buscaminas.casilla.CasillaBlanco;
import ar.edu.ub.buscaminas.casilla.CasillaBomba;
import ar.edu.ub.buscaminas.casilla.CasillaNumero;
import ar.edu.ub.buscaminas.casilla.CasillasPrinter;
import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.listener.TableroListener;

public class Tablero {
	private Map<Coordenada,Casilla> casillas;
	private List<CasillaBomba> bombas;
	private List<CasillaBlanco> blancos;
	private List<CasillaBloqueada> bloqueadas;
	private TableroListener listener;
	private CasillasPrinter printer;
	
	public Tablero() {
		this.setCasillas( new HashMap<Coordenada,Casilla>());	
		this.setBombas(new LinkedList<CasillaBomba>());
		this.setBlancos(new LinkedList<CasillaBlanco>());
		this.setBloqueadas(new LinkedList<CasillaBloqueada>());
	}

	public Map<Coordenada,Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(Map<Coordenada,Casilla> casillas) {
		this.casillas = casillas;
	}

	public TableroListener getListener() {
		return listener;
	}

	public void setListener(TableroListener listener) {
		this.listener = listener;
	}

	public void mostrarBlancosAlrededor(CasillaBlanco casilla) {
		mostrarBlancosAlrededor(casilla, new TreeSet<Casilla>());
	}

	public void mostrarBlancosAlrededor(Casilla casilla, Set<Casilla> casillasProbadas) {			
		if( !casillasProbadas.add( casilla ) )
			return;
		
		casilla.voltearBocaArriba();
		
		List<Casilla> casillas = this.getCasillasAlrededor(casilla);		
		casillas.retainAll( this.getBlancos() );
		
		for( Casilla casillaBlanco : casillas )			
			this.mostrarBlancosAlrededor( casillaBlanco, casillasProbadas );
	}

	public void elegirCasilla(Coordenada coordenada) {
		Casilla casilla = this.getCasilla( coordenada );
		
		casilla.voltearBocaArriba();
		
		this.getBombas().remove( casilla );
		this.getBlancos().remove( casilla );
		
		casilla.elegiCasilla( this.getListener() );
	}

	private Casilla getCasilla(Coordenada coordenada) {
		return this.getCasillas().get(coordenada);
	}

	public void addCasilla(Casilla casilla) {
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
		this.addCasilla(casilla);
	}	
	public CasillasPrinter getPrinter() {
		return printer;
	}

	public void setPrinter(CasillasPrinter printer) {
		this.printer = printer;
	}

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
		return this.getBlancos().size();
	}

	public List<CasillaBomba> getBombas() {
		return bombas;
	}

	public void setBombas(LinkedList<CasillaBomba> linkedList) {
		this.bombas = linkedList;
	}

	public List<CasillaBlanco> getBlancos() {
		return blancos;
	}

	public void setBlancos(List<CasillaBlanco> blancos) {
		this.blancos = blancos;
	}

	public void loadFromFile(String pathMapa, int porcentajeBombas ) {

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

	private void ponerBombas(int porcentajeBombas) {
		int cantidadBombas = this.getCasillas().size() * porcentajeBombas / 100;
		Random rand = new Random();
		
		while( this.getCantidadBombasBocaAbajo() < cantidadBombas )
		{
			int index = rand.nextInt( this.getBlancos().size() );
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

	private List<Casilla> getCasillasAlrededor(Casilla casillaCentral) {
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

	public List<CasillaBloqueada> getBloqueadas() {
		return bloqueadas;
	}

	public void setBloqueadas(List<CasillaBloqueada> bloqueadas) {		
		this.bloqueadas = bloqueadas;
	}

}
