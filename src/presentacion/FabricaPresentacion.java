package presentacion;

import excepciones.DAOExcepcion;
import excepciones.LogicaExcepcion;

public class FabricaPresentacion implements IFabricaPresentacion{
	
	private static FabricaPresentacion fabrica = null;
	
	public static FabricaPresentacion getInstance() throws DAOExcepcion{
		if(fabrica == null){
			fabrica = new FabricaPresentacion();
		}
		return fabrica;
	}
	
	public void crearInterfaz(String tipo){
		if(tipo.equals("Crear Piso")){
			try {
				CrearPisoJDialog cp;
				cp = new CrearPisoJDialog();
				cp.setModal(true);
				cp.setVisible(true);
			} 
			catch (LogicaExcepcion e) {
				e.printStackTrace();
			}
		}
		
		else if(tipo.equals("Crear Nave Industrial")){
			CrearNaveIndustrialJDialog cni;
			try {
				cni = new CrearNaveIndustrialJDialog();
				cni.setModal(true);
				cni.setVisible(true);
			} catch (LogicaExcepcion e1) {
				e1.printStackTrace();
			}
		}
		
		else if(tipo.equals("Consultar Inmueble")){
			try{
				ConsultaInmueblesJDialog ci = new ConsultaInmueblesJDialog();
				ci.setModal(true);
				ci.cargaInmuebles();
				ci.setVisible(true);
			}
			catch(LogicaExcepcion e){
				e.printStackTrace();
			}
		}
		
		else if(tipo.equals("Consultar Visitas/Ofertas")){
			try{
				ConsultarVisitasOfertasInmueblesJDialog cvoi = new ConsultarVisitasOfertasInmueblesJDialog();
				cvoi.setModal(true);
				cvoi.setVisible(true);
			}
			catch(LogicaExcepcion e){
				e.printStackTrace();
			}
		}
		
		else if(tipo.equals("Consulta Inmuebles Disponibles")){
			try{
				ConsultarInmueblesNoVendidosNiAlquiladosJDialog cid = new ConsultarInmueblesNoVendidosNiAlquiladosJDialog();
				cid.setModal(true);
				cid.cargaInmuebles();
				cid.setVisible(true);
			}
			catch(LogicaExcepcion e){
				e.printStackTrace();
			}
		}
		
		else if(tipo.equals("Listar Clientes-Inmuebles")){
			try{
				ConsultarClientesInmueblesJDialog cci = new ConsultarClientesInmueblesJDialog(); 
				cci.setModal(true);
				cci.setVisible(true);
			}
			catch(LogicaExcepcion e1){
				e1.printStackTrace();
			}
		}
		
		else if(tipo.equals("Crear Cliente")){
			try{
				CrearClienteJDialog cc;
				cc = new CrearClienteJDialog();
				cc.setModal(true);
				cc.setVisible(true);
			}
			catch(LogicaExcepcion e1){
				e1.printStackTrace();
			}
		}
		
		else if(tipo.equals("Nueva Transaccion")){
			try{
				CrearVentaAlquilerJDialog cva = new CrearVentaAlquilerJDialog();
				cva.setModal(true);
				cva.setVisible(true);
			}
			catch(LogicaExcepcion e1){
				e1.printStackTrace();
			}
		}
		
		else if(tipo.equals("Nueva Oferta")){
			try{
				CrearOfertaJDialog co = new CrearOfertaJDialog();
				co.setModal(true);
				co.setVisible(true);
			}
			catch(LogicaExcepcion e1){
				e1.printStackTrace();
			}
		}
		
		else if(tipo.equals("Nueva Visita")){
			try{
				CrearVisitaJDialog co = new CrearVisitaJDialog();
				co.setModal(true);
				co.setVisible(true);
			}
			catch(LogicaExcepcion e1){
				e1.printStackTrace();
			}
		}
		
		else if(tipo.equals("Consultar AsesoresInmuebles")){
			try{	
				ConsultarAsesoresInmueblesJDialog cai;
				cai = new ConsultarAsesoresInmueblesJDialog();
				cai.setModal(true);
				cai.setVisible(true);
			}
			catch(LogicaExcepcion e1){
				e1.printStackTrace();
			}
		}
		
		else if(tipo.equals("Crear Asesor")){
			try {
				CrearAsesorJDialog ca;
				ca = new CrearAsesorJDialog();
				ca.setModal(true);
				ca.setVisible(true);
			} 
			catch (LogicaExcepcion e1) {
				e1.printStackTrace();
			}
		}
	}

}
