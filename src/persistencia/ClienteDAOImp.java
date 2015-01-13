package persistencia;

import java.sql.*;
import java.util.ArrayList;

import logica.Cliente;
import excepciones.DAOExcepcion;

public class ClienteDAOImp implements IClienteDAO{

	private SentenciaSQL manejador;
	
	   public ClienteDAOImp() throws DAOExcepcion {
			super();
			manejador = SentenciaSQL.getInstance();
			manejador.conexionBD();
	   }

	  public void crearCliente(Cliente cl) throws DAOExcepcion {
		  
			manejador.updateSQL("insert into CLIENTE (NIF_CLIENTE, NOMBRE, APELLIDOS) values ('"+cl.getNifCliente()+"','"+cl.getNombre()+"','"+cl.getApellidos()+"')");					
	
		}

	 public Cliente encontrarClientePorCod(String cod)throws DAOExcepcion{
		 try {
			ResultSet rs = manejador.querySQL("select NOMBRE,APELLIDOS from CLIENTE where NIF_CLIENTE= '"+cod+"'");
			if (rs.next())
				  return new Cliente(cod,rs.getString("NOMBRE"),rs.getString("APELLIDOS"));
			  else
				  return null;
		 } catch (SQLException e) {
			 throw new DAOExcepcion(e);
		 }			
	}
	 
	 public ArrayList<Cliente> encontrarClientes() throws DAOExcepcion{
		 try{
			 ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			 
			 ResultSet rs = manejador.querySQL("select * from CLIENTE");
			 while(rs.next()){
				 clientes.add(new Cliente(rs.getString("NIF_CLIENTE"),rs.getString("NOMBRE"),rs.getString("APELLIDOS")));
			 }
			 return clientes;
		 }
		 catch(SQLException e){
			 throw new DAOExcepcion(e);
		 }
	 }

	
}
