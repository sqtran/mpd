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
import karabelas.mpd.ParkingMovingInfractionsDAO;



/**
 * Servlet implementation class TrafficParkingInfraction
 */
public class TrafficParkingInfraction extends HttpServlet {
	

	   private static final long serialVersionUID = 1L;
		BoneCPDataSource pool = null;
		ServletContext application = null;	
		
		 public void init(ServletConfig config)throws ServletException{
			super.init(config);				
			System.out.println("INITIALIZING INIT METHOD TRAFFICPARKINGINFRACTION SERVLET**!!");
			application = this.getServletContext();		
			pool = (BoneCPDataSource)application.getAttribute("pool");
			
			if(pool == null){			
					
				throw new ServletException("Unable to connect to database");			
			}else {
				try {
					application.setAttribute("lstinfractions", ParkingMovingInfractionsDAO.listOfInfractions(pool));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("ERROR GETTING LIST OF INFRACIONS!!");
					System.out.println("Exception: "+ e.getMessage());
				}
			}
		}	 
		
		 /*
		  * 
		  * check for username and password
		  * create a UserInfo object	  
		  */
		public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			
			System.out.println("<-----doGetENTERING TrafficParkingInfraction Servlet-----> ");
			if(vaildParameters(request)){
				placeSearchResultsIntoUserSession(request);				
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listinfractions.jsp");
			rd.forward(request, response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			
			doGet(request, response);				
		
		}

		private boolean vaildParameters(HttpServletRequest request){
			System.out.println("*****PROCESSING PARAMETERS*******");
			String optionBtn = request.getParameter("sgroup");
			String description = request.getParameter("description");
			boolean hasValidParams = false;
			
			//radio button was selected and search words given
			if(optionBtn != null && !optionBtn.equals("")){
			    if( description != null && !description.equals("")){
			    	hasValidParams = true;
			    }
			 }
			return hasValidParams;
					
		}		
		
	
		//call DAO layer, which will determine type of SQL String to build based
		//on call to this.typeOfSearch(HttpServletRequest)
		private void placeSearchResultsIntoUserSession(HttpServletRequest request){
			String strDescription = request.getParameter("description");
			try {
				request.setAttribute("lstinfractions", ParkingMovingInfractionsDAO.listSearchInfractionsByDescription(typeOfSearch(request), strDescription, "Description"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR GETTING SEARCH RESULTS " + e.getMessage());
			}
			
		}
		/*
		 * Precondition: the parameter "sgroup" from radio button is not null or empty
		 * effects: used to determine which SQL Like clause to build
		 */
		private int typeOfSearch(HttpServletRequest request){
			
				System.out.println("*****TYPE OF SEARCH *******");
				String optionBtn = request.getParameter("sgroup");				
						
		   //arbitrarily give parameters a number value starting with 0 until 2		
				if(optionBtn.equals("exact")){
					return 0;
				}
			    if(optionBtn.equals("any")){
			    	return 1;	    	
			    }
			    if(optionBtn.equals("all")){
			    	return 2;
			    }		
										
			 return 0;
		}
			  
	}