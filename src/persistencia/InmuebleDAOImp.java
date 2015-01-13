package persistencia;
import java.sql.*;

import logica.Asesor;
import logica.Inmueble;
import logica.Cliente;
import excepciones.DAOExcepcion;

import java.util.*;

public class InmuebleDAOImp implements IInmuebleDAO {
	
	private IAsesorDAO asesorDAO;
	private IClienteDAO clienteDAO;
	private SentenciaSQL manejador;
	
	   public InmuebleDAOImp() throws DAOExcepcion {
		   asesorDAO = new AsesorDAOImp();
		   clienteDAO = new ClienteDAOImp();
		   manejador = SentenciaSQL.getInstance();
		   manejador.conexionBD();
		
		}

	  public void crearInmueble(Inmueble in) throws DAOExcepcion {	
		  manejador.updateSQL("insert into INMUEBLE (COD_ID, ASESOR, CALLE, CLIENTE, FECHA_ALTA, LOCALIDAD, SUPERFICIE_TOTAL, VENTA_ALQUILER) values ('"+in.getCod_Id()+"','"+in.getAsesor().getCodigo_emp()+"','"+in.getCalle()+"', '"+in.getCliente().getNifCliente()+"', '"+in.getFecha_Alta()+"', '"+in.getLocalidad()+"', '"+in.getSuperficie_Total()+"', '"+in.getVenta_Alquiler()+"')");
		 
	  }

	 public Inmueble encontrarInmueblePorCod(String cod)throws DAOExcepcion{
		 try{
			 ResultSet rs = manejador.querySQL("select * from INMUEBLE where COD_ID= '"+cod+"'");
			 
			 if (rs.next()){
				 Asesor a = asesorDAO.encontrarAsesorPorCod(rs.getString("ASESOR"));
				 Cliente c = clienteDAO.encontrarClientePorCod(rs.getString("CLIENTE"));
				 return new Inmueble(cod,rs.getString("CALLE"),rs.getString("LOCALIDAD"), rs.getString("FECHA_ALTA"),rs.getString("SUPERFICIE_TOTAL"),rs.getString("VENTA_ALQUILER"),c,a);
			 }
			 else
				 return null;				
		 }
		 catch (SQLException e){
			 throw new DAOExcepcion(e);
		 }	
	 }

	public ArrayList<Inmueble> encontrarInmuebles() throws DAOExcepcion{
		try{
			ResultSet rs = manejador.querySQL("select * from INMUEBLE");
			ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();
			
			while(rs.next()){
				Asesor a = asesorDAO.encontrarAsesorPorCod(rs.getString("ASESOR"));
				Cliente c = clienteDAO.encontrarClientePorCod(rs.getString("CLIENTE"));
				inmuebles.add(new Inmueble(rs.getString("COD_ID"),rs.getString("CALLE"),rs.getString("LOCALIDAD"),rs.getString("FECHA_ALTA"),rs.getString("SUPERFICIE_TOTAL"),rs.getString("VENTA_ALQUILER"),c,a));
			}
			return inmuebles;
		}
		catch (SQLException e){
			throw new DAOExcepcion(e);
		}
	}

	public ArrayList<Inmueble> encontrarInmueblesPorCliente(String cod)throws DAOExcepcion{
		try{
			ResultSet rs = manejador.querySQL("select * from INMUEBLE where CLIENTE='"+cod+"'");
			ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();
			
			Cliente c = clienteDAO.encontrarClientePorCod(cod);
			while(rs.next()){
				Asesor a = asesorDAO.encontrarAsesorPorCod(rs.getString("ASESOR"));
				inmuebles.add(new Inmueble(cod,rs.getString("CALLE"),rs.getString("LOCALIDAD"),rs.getString("FECHA_ALTA"),rs.getString("SUPERFICIE_TOTAL"),rs.getString("VENTA_ALQUILER"),c,a));
			}
			return inmuebles;
		}
		catch(SQLException e){
			throw new DAOExcepcion(e);
		}
	}
	
	public ArrayList<Inmueble> encontrarInmueblesPorAsesor(String cod)throws DAOExcepcion{
		try{
			ArrayList<Inmueble> inmuebles = new ArrayList<Inmueble>();
			ResultSet rs = manejador.querySQL("select * from INMUEBLE where ASESOR='"+cod+"'");
			
			Asesor a = asesorDAO.encontrarAsesorPorCod(cod);
			while(rs.next()){
				Cliente c = clienteDAO.encontrarClientePorCod(rs.getString("CLIENTE"));
				inmuebles.add(new Inmueble(rs.getString("COD_ID"),rs.getString("CALLE"),rs.getString("LOCALIDAD"),rs.getString("FECHA_ALTA"),rs.getString("SUPERFICIE_TOTAL"),rs.getString("VENTA_ALQUILER"),c,a));
			}
			return inmuebles;
		}
		catch(SQLException e){
			throw new DAOExcepcion(e);
		}
	}
	 
}
