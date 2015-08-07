package karabelas.db;


import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;

public class SQLAndOrClauseBuilder {

	

	/*
	 * Precondtion: searchWords parameter is not empty or null
	 * delimeter: can be null but not empty
	 * 
	 */
	public static Object[] buildParametersFromString(String searchWords, String delimiter){
		Object[] arrayOfParameter = StringUtils.split(searchWords, delimiter);
		return arrayOfParameter;
		
	}
	
	/*Precondition: numberOfParameters > 0
	 * Parameter dbFieldName is not empty or null
	 *Postcondition: will return a string in the form of:
	 * 'fieldName Like ? (numberOfParameters == 1)
	 * 'fieldName Like ? OR fieldName Like ? (NumberOfParameters > 1)
	 */
	public static String buildLikeOrClause(int numberOfParameters, String dbFieldName){
		
		StringBuffer orClauseWithParamHolder = new StringBuffer(100);		
		
		for(int i=0; i < numberOfParameters; i++){			
			orClauseWithParamHolder.append(dbFieldName + " Like ?");
			if(numberOfParameters == (i+1))
				break;
			else{					
				orClauseWithParamHolder.append(" OR ");
			}					
		}
		
		System.out.println(orClauseWithParamHolder.toString());
		return orClauseWithParamHolder.toString();
	}
	
	
	public static String buildLikeAndClause(int numberOfParameters, String dbFieldName){
		
		StringBuffer andClauseWithParamHolder = new StringBuffer(100);
		
		for(int i=0; i < numberOfParameters; i++){
			
			andClauseWithParamHolder.append(dbFieldName + " Like ?");
			if(numberOfParameters == (i+1))
				break;
			else{
				andClauseWithParamHolder.append(" AND ");
			}				
		}
		
		System.out.println(andClauseWithParamHolder.toString());
		return andClauseWithParamHolder.toString();
	}	
	
	public static Object[] appendWildCardToSearchWords(Object[] searchStrings){
				
		for(int i=0; i < searchStrings.length; i++){
			searchStrings[i] = "%"+searchStrings[i]+"%";							
		}		
		return searchStrings;
	}

	
}
