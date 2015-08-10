package karabelas.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jolbox.bonecp.BoneCPDataSource;



/**
 * Servlet implementation class TrafficParkingInfraction
 */
public class LookOutServletController extends HttpServlet {
	

	   private static final long serialVersionUID = 1L;
		BoneCPDataSource pool = null;
		ServletContext application = null;	
		private static String DEFAULT = "/WEB-INF/jsp/patrol/login.jsp";
		
		 public void init(ServletConfig config)throws ServletException{
			super.init(config);				
			System.out.println("INITIALIZING INIT METHOD LOOKOUT CONTROLLER SERVLET**!!");
			application = this.getServletContext();		
			pool = (BoneCPDataSource)application.getAttribute("pool");			
		}	 
		
		 /*
		  * 
		  * check for username and password
		  * create a UserInfo object	  
		  */
		public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			
			System.out.println("<-----doGetENTERING LOOKOUT CONTROLLER Servlet-----> ");
			
			RequestDispatcher rd = request.getRequestDispatcher(DEFAULT);
			rd.forward(request, response);
		}
		
		public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			
			doGet(request, response);				
		
		}

	
	
	  
	}