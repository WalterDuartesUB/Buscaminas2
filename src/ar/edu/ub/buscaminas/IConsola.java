package ar.edu.ub.buscaminas;

public interface IConsola {
	public void print();
	public void print(String s);
	public void println();
	public void println(String s);
	public void println(Object o);
	
	public String nextLine();
	public int 	  nextInt();
	public void limpiarPantalla();
}
