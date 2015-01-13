package persistencia;

import java.sql.*;
import java.util.ArrayList;

import logica.*;
import excepciones.*;

public class OfertaDAOImp implements IOfertaDAO{
	
	//protected ConnectionManager connManager;
	private IVisitaDAO visitaDAO;
	private SentenciaSQL manejador;
	
	public OfertaDAOImp() throws DAOExcepcion {
		super();
		visitaDAO = new VisitaDAOImp();
		manejador = SentenciaSQL.getInstance();
	}
	
	public void crearOferta(Oferta o)throws DAOExcepcion{
		manejador.querySQL("insert into Oferta (ID_OFERTA,FECHA,VISITA,PRECIO) values ('"+o.getCod_Id()+"','"+o.getFecha()+"','"+o.getVisita().toString().split("-")[0]+"','"+o.getPrecio()+"')");
	}
	
	public ArrayList<Oferta> encontrarOfertaPorVisita(String visita)throws DAOExcepcion{
		
		try{
			ResultSet rs = manejador.querySQL("select * from OFERTA where VISITA= '"+visita+"'");
			ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
			Visita vis = visitaDAO.encontrarVisitaPorCod(visita);
			if(rs.next())
				ofertas.add(new Oferta(rs.getString("ID_OFERTA"),rs.getString("PRECIO"),rs.getString("FECHA"),vis));
			return ofertas;
		  }
		  catch (Exception e){
			  System.out.println("Antes del printStackTrace");
			  e.printStackTrace();
			  System.out.println("Despues del printStackTrace");
			  throw new DAOExcepcion(e);
		  }
	}
	
	public Oferta encontrarOfertaPorCod(String cod)throws DAOExcepcion{
		try{
			ResultSet rs = manejador.querySQL("select * from OFERTA where ID_OFERTA= '"+cod+"'");
			Visita vis = visitaDAO.encontrarVisitaPorCod(cod);
			if(rs.next())
				return new Oferta(cod,rs.getString("PRECIO"),rs.getString("FECHA"),vis);
			else
				return null;
		  }
		  catch (Exception e){
			  throw new DAOExcepcion(e);
		  }
	}

}
