package logica;

import java.util.ArrayList;

import excepciones.LogicaExcepcion;

public class FabricaInmueble implements IFabricaInmueble{
	
	private static FabricaInmueble fabrica = null;
	
	public static FabricaInmueble getInstance() throws LogicaExcepcion {
		if(fabrica == null)
			fabrica = new FabricaInmueble();
		return fabrica;
	}
	
	@SuppressWarnings("rawtypes")
	public Inmueble crearInmueble(ArrayList lista){
		
		if(lista.get(0).toString().equals("Inmueble")){
			return new Inmueble(lista.get(1).toString(),lista.get(2).toString(),lista.get(3).toString(),lista.get(4).toString(),
					lista.get(5).toString(),lista.get(6).toString(),(Cliente)lista.get(7),(Asesor)lista.get(8));
		}
		
		if(lista.get(0).toString().equals("Piso")){
			return new Piso(lista.get(1).toString(),lista.get(2).toString(),lista.get(3).toString(),lista.get(4).toString(),
					lista.get(5).toString(),lista.get(6).toString(),lista.get(7).toString(),
					(Cliente)lista.get(8),(Asesor)lista.get(9));
		}
		
		if(lista.get(0).toString().equals("Nave Industrial")){
			return new NaveIndustrial(lista.get(1).toString(),lista.get(2).toString(),lista.get(3).toString(),lista.get(4).toString(),
					lista.get(5).toString(),lista.get(6).toString(),lista.get(7).toString(),lista.get(8).toString(),
					(Cliente)lista.get(9),(Asesor)lista.get(10));
		}
		
		if(lista.get(0).toString().equals("Bajo Comercial")){
			return new BajoComercial(lista.get(1).toString(),lista.get(2).toString(),lista.get(3).toString(),lista.get(4).toString(),
					lista.get(5).toString(),lista.get(6).toString(),lista.get(7).toString(),
					(Cliente)lista.get(8),(Asesor)lista.get(9));
		}
		
		if(lista.get(0).toString().equals("Casa Pueblo")){
			return new CasaPueblo(lista.get(1).toString(),lista.get(2).toString(),lista.get(3).toString(),lista.get(4).toString(),
					lista.get(5).toString(),lista.get(6).toString(),lista.get(7).toString(),lista.get(8).toString(),
					lista.get(9).toString(),(PlazaGaraje)lista.get(10),(Cliente)lista.get(11),(Asesor)lista.get(12));
		}
		
		if(lista.get(0).toString().equals("Plaza Garaje")){
			return new PlazaGaraje(lista.get(1).toString(),lista.get(2).toString(),lista.get(3).toString(),lista.get(4).toString(),
					lista.get(5).toString(),lista.get(6).toString(),lista.get(7).toString(),
					(Piso)lista.get(8),(CasaPueblo)lista.get(9),(Cliente)lista.get(10),(Asesor)lista.get(11));
		}
		
		if(lista.get(0).toString().equals("Adosado")){
			return new Adosado(lista.get(1).toString(),lista.get(2).toString(),lista.get(3).toString(),lista.get(4).toString(),
					lista.get(5).toString(),lista.get(6).toString(),lista.get(7).toString(),lista.get(8).toString(),
					lista.get(9).toString(),lista.get(10).toString(),(PlazaGaraje)lista.get(11),(Cliente)lista.get(12),(Asesor)lista.get(13));
		}
		
		return null;
	}

}

