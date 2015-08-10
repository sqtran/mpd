package karabelas.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jolbox.bonecp.BoneCPConfig;

 
public final class DBConfigFile {
	
	private static InputStream inputStream;
   /*
    * Method gets a full path to a properties file.
    * 
    * 
    */
	public static BoneCPConfig createBoneCPConfig(String propFileName) throws IOException {
 
		try {
			BoneCPConfig config = null;
			Properties prop = new Properties(); 
			inputStream = new FileInputStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			config = createBoneCPConfigFromProperties(prop);
			
			if (config != null) 
				return config;
			else
				throw new Exception("Unable to locate the Config Property File Specified in Parameter!");			
			
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		//should never get here
		return null;
	
	}
	
	private static BoneCPConfig createBoneCPConfigFromProperties(Properties prop) throws Exception{
		
		System.out.println("PROPERTY FILE TO STRING ---> " + prop);
		System.out.println("GET INNTEGER FROM PROPERTY ---> " + prop.getProperty("maxConnectionsPerPartition"));
		Integer.parseInt(prop.getProperty("maxConnectionsPerPartition"));
		BoneCPConfig config = new BoneCPConfig();
		// get the property value and print it out	
     
		config.setJdbcUrl(prop.getProperty("jdbcUrl")); 
		config.setUsername(prop.getProperty("Username")); 
		config.setPassword(prop.getProperty("Password"));
		config.setMinConnectionsPerPartition(Integer.parseInt(prop.getProperty("minConnectionsPerPartition")));
		config.setMaxConnectionsPerPartition(Integer.parseInt(prop.getProperty("maxConnectionsPerPartition")));
		config.setPartitionCount(Integer.parseInt(prop.getProperty("partitionCount")));
		System.out.println("BONE CONFIG FILE TO STRNG " + config);	
		return config;
		
	}
	
}