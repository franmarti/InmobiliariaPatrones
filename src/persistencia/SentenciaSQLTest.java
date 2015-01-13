package persistencia;

import static org.junit.Assert.*;

import org.junit.Test;

import excepciones.DAOExcepcion;

public class SentenciaSQLTest {

	@Test
	public void testConexionBD() throws ClassNotFoundException, DAOExcepcion {
		SentenciaSQL manejador = SentenciaSQL.getInstance();
		manejador.conexionBD();
		assertNotNull(manejador.connManager);
	}
}
