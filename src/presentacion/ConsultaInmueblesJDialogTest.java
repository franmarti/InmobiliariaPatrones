package presentacion;

import static org.junit.Assert.*;

import java.util.Iterator;

import logica.Inmueble;

import org.junit.Test;

import excepciones.LogicaExcepcion;

public class ConsultaInmueblesJDialogTest {

	@Test
	public void testCargaInmuebles() throws LogicaExcepcion {
		FachadaPresentacion fachada = FachadaPresentacion.getInstance();
		Iterator<Inmueble> iterator =  fachada.encontrarInmuebles().iterator();
		assertNotNull(iterator);
	}

}
