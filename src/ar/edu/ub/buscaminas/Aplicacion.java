package ar.edu.ub.buscaminas;

import ar.edu.ub.buscaminas.menu.MenuPrincipal;
import ar.edu.ub.buscaminas.record.RecordJuegoRepository;

public class Aplicacion {
	
	public static void main(String[] args) {
		Consola consola = new Consola();
		
		new MenuPrincipal( consola, "./mapas/", new RecordJuegoRepository("./record/records.rec") ).mostrar();
				
		consola.close();
		
		
	}

}
