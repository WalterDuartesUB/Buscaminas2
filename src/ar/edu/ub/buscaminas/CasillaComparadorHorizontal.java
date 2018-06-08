package ar.edu.ub.buscaminas;

import java.util.Comparator;

import ar.edu.ub.buscaminas.casilla.Casilla;

public class CasillaComparadorHorizontal implements Comparator<Casilla> {
	@Override
	public int compare(Casilla arg0, Casilla arg1) {
		int i = arg0.getCoordenada().getColumna() - arg1.getCoordenada().getColumna();
		
		if( i != 0 )
			return i;
		
		return arg0.getCoordenada().getFila() - arg1.getCoordenada().getFila();
	}

}
