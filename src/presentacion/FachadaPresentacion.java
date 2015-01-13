package presentacion;


import java.util.ArrayList;

import logica.Adosado;
import logica.Asesor;
import logica.BajoComercial;
import logica.CasaPueblo;
import logica.Cliente;
import logica.Controlador;
import logica.FabricaInmueble;
import logica.Inmueble;
import logica.NaveIndustrial;
import logica.Oferta;
import logica.Piso;
import logica.PlazaGaraje;
import logica.Transaccion;
import logica.Visita;
import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

public class FachadaPresentacion {
	
	private static FachadaPresentacion fachada = null; 
	private FabricaInmueble fabrica = null;
	private Controlador control = null;

	private FachadaPresentacion() throws LogicaExcepcion{
		fabrica = FabricaInmueble.getInstance();
		control = Controlador.getInstance();
	}
	
	public static FachadaPresentacion getInstance() throws LogicaExcepcion{
		if(fachada == null){
			fachada = new FachadaPresentacion();
		}
		return fachada;
	}
	

	@SuppressWarnings("rawtypes")
	public void crearPiso(ArrayList lista) throws LogicaExcepcion{
		control.crearPiso((Piso) fabrica.crearInmueble(lista));
	}

	@SuppressWarnings("rawtypes")
	public void crearNaveIndustrial(ArrayList lista) throws LogicaExcepcion{
		control.crearNaveIndustrial((NaveIndustrial)fabrica.crearInmueble(lista));
	}
	
	@SuppressWarnings("rawtypes")
	public BajoComercial crearBajoComercial(ArrayList lista){
		return (BajoComercial) fabrica.crearInmueble(lista);
	}
	
	@SuppressWarnings("rawtypes")
	public CasaPueblo crearCasaPueblo(ArrayList lista){
		return (CasaPueblo) fabrica.crearInmueble(lista);
	}
	
	@SuppressWarnings("rawtypes")
	public Adosado crearAdosado(ArrayList lista){
		return (Adosado) fabrica.crearInmueble(lista);
	}
	
	@SuppressWarnings("rawtypes")
	public PlazaGaraje crearPlazaGaraje(ArrayList lista){
		return (PlazaGaraje) fabrica.crearInmueble(lista);
	}
	
	@SuppressWarnings("rawtypes")
	public Inmueble crearInmueble(ArrayList lista){
		return (Inmueble) fabrica.crearInmueble(lista);
	}
	
	@SuppressWarnings("rawtypes")
	public void crearOferta(ArrayList lista) throws LogicaExcepcion{
		control.crearOferta(new Oferta(lista.get(0).toString(),lista.get(1).toString(),lista.get(2).toString(),(Visita)lista.get(3)));
	}
	
	@SuppressWarnings("rawtypes")
	public void crearVisita(ArrayList lista) throws LogicaExcepcion{
		control.crearVisita(new Visita(lista.get(0).toString(),lista.get(1).toString(),(Inmueble)lista.get(2),(Cliente)lista.get(3),(Asesor)lista.get(4)));
	}
	
	@SuppressWarnings("rawtypes")
	public void crearTransaccion(ArrayList lista, String id) throws LogicaExcepcion{
		control.crearTransaccion(new Transaccion(lista.get(0).toString(),lista.get(1).toString(),lista.get(2).toString(),(Oferta)lista.get(3)), id);
	}
	
	public void crearCliente(ArrayList<String> lista) throws LogicaExcepcion{
		control.crearCliente(new Cliente(lista.get(0).toString(),lista.get(1).toString(),lista.get(2).toString()));
	}
		
	public void crearAsesor(ArrayList<String> lista) throws LogicaExcepcion{
		control.crearAsesor(new Asesor (lista.get(0).toString(),lista.get(1).toString(),lista.get(2).toString()));
	}
	
	public ArrayList<Inmueble> encontrarInmuebles() throws LogicaExcepcion{
		return control.encontrarInmuebles();
	}
	
	public ArrayList<Inmueble> encontrarInmueblesPorAsesor(String cod) throws LogicaExcepcion{
		return control.encontrarInmueblesPorAsesor(cod);
	}
	
	public ArrayList<Asesor> encontrarAsesores() throws LogicaExcepcion{
		return control.encontrarAsesores();
	}
	
	public ArrayList<Inmueble> encontrarInmueblesPorCliente(String cod) throws LogicaExcepcion{
		return control.encontrarInmueblesPorCliente(cod);
	}
	
	public ArrayList<Cliente> encontrarClientes() throws LogicaExcepcion{
		return control.encontrarClientes();
	}
	
	public ArrayList<Transaccion> encontrarTransacciones() throws LogicaExcepcion{
		return control.encontrarTransacciones();
	}
	
	public ArrayList<Visita> encontrarVisitasPorInmueble(String inmueble) throws LogicaExcepcion{
		return control.encontrarVisitasPorInmueble(inmueble);
	}
	
	public ArrayList<Oferta> encontrarOfertaPorVisita(String cod) throws DAOExcepcion{
		return control.encontrarOfertaPorVisita(cod);
	}
	
	public Asesor encontrarAsesorPorCod(String cod) throws LogicaExcepcion{
		return control.encontrarAsesorPorCod(cod);
	}
	
	public Cliente encontrarClientePorCod(String cod) throws LogicaExcepcion{
		return control.encontrarClientePorCod(cod);
	}
	
	public ArrayList<Visita> encontrarVisitasPorAsesor(String asesor) throws LogicaExcepcion{
		return control.encontrarVisitasPorAsesor(asesor);
	}
	
	public Visita encontrarVisitaPorCod(String cod) throws LogicaExcepcion{
		return control.encontrarVisitaPorCod(cod);
	}
	
	public Inmueble encontrarInmueblePorCod(String cod) throws LogicaExcepcion{
		return control.encontrarInmueblePorCod(cod);
	}
	
	public Oferta encontrarOfertaPorCod(String cod) throws LogicaExcepcion{
		return control.encontrarOfertaPorCod(cod);
	}
	
	public boolean comprobarOfertaTransaccion(String cod) throws LogicaExcepcion{
		return control.comprobarOfertaTransaccion(cod);
	}

}
