package persistencia;

import logica.*;
import excepciones.*;

import java.sql.*;
import java.util.*;

public class VisitaDAOImp implements IVisitaDAO{

	//protected ConnectionManager connManager;
	private IInmuebleDAO inmuebleDAO;
	private IClienteDAO clienteDAO;
	private IAsesorDAO asesorDAO;
	private SentenciaSQL manejador;
	
	public VisitaDAOImp() throws DAOExcepcion{
		super();
		manejador = SentenciaSQL.getInstance();
		manejador.conexionBD();
		inmuebleDAO = new InmuebleDAOImp();
		clienteDAO = new ClienteDAOImp();
		asesorDAO = new AsesorDAOImp();
	}
	
	public void crearVisita(Visita v)throws DAOExcepcion{
		manejador.querySQL("insert into VISITA (ID_VISITA,ASESOR,CLIENTE,FECHA,INMUEBLE) values ('"+v.getCod_Id()+"','"+v.getAsesor().getCodigo_emp()+"','"+v.getCliente().getNifCliente()+"','"+v.getFecha()+"','"+v.getInmueble().getCod_Id()+"')");
	
	}
	
	public ArrayList<Visita> encontrarVisitasPorInmueble(String inmueble)throws DAOExcepcion{
		try{
			ResultSet rs = manejador.querySQL("select * FROM VISITA where INMUEBLE = '"+inmueble+"'");
			
			ArrayList<Visita> visitas = new ArrayList<Visita>();
			while(rs.next()){
				Inmueble in = inmuebleDAO.encontrarInmueblePorCod(inmueble);
				Cliente cl = clienteDAO.encontrarClientePorCod(rs.getString("CLIENTE"));
				Asesor as = asesorDAO.encontrarAsesorPorCod(rs.getString("ASESOR"));
				visitas.add(new Visita(rs.getString("ID_VISITA"),rs.getString("FECHA"),in,cl,as));
			}
			return visitas;
		}
		catch(SQLException e){
			throw new DAOExcepcion(e);
		}
	}
	
	public Visita encontrarVisitaPorCod(String cod)throws DAOExcepcion{
		try{
			ResultSet rs = manejador.querySQL("select * FROM VISITA where ID_VISITA = '"+cod+"'");
			if(rs.next()){
				Inmueble in = inmuebleDAO.encontrarInmueblePorCod(rs.getString("INMUEBLE"));
				Cliente cl = clienteDAO.encontrarClientePorCod(rs.getString("CLIENTE"));
				Asesor as = asesorDAO.encontrarAsesorPorCod(rs.getString("ASESOR"));
				return new Visita(cod,rs.getString("FECHA"),in,cl,as);
			}
			else{
				return null;
			}
		}
		catch(SQLException e){
			throw new DAOExcepcion(e);
		}
	}

	public ArrayList<Visita> encontrarVisitasPorAsesor(String asesor)throws DAOExcepcion{
		try{
			ResultSet rs = manejador.querySQL("select * from VISITA where ASESOR = '"+asesor+"'");
			ArrayList<Visita> visitas = new ArrayList<Visita>();
			while(rs.next()){
				Inmueble in = inmuebleDAO.encontrarInmueblePorCod(rs.getString("INMUEBLE"));
				Cliente cl = clienteDAO.encontrarClientePorCod(rs.getString("CLIENTE"));
				Asesor as = asesorDAO.encontrarAsesorPorCod(asesor);
				visitas.add(new Visita(rs.getString("ID_VISITA"),rs.getString("FECHA"),in,cl,as));
			}
			return visitas;
		}
		catch(SQLException e){
			throw new DAOExcepcion(e);
		}
	}
	
}
