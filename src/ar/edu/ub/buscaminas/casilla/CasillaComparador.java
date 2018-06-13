package ar.edu.ub.buscaminas.casilla;

import java.util.Comparator;

public class CasillaComparador implements Comparator<Casilla> {
	public enum CriterioOrdenamiento{
		FILA_ASC_COL_ASC {
			@Override
			public int compare(Casilla casilla, Casilla otraCasilla) {
				int i = casilla.getCoordenada().getFila() - otraCasilla.getCoordenada().getFila();
				
				if( i != 0 )
					return i;
				
				return casilla.getCoordenada().getColumna() - otraCasilla.getCoordenada().getColumna();
			}
		},
		FILA_DESC_COL_DESC {
			@Override
			public int compare(Casilla casilla, Casilla otraCasilla) {
				return -1 * CriterioOrdenamiento.FILA_ASC_COL_ASC.compare(casilla, otraCasilla);
			}
		},
		COL_ASC_FILA_ASC {
			@Override
			public int compare(Casilla casilla, Casilla otraCasilla) {
				int i = casilla.getCoordenada().getColumna() - otraCasilla.getCoordenada().getColumna();
				
				if( i != 0 )
					return i;
				
				return casilla.getCoordenada().getFila() - otraCasilla.getCoordenada().getFila();
			}
		},
		COL_DESC_FILA_DESC {
			@Override
			public int compare(Casilla casilla, Casilla otraCasilla) {
				return -1 * CriterioOrdenamiento.COL_ASC_FILA_ASC.compare(casilla, otraCasilla);
			}
		}, FILA_ASC {
			@Override
			public int compare(Casilla casilla, Casilla otraCasilla) {				
				return casilla.getCoordenada().getFila() - otraCasilla.getCoordenada().getFila();
			}
		}, COL_ASC {
			@Override
			public int compare(Casilla casilla, Casilla otraCasilla) {
				return casilla.getCoordenada().getColumna() - otraCasilla.getCoordenada().getColumna();
			}
		};
		
		public abstract int compare( Casilla casilla, Casilla otraCasilla );
		
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private CriterioOrdenamiento criterio;
	
	public CasillaComparador( CriterioOrdenamiento criterio ) {
		this.setCriterio(criterio);
	}
	
	@Override
	public int compare(Casilla casilla, Casilla otraCasilla) {
		return this.getCriterio().compare(casilla, otraCasilla );
	}

	private CriterioOrdenamiento getCriterio() {
		return criterio;
	}

	private void setCriterio(CriterioOrdenamiento criterio) {
		this.criterio = criterio;
	}	

}
