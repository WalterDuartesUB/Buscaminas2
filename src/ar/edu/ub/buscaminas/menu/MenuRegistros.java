package ar.edu.ub.buscaminas.menu;

import ar.edu.ub.buscaminas.Consola;
import ar.edu.ub.buscaminas.record.RecordJuegoRepository;

public class MenuRegistros {

	private Consola consola;
	private RecordJuegoRepository recordJuegoRepository;
	public MenuRegistros(Consola consola, RecordJuegoRepository recordJuegoRepository) {
		this.setConsola(consola);
		this.setRecordJuegoRepository(recordJuegoRepository);
	}

	public void mostrar() {
		this.getConsola().limpiarPantalla();
		this.getConsola().println("Records");
		this.getConsola().println("-------");
		this.getRecordJuegoRepository().print( this.getConsola() );
		this.getConsola().println("Enter para volver al menu principal");
		this.getConsola().nextLine();
		
	}

	private Consola getConsola() {
		return consola;
	}

	private void setConsola(Consola consola) {
		this.consola = consola;
	}

	private RecordJuegoRepository getRecordJuegoRepository() {
		return recordJuegoRepository;
	}

	private void setRecordJuegoRepository(RecordJuegoRepository recordJuegoRepository) {
		this.recordJuegoRepository = recordJuegoRepository;
	}

}
