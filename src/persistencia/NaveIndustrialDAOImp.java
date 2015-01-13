package persistencia;

import java.sql.*;

import logica.*;
import excepciones.DAOExcepcion;

public class NaveIndustrialDAOImp extends InmuebleDAOImp implements INaveIndustrialDAO{
	
	private IAsesorDAO asesorDAO;
	private IClienteDAO clienteDAO;
	private SentenciaSQL manejador;
	
	   public NaveIndustrialDAOImp() throws DAOExcepcion {
			super();
			manejador = SentenciaSQL.getInstance();
			manejador.conexionBD();
		
		}

	  public void crearNaveIndustrial(NaveIndustrial ni) throws DAOExcepcion {
		 
			  crearInmueble(new Inmueble(ni.getCod_Id(), ni.getCalle(), ni.getLocalidad(), ni.getFecha_Alta(), ni.getSuperficie_Total(), ni.getVenta_Alquiler(), ni.getCliente(), ni.getAsesor()));
			  manejador.updateSQL("insert into NAVEINDUSTRIAL (COD_ID, CALIFICACION, NUM_PUERTAS) values ('"+ni.getCod_id()+"','"+ni.getCalificacion()+"','"+ni.getNum_puertas()+"')");
		
	  }

	 public NaveIndustrial encontrarNaveIndustrialPorCod(String cod)throws DAOExcepcion{
		 try{
		  Inmueble in = encontrarInmueblePorCod(cod);
		  if(in!=null){
			  ResultSet rs = manejador.querySQL("select NUM_PUERTAS,CALIFICACION from NAVEINDUSTRIAL where COD_ID= '"+cod+"'");
			  if (rs.next()){
				  Cliente c = clienteDAO.encontrarClientePorCod(rs.getString("CLIENTE"));
			  	  Asesor a = asesorDAO.encontrarAsesorPorCod(rs.getString("ASESOR"));
				  return new NaveIndustrial(cod,in.getCalle(),in.getLocalidad(),in.getFecha_Alta(),in.getSuperficie_Total(),in.getVenta_Alquiler(),rs.getString("NUM_PUERTAS"),rs.getString("CALIFICACION"),c,a);
			  }
			  else
				  return null;
		  }
		  return null;
	  }catch (SQLException e){
		  throw new DAOExcepcion(e);
	  }
			
	}

}
