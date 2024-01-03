package entite;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.Connection;

public interface IBaseDonnee {
	public java.sql.Connection initConnection() throws Exception;
	
}
