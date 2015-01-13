package persistencia;

import java.util.*;
import java.sql.*;

import logica.*;
import excepciones.*;

public class PisoDAOImp extends InmuebleDAOImp implements IPisoDAO{
	
	private SentenciaSQL manejador;
	
	public PisoDAOImp() throws DAOExcepcion {
		super();
		manejador = SentenciaSQL.getInstance();
		manejador.conexionBD();
	}

	public Piso encontrarPisoPorCod(String cod)throws DAOExcepcion{
		try{
			Inmueble in = encontrarInmueblePorCod(cod); 			
			if (in != null)
			{
				ResultSet rs = manejador.querySQL("select * from PISO where COD_ID = '"+ cod +"'");
				
				if (rs.next())
					return new Piso(cod, in.getCalle(), in.getLocalidad(), in.getFecha_Alta(), 
							in.getSuperficie_Total(), in.getVenta_Alquiler(),
							rs.getString("NUM_HABITACIONES"), in.getCliente(),in.getAsesor());
			}
			return null;

		}catch (Exception e){
			throw new DAOExcepcion(e);
		}
	}
	
	@Override
	public void crearPiso(Piso p)throws DAOExcepcion{
		try{	
			crearInmueble(new Inmueble(p.getCod_Id(), p.getCalle(), p.getLocalidad(), p.getFecha_Alta(), p.getSuperficie_Total(), 
					p.getVenta_Alquiler(), p.getCliente(),p.getAsesor())); 
			manejador.updateSQL("insert into PISO (COD_ID, NUM_HABITACIONES) " +"values ('" + p.getCod_Id() + "', '" + p.getNum_Habitaciones() + "')");
			
		}catch (Exception e){
			throw new DAOExcepcion(e);
		}
	}
	
	@Override
	public List<Piso> encontrarPisos() throws DAOExcepcion{
		try{
			ResultSet rs = manejador.querySQL("select * from PISO");
			List<Piso> listaPisos=new ArrayList<Piso>();
			try{				
				while (rs.next()){
					Piso p = encontrarPisoPorCod(rs.getString("COD_ID"));	 
					listaPisos.add(p);
				}
				return listaPisos;
			}catch (Exception e){
				throw new DAOExcepcion(e);
			}
		}catch (DAOExcepcion e){
			throw e;
		}	
	}

}
