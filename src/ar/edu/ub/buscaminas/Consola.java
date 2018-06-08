package ar.edu.ub.buscaminas;

import java.awt.Color;
import java.util.Scanner;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;

public class Consola implements IConsola {

	private Scanner in;
	private ColoredPrinter cp;
	public Consola() {
		this.setIn( new Scanner(System.in) );
//		this.setCp( new ColoredPrinter.Builder(1, false).foreground(FColor.WHITE).background(BColor.BLUE).build() );
		this.setCp( new ColoredPrinter.Builder(1, false).build() );
	}
	@Override
	public void print() {
		this.print("");
	}

	@Override
	public void print(String s) {
		this.getCp().print(s);		
	}

	@Override
	public void println() {
		this.println("");		
	}

	@Override
	public void println(String s) {
		this.getCp().println(s);		
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
	public ColoredPrinter getCp() {
		return cp;
	}
	public void setCp(ColoredPrinter cp) {
		this.cp = cp;
	}
	@Override
	public void close() {
		this.getIn().close();
		this.getCp().clear();		
	}
	
	public void print(BColor bkColor,FColor fColor, String s) {
		this.getCp().clear();
//		this.setCp( new ColoredPrinter.Builder(1, false).foreground(FColor.WHITE).background( bkColor ).build() );
//		this.print( s );
		this.getCp().print(s, Attribute.NONE, fColor, bkColor);
		this.getCp().clear();
	}

}
