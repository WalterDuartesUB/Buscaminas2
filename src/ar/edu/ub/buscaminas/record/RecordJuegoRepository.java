package ar.edu.ub.buscaminas.record;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import ar.edu.ub.buscaminas.Consola;
import ar.edu.ub.buscaminas.excepciones.RecordJuegoException;

public class RecordJuegoRepository {
	private String 					pathRecords;
	private Map<String,RecordJuego> records;
	
	public RecordJuegoRepository(String pathRecords ) {
		this.setPathRecords(pathRecords);
		this.setRecords( new HashMap<String, RecordJuego>());
	}
	
	@SuppressWarnings("unchecked")
	public void load() {
		try( ObjectInputStream inputStream = new ObjectInputStream( new FileInputStream( this.getPathRecords() ) ) ){
			this.setRecords( ( Map<String,RecordJuego> ) inputStream.readObject() );	
		} catch( FileNotFoundException | EOFException e ) { 
		} catch (IOException | ClassNotFoundException e) {			
			throw new RecordJuegoException( e.getMessage() );
		}
	}

	public void save() throws RecordJuegoException {		
		try( ObjectOutputStream outputStream = new ObjectOutputStream( new FileOutputStream( this.getPathRecords() ) ) ){
			outputStream.writeObject( this.getRecords());
		} catch (IOException e) {
			throw new RecordJuegoException( e.getMessage() );
		}
	}
	
	public void print( Consola consola ) {
		for( String tipoRecord : this.getRecords().keySet() )			
			consola.println( this.getRecords().get(tipoRecord) );
	}
	
	public void add( RecordJuegoConquista record ) throws RecordJuegoException {
		if( record == null )
			throw new RecordJuegoException("No se puede agregar un RecordJuego que sea null");
		
		this.agregar( record );
	}
	
	public void add( RecordJuegoSupervivenciaMultiplayer record ) throws RecordJuegoException {
		if( record == null )
			throw new RecordJuegoException("No se puede agregar un RecordJuego que sea null");
				
		this.agregar( record );
	}
		
	public void add( RecordJuegoSupervivenciaSingleplayer record ) throws RecordJuegoException {
		if( record == null )
			throw new RecordJuegoException("No se puede agregar un RecordJuego que sea null");
		
		this.agregar( record );
	}
	
	@SuppressWarnings("unchecked")
	private <T extends RecordJuego > void agregar( T record){
		Comparable<T> recordAnterior = (Comparable<T>)this.getRecords().get( record.getIdRecordJuego() );
		
		if( recordAnterior == null || recordAnterior.compareTo( record ) > 0)			
			this.agregarYGuardar(record);
	}

	private void agregarYGuardar(RecordJuego record) {
		this.getRecords().put( record.getIdRecordJuego(), record );
		this.save();
	}
	
	private Map<String,RecordJuego> getRecords() {
		return records;
	}

	private void setRecords(Map<String,RecordJuego> records) {
		this.records = records;
	}

	private String getPathRecords() {
		return pathRecords;
	}

	private void setPathRecords(String pathRecords) {
		this.pathRecords = pathRecords;
	}
	
}
