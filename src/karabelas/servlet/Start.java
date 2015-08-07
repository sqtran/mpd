package karabelas.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

import karabelas.ContentsConstants;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import karabelas.db.DBConfigFile;
import karabelas.db.DBConnectionPool;
import karabelas.mpd.ParkingMovingInfraction;

/*
 * Application Starts here.  Creates a static BoneCP (database connectionpool)
 * Also attempts to setup log4j logger.  Haven't been successful in setting it up
 * 
 * 
 */


public class Start extends HttpServlet{

  
 // private static final Logger fLogger = Logger.getLogger(Start.class);	
  static ServletContext application = null;
  
  static Logger logger = null;
  
  public void init(ServletConfig config) throws ServletException{
	  	super.init(config);
	  	application = config.getServletContext();
	  	String webAppPath = application.getRealPath("/");
	  	String strNameOfDBConfigFile = config.getInitParameter("dbconfigfile");
	  //should be a log4j.properties file in the WEB-INF directory	
	  	String log4jLocation = config.getInitParameter("log4j-properties-location");    	
	  	String pathToDBConfig = webAppPath +strNameOfDBConfigFile;
      
    	       
     	log4jLocation = webAppPath + log4jLocation;
       
     	System.out.println("LOG4J PATH IS " + log4jLocation);
        
        try{
	    	BoneCPConfig boneConfig = DBConfigFile.createBoneCPConfig(pathToDBConfig);
	    	DBConnectionPool.createDBPool(boneConfig);
	    	application.setAttribute("pool", DBConnectionPool.getDBConnectionPool()); 
	        insertDefaultMessages();
	    	//testDBConnection();
	        setUpLog4J(log4jLocation);
	        System.out.println("*******----*******context name is " + application.getServletContextName()+"****-----*********");
	    		
        }
	     catch(SQLException sqlExp){
	        	System.out.println("ERROR IN CREATING DB CONNECTION POOL !!!" + sqlExp.getMessage());
        }
	     catch(Exception e) {
	        	e.printStackTrace();
	        	throw new UnavailableException("Couldn't Load database driver" + e.getMessage());
	     }
    
  }

  
  //Process the HTTP Get request
  public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      	System.out.println("********************SERVICE METHOD HERE**********");
  } 

  
  
  private void insertDefaultMessages(){
	  
	  application.setAttribute("HeaderMessage", ContentsConstants.HEADERMESSAGE);
	  application.setAttribute("SecondaryMessage", ContentsConstants.SECONDARYMESSAGE);	  
	    
	  
  }
  private void testDBConnection()throws SQLException{
	  
	 
	     QueryRunner run = new QueryRunner(DBConnectionPool.getDBConnectionPool());
      
	     // Use the BeanListHandler implementation to convert all
	     // ResultSet rows into a List of Person JavaBeans.
	     ResultSetHandler<List<ParkingMovingInfraction>> h = new BeanListHandler<ParkingMovingInfraction>(ParkingMovingInfraction.class);

	     // Execute the SQL statement and return the results in a List of
	     // Person objects generated by the BeanListHandler.
	      List<ParkingMovingInfraction> lstOfInfractions = run.query("SELECT * FROM parkingmovinginfractions", h);

	       System.out.println("Size of List is " + lstOfInfractions.size());
	       System.out.println(lstOfInfractions.toString());
	       
	       ResultSetHandler<ParkingMovingInfraction> infraction = new BeanHandler<ParkingMovingInfraction>(ParkingMovingInfraction.class);
       
	    // Execute the SQL statement with one replacement parameter and
	    // return the results in a new Person object generated by the BeanHandler.
	       ParkingMovingInfraction p = run.query("SELECT * FROM parkingmovinginfractions WHERE code=?", infraction, "P168"); 
	       
	       System.out.println("Infraction for Code 168 is " + p.toString());
	  
	  
  }
  private static void setUpLog4J(String log4jLocation){
  
	  if (log4jLocation == null) {
		System.err.println("*** No log4j-properties-location init param, so initializing log4j with BasicConfigurator");
		BasicConfigurator.configure();
	} else {
		
		File fileLog4J = new File(log4jLocation);
		if (fileLog4J.exists()) {
			System.out.println("Initializing log4j with: " + log4jLocation);
			PropertyConfigurator.configure(log4jLocation);
		} else {
			System.err.println("*** " + log4jLocation + " file not found, so initializing log4j with BasicConfigurator");
			BasicConfigurator.configure();
		}
	}
  }

  
} //END Start servlet
