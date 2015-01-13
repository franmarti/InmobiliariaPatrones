package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;

import logica.*;
import excepciones.*;

public class TransaccionDAOImp implements ITransaccionDAO{

	public IOfertaDAO ofertaDAO;
	//protected ConnectionManager connManager;
	private SentenciaSQL manejador;
	
	public TransaccionDAOImp() throws DAOExcepcion {
		super();
		ofertaDAO = new OfertaDAOImp();
		manejador = SentenciaSQL.getInstance();
		manejador.conexionBD();
	
	}
	
	public void crearTransaccion(Transaccion t, String id)throws DAOExcepcion{
		try{
			manejador.querySQL("insert into TRANSACCION (ID_TRANSACCION,COMPRA_O_ALQUILER,FECHA,OFERTA,PRECIO_FINAL) values ('"+id+"','"+t.getCompra_o_alquiler()+"','"+t.getFecha()+"','"+t.getOferta().getCod_Id()+"','"+t.getPrecio_final()+"')");
		
		}
		catch(Exception e){
			throw new DAOExcepcion(e);
		}
	}
	
	public ArrayList<Transaccion> encontrarTransacciones()throws DAOExcepcion{
		try{
			ArrayList<Transaccion> transacciones = new ArrayList<Transaccion>();
			ResultSet rs = manejador.querySQL("select * from transaccion");
			
			while(rs.next()){
				Oferta o = ofertaDAO.encontrarOfertaPorCod(rs.getString("OFERTA"));
				transacciones.add(new Transaccion(rs.getString("PRECIO_FINAL"),rs.getString("FECHA"),rs.getString("COMPRA_O_ALQUILER"),o));
			}
			return transacciones;
		}
		catch(Exception e){
			throw new DAOExcepcion(e);
		}
	}
	
	public boolean comprobarOfertaTransaccion(String oferta)throws DAOExcepcion{
		try{
			ResultSet rs = manejador.querySQL("select * from transaccion where oferta = '"+oferta+"'");
			
			if(rs.next())
				return true;
			return false;
		}
		catch(Exception e){
			throw new DAOExcepcion(e);
		}
	}
	
}
