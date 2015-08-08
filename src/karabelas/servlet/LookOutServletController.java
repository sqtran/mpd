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
public class LookOutServletController extends HttpServlet {
	

	   private static final long serialVersionUID = 1L;
		BoneCPDataSource pool = null;
		ServletContext application = null;	
		private static String DEFAULT = "/WEB-INF/jsp/patrol/patrolentry.jsp";
		
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