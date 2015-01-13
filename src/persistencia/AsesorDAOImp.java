package persistencia;
import java.sql.*;
import java.util.*;

import logica.Asesor;
import excepciones.DAOExcepcion;

public class AsesorDAOImp implements IAsesorDAO{
	   //protected ConnectionManager connManager;
	   private SentenciaSQL manejador;
	   
	   public AsesorDAOImp() throws DAOExcepcion {
			super();
			manejador = SentenciaSQL.getInstance();
			manejador.conexionBD();
		}

	  public void crearAsesor(Asesor as) throws DAOExcepcion {		
		  manejador.updateSQL("insert into ASESOR (CODIGO_EMPLEADO, NOMBRE, APELLIDOS) values ('"+as.getCodigo_emp()+"','"+as.getNombre()+"','"+as.getApellidos()+"')");
	  }

	  public Asesor encontrarAsesorPorCod(String cod)throws DAOExcepcion{
		  try{
			  ResultSet rs = manejador.querySQL("select NOMBRE,APELLIDOS from ASESOR where CODIGO_EMPLEADO= '"+cod+"'");
			  if (rs.next())
				  return new Asesor(cod,rs.getString("NOMBRE"),rs.getString("APELLIDOS"));
			  else
				  return null;				
		  }
		  catch (SQLException e){
			  throw new DAOExcepcion(e);
		  }
	  }
	 
	  public ArrayList<Asesor> encontrarAsesores() throws DAOExcepcion{
		  try{
			  ArrayList<Asesor> asesores = new ArrayList<Asesor>();
			  ResultSet rs = manejador.querySQL("select * from ASESOR");
			  while(rs.next()){
				  asesores.add(new Asesor(rs.getString("CODIGO_EMPLEADO"),rs.getString("NOMBRE"),rs.getString("APELLIDOS")));
			  }
			  return asesores;
		  }
		  catch (SQLException e){
			  throw new DAOExcepcion(e);
		  }
	  }
}
