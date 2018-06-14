package ar.edu.ub.buscaminas.jugador.testcase;



import ar.edu.ub.buscaminas.jugador.Jugador;
import junit.framework.TestCase;

public class TestJugador extends TestCase {

	
	// Jugador Null
	public void testCrearJugadorConAliasNull()
	{
		String alias = null;
		boolean pudoCrearJugador = false;
		try
		{
			new Jugador(alias);
			pudoCrearJugador = true;
		}
		catch (Exception e)
		{
			pudoCrearJugador = false;
		}
		
		assertFalse(pudoCrearJugador);
	}
	
	// Jugador con alias vacio
	
	public void testCrearJugadorConAliasVacio() {
		String alias = "";
		boolean pudoCrearJugador = false;
		try {
			new Jugador(alias);
			pudoCrearJugador = true;
		}catch (Exception e) {
			pudoCrearJugador = false;
		}
		
		assertFalse(pudoCrearJugador);
	}
	
	// Jugador con un espacio en blanco
	
	public void testCrearJugadorConEspacioBlanco() {
		String alias = " ";
		boolean pudoCrearJugador = false;
		
		try {
			new Jugador (alias);
			pudoCrearJugador = true;
		}catch(Exception e) {
			pudoCrearJugador = false;
		}
		
		assertFalse(pudoCrearJugador);
	}
	
	// Get alias
	
	public void testGetJugadorAlias() {
		String alias = "simba";
		Jugador jugador = new Jugador(alias);
		
		assertEquals(alias, jugador.getAlias());
	}
	

}


