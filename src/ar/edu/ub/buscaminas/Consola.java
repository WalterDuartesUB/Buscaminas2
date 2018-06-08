package ar.edu.ub.buscaminas;

import java.util.Scanner;

public class Consola implements IConsola {

	private Scanner in;
	public Consola() {
		this.setIn( new Scanner(System.in) );
	}
	@Override
	public void print() {
		this.print("");
	}

	@Override
	public void print(String s) {
		System.out.print(s);		
	}

	@Override
	public void println() {
		this.println("");		
	}

	@Override
	public void println(String s) {
		System.out.println(s);		
	}

	@Override
	public String nextLine() {
		return this.getIn().nextLine();
	}

	@Override
	public int nextInt() {
		return this.getIn().nextInt();
	}
	public Scanner getIn() {
		return in;
	}
	public void setIn(Scanner in) {
		this.in = in;
	}
	@Override
	public void println(Object o) {
		this.println(o.toString());		
	}
	@Override
	public void limpiarPantalla() {
		for( int i = 0; i < 30; i ++)
			System.out.println();
	}

}
