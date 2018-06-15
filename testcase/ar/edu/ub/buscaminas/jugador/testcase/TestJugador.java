package ar.edu.ub.buscaminas.jugador.testcase;




import ar.edu.ub.buscaminas.casilla.Coordenada;
import ar.edu.ub.buscaminas.excepciones.JugadorException;
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
		catch (JugadorException e)
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
		}catch (JugadorException e) {
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
		}catch(JugadorException e) {
			pudoCrearJugador = false;
		}
		
		assertFalse(pudoCrearJugador);
	}
	
	
	public void testCrearJugadorConObjetoJugador() {
		boolean pudoCrearJugador = false;
		
		try {
			new Jugador (new Jugador("asd"));
			pudoCrearJugador = true;
		}catch(Exception e ) {
			pudoCrearJugador = false;
		}
		
		assertTrue(pudoCrearJugador);
	}
	// Get alias
	
	public void testGetJugadorAlias() {
		String alias = "simba";
		Jugador jugador = new Jugador(alias);
		
		assertEquals(alias, jugador.getAlias());
	}
	
	public void testToStringJugador() {
		Jugador jugador = new Jugador("simba");
		String tostring = "Jugador [alias=simba]";
		assertEquals(tostring, jugador.toString());
		
	}
	
	public void testHashcodJugador() {
		Jugador jugador = new Jugador("simba");
		Jugador jugador2 = new Jugador("simba");
		
		assertTrue(jugador.hashCode() == jugador2.hashCode());
	}
	
	public void testEqualsMismoJugador(){
		Jugador jugador = new Jugador("simba");
		Jugador jugador2 = new Jugador("simba");
		
		assertTrue(jugador.equals(jugador2));
	}
	
	public void testEqualsDistintoJugador(){
		Jugador jugador = new Jugador("simba");
		Jugador jugador2 = new Jugador("pumba");
		
		assertFalse(jugador.equals(jugador2));
		
	}

	
	public void testEqualsJugadorOtroObjeto() {
		Coordenada coordenada = new Coordenada(1,1);
		Jugador jugador = new Jugador("asd");
		
		assertFalse(jugador.equals(coordenada));
	}
	
	public void testEqualsMismoObjetoJugador() {
		Jugador jugador = new Jugador("simba");
		assertTrue(jugador.equals(jugador));
	}

}


