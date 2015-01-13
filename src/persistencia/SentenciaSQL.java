package persistencia;

import java.sql.ResultSet;

import excepciones.DAOExcepcion;

public class SentenciaSQL implements Comando {
	
	private static SentenciaSQL manejador = null;
	protected ConnectionManager connManager = null;
	
	public static SentenciaSQL getInstance() throws DAOExcepcion{
		if(manejador == null){
			manejador = new SentenciaSQL();
		}
		return manejador;
	}
	
	public void conexionBD() throws DAOExcepcion{
		try{
			connManager = new ConnectionManager("practica4");
		}catch (ClassNotFoundException e){
			throw new DAOExcepcion(e);
		}
	}
	
	public void updateSQL(String sentencia){
		try {
			connManager.connect();
			connManager.updateDB(sentencia);
			connManager.close();
		} catch (DAOExcepcion e) {e.printStackTrace();}
	}
	
	public ResultSet querySQL(String sentencia){
		try {
			connManager.connect();
			ResultSet rs = connManager.queryDB(sentencia);
			connManager.close();
			return rs;
		} catch (DAOExcepcion e) {
			e.printStackTrace();
		}
		return null;
	}
}
