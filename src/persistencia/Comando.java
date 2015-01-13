package persistencia;

import java.sql.ResultSet;

public interface Comando {
	public void updateSQL(String sentencia);
	public ResultSet querySQL(String sentencia);

}
