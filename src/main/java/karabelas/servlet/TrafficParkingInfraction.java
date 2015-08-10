package karabelas.servlet;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import karabelas.mpd.ParkingMovingInfractionsDAO;

import com.jolbox.bonecp.BoneCPDataSource;


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
			//if we have search words..giddy up
			if(validParameters(request)){
				placeSearchResultsIntoUserSession(request);			
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/listinfractions.jsp");
			rd.forward(request, response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			
			doGet(request, response);	
		}

		/**
		 * only description (search words) need to be provided to make request valid
		 * Effects: if no search words are provided the default behavior is to display
		 * the entire list of parkingmovinginfractions from the application level scope (loaded at startup of
		 * web application in Start.java servlet)
		 * @param request
		 * @return
		 */
		private boolean validParameters(HttpServletRequest request){
			System.out.println("*****PROCESSING PARAMETERS YAHOOO*******");			
			String description = request.getParameter("description");
			
			boolean hasValidParams = false;
			
			//were search words provided
			if(description != null && !description.equals("")){			   
			    hasValidParams = true;			    
			 }
			return hasValidParams;
					
		}		
		
	
		//call DAO layer, which will determine type of SQL String to build based
		//on call to this.typeOfSearch(HttpServletRequest)
		private void placeSearchResultsIntoUserSession(HttpServletRequest request){
			String strDescription = request.getParameter("description");
			strDescription = strDescription == null ? "" : strDescription;
			try {
				request.setAttribute("lstinfractions", ParkingMovingInfractionsDAO.listSearchInfractionsByDescription(typeOfSearch(request), strDescription, "Description"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR GETTING SEARCH RESULTS " + e.getMessage());
			}
			
		}
		/**
		 * 
		 * @param request
		 * @return an int used to determine which portion of the SQL Like Clause
		 * needs to be built.  If user did not select an option, then defaults to
		 * returning 0 (zero), which builds SQL like clause without any wildcard
		 * LIKE searchWords
		 */
		private int typeOfSearch(HttpServletRequest request){
			
				System.out.println("*****TYPE OF SEARCH *******");
				String optionBtn = request.getParameter("sgroup");				
				optionBtn = optionBtn == null ? "zero" : optionBtn;
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