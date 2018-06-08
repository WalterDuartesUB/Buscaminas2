package ar.edu.ub.buscaminas.casilla;

public class Coordenada implements Comparable<Coordenada>{
	private int fila;
	public Coordenada(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	public Coordenada(Coordenada coordenada) {
		this( coordenada.getFila(), coordenada.getColumna() );
	}
	private int columna;
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columna;
		result = prime * result + fila;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenada other = (Coordenada) obj;
		if (columna != other.columna)
			return false;
		if (fila != other.fila)
			return false;
		return true;
	}
	@Override
	public int compareTo(Coordenada o) {
		int i = this.getFila() - o.getFila();
		
		if( i != 0)
			return i;
					
		return this.getColumna() - o.getColumna();
	}
	@Override
	public String toString() {
		return "Coordenada [fila=" + fila + ", columna=" + columna + "]";
	}
	public Coordenada sumar(int fila, int columna) {
		return new Coordenada( fila + this.getFila(), columna + this.getColumna());
	}
	
	
	
}
