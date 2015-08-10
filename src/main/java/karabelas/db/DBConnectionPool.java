package karabelas.db;

import java.sql.SQLException;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;


public final class DBConnectionPool {
	
	private static BoneCPDataSource dbConnectionPool = null;
	
	public static void createDBPool(BoneCPConfig config) throws SQLException{		
		/* setup the connection pool */
		dbConnectionPool = new BoneCPDataSource(config);	
		
	}	
	
	public static BoneCPDataSource getDBConnectionPool(){
		if(dbConnectionPool != null){
			
			return dbConnectionPool;
		}
		else return null;
		
	}			
	
	static{
			System.out.println("Mr. Spock Here--CAPTAIN WE ARE LOADING DRIVER!!");
			
			try {
			// load the database driver (make sure this is in your classpath!)
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch (Exception e) {
				
				e.printStackTrace();
			}			
	}
	
}
