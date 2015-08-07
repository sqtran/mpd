package karabelas.servlet;


import java.util.Enumeration;
import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import karabelas.LogRequest;
import karabelas.LogRequestDAO;


/**
 * 
 * @author William Karabelas
 * @version 1.1
 * 'logs' data in the database -- places data in the 'RequestLog' table
 * for auditing purposes.  I want to know who was here, where they were, where they
 * were going and what they were after.
 * 
 */
public class RequestLogger implements ServletRequestListener {
	
	private static ServletContext srvCntx = null; 	
	private static final Logger fLogger = Logger.getLogger(RequestLogger.class);
	
	
	public void requestDestroyed(ServletRequestEvent requestEvent){	
		fLogger.debug("RequestLogger instance  has been destroyed");
		System.out.println("RequestLogger-->ServletListener:Request Destroye**** ");
	}
	
	public void requestInitialized(ServletRequestEvent requestEvent){
		
		if (srvCntx == null){			
			srvCntx = requestEvent.getServletContext();			
		}
		LogRequest requestToBeLogged = new LogRequest();		
		System.out.println("servletContext.getRealPath is " + srvCntx.getRealPath("/"));		
		
	    //Used to get information about a new request
	    ServletRequest request = requestEvent.getServletRequest();
	    requestToBeLogged.setLogParameterNames(logParameterNames(request));
	    //System.out.println("Param names for request are " +  request.getParameterNames());
	    
	    
	    if(request instanceof HttpServletRequest){	    	
	       	
	    	 requestToBeLogged.setIpAddress(((HttpServletRequest)request).getRemoteAddr()); 
		     requestToBeLogged.setRequestURI(((HttpServletRequest)request).getRequestURI());
		     requestToBeLogged.setAuthUser(((HttpServletRequest)request).getRemoteUser());
		     requestToBeLogged.setIpAddress(((HttpServletRequest)request).getRemoteAddr());
		     requestToBeLogged.setReferer(((HttpServletRequest)request).getHeader("Referer"));
		  //log the ServletRequest to the database   
		     attemptAdd(requestToBeLogged);
		   
		   fLogger.debug(requestToBeLogged.toString()) ;
		   fLogger.debug("BILLYK logging this--: " + ((HttpServletRequest)request).getServletPath() + "--- " + ((HttpServletRequest)request).getPathInfo());
	   }		
	}
	
	 /**
	  *
	  * @author BK	  
	  * @param LogRequest
	  * 
	  * 
	  */
	  private void attemptAdd(LogRequest aRequestToLog) {
		  
		LogRequestDAO logRequestDAO = new LogRequestDAO();
		
		int id = -1;
	    
		try {
	    	  id = logRequestDAO.add(aRequestToLog);
	    }
	    catch(Exception ex){
	       	 
	    	   fLogger.error("SHOCK DE BOOM *****-----> : " + ex.getMessage());
	    }
	  }
	  
	  /**
	   * Gets the "parameterNames" from the <code>ServletRequest</code> parameter
	   * Builds a string of all the parameter names sent with the ServletRequest
	   * putting the parameterName inside a block of <-- ParameterName -->   * 
	   *
	   *
	   * @param request
	   * @return
	   */
	  
	  private String logParameterNames(ServletRequest request){
		  
		  StringBuilder sb = new StringBuilder(50);
		  Enumeration enumParams = request.getParameterNames();
		  String paramName = null;
		  if(enumParams != null)
			  
		  while(enumParams.hasMoreElements()){
			  sb.append("<--");
			  paramName = (String)enumParams.nextElement();
			  sb.append(paramName+"="+request.getParameter(paramName));
			  sb.append("-->");
		  }
		  
		  System.out.println("REQUESTPARAMETERS TO STRING OF PARAM NAMES IS " + sb.toString());
		  return sb.toString();
	  }
	  
	  
	
}
