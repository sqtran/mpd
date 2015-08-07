package karabelas.mpd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import karabelas.LogRequest;
import karabelas.db.DBConnectionPool;
import karabelas.db.SQLAndOrClauseBuilder;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *  ParkingMovingInfractionsDAO
 * @author karabelasw
 *
 */

public class ParkingMovingInfractionsDAO {

	
	private static QueryRunner run = null;  
	private static String SELECT_SEARCH_INFRACTIONS = "Select * from parkingmovinginfractions WHERE ";
	
	/*
	 * Return a List of ParkingMovingInfraction objects from the database
	 * Precondition: Database pool (datasource) has been established,
	 * Uses Apache Commons DBUtils for lean mean DB calls under the hood
	 */
	 public static List<ParkingMovingInfraction> listOfInfractions(DataSource aDataSource) throws SQLException {
		
		 List<ParkingMovingInfraction> lstOfInfractions = null;		 
		 if(run == null){
			run = new QueryRunner(aDataSource);
		 }
		 if( run != null){	 
			 
			 ResultSetHandler<List<ParkingMovingInfraction>> h = new BeanListHandler<ParkingMovingInfraction>(ParkingMovingInfraction.class);
		     // Execute the SQL statement and return the results in a List of		     
			 lstOfInfractions = run.query("SELECT * FROM parkingmovinginfractions", h);
		 }
		 
		 	return lstOfInfractions;		 
	  }

	  /**
	  * Return a single {@link LogRequest} identified by its id.
	  */
	  public ParkingMovingInfraction fetchbyId(int anID) {
	    return new ParkingMovingInfraction();
	  }
	  
	  public ParkingMovingInfraction fetchByCode(String aCode) {
		    return new ParkingMovingInfraction();
	 }


	  /** Return the number of records in parkingmovinginfractions table.  */
	 public Integer getCountFromParkingMovingInfractions() throws SQLException {
	    return 0;
	  }
	 
	 /**
	  * 
	  * @param optButton =>0 determines the SQL builder
	  * @param searchWords not empty and not null
	  * @param fieldName-- databasefield name to be search
	  * @return returns a List of ParkingMovingInfraction objects or an empty list
	  * @throws SQLException
	  */
	 
	 public static List<ParkingMovingInfraction> listSearchInfractionsByDescription(int optButton, String searchWords, String fieldName) throws SQLException {
			
		 List<ParkingMovingInfraction> lstOfInfractions = null;	
		 StringBuffer sql = new StringBuffer(SELECT_SEARCH_INFRACTIONS);
		 if(run == null){
			run = new QueryRunner(DBConnectionPool.getDBConnectionPool());
		 }
		 if( run != null){
			 			 
			 ResultSetHandler<List<ParkingMovingInfraction>> rsh = new BeanListHandler<ParkingMovingInfraction>(ParkingMovingInfraction.class);
			//build array of parameters to pass queryRunner
			 Object[] arraySqlParameters = SQLAndOrClauseBuilder.buildParametersFromString(searchWords, " ");
			 
			 //this will prefix and suffix each search word with '%' for SQL wildcard 
			 //we wont want to do this for 'exact' searches
			  arraySqlParameters = SQLAndOrClauseBuilder.appendWildCardToSearchWords(arraySqlParameters);			 
			 
			 switch(optButton){
			 case 0://exact was selected, we reset our arrayParameter as one string of search words
				 arraySqlParameters = new Object[1];
				 arraySqlParameters[0] = searchWords;
				 sql.append(fieldName+" LIKE ?");
				break;
			 case 1://'any' was selected build Like OR clause of SQL
				 sql.append(SQLAndOrClauseBuilder.buildLikeOrClause(arraySqlParameters.length, fieldName));
				 break;
			 case 2://'and' was selected build Like AND clause of SQL
				 sql.append(SQLAndOrClauseBuilder.buildLikeAndClause(arraySqlParameters.length, fieldName));
				 break;
			 default:
				 arraySqlParameters = new Object[1];
			 	 arraySqlParameters[0] = searchWords;
				 sql.append("Description LIKE ?");
				 break;		 
			 }
			System.out.println("*******EXECUTING THE SQL LINE OF*******-->\n" + sql.toString());
			 
			 lstOfInfractions = (List<ParkingMovingInfraction>)run.query(sql.toString(), rsh, arraySqlParameters);
		     // Execute the SQL statement and return the results in a List of		     
			 
		 }
		 
		 	return lstOfInfractions;		 
	  }	 
	 
}
