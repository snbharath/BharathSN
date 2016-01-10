package com.fidelity.thor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.mysql.jdbc.Statement;


@Controller
@RequestMapping("/login.html")
public class Logincontroller {
@RequestMapping(method =RequestMethod.GET)
public String logging(Map model)
{
Loginpojo data1=new Loginpojo();
model.put("data1",data1);
return "logdetails";
}

@RequestMapping(method=RequestMethod.POST)
public String None(@ModelAttribute Loginpojo loginpojo, ModelMap model) throws ClassNotFoundException
{
/*	XmlBeanFactory bean=new XmlBeanFactory(new ClassPathResource("Newdata1.xml"));
	Jdbclogindao jdbclogindao=(Jdbclogindao) bean.getBean("logindao");
	jdbclogindao.saveUserDetails(loginpojo);
	return "registrationsuccess";      
	}*/
	 		String email1=loginpojo.getEmail();
	 		String Password1=loginpojo.getPassword();
	 		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	 		String DB_URL = "jdbc:mysql://localhost/ramanji";
	 		String USER = "root";
	 		String PASS = "";
	 		Connection conn = null;
	 		java.sql.Statement stmt = null;
	 		String[] eid=new String[50];
	 		int i=0,j=0;
	 		String[] passwd=new String[50];
	 		try{
	 			Class.forName("com.mysql.jdbc.Driver");
	 			conn = DriverManager.getConnection(DB_URL,USER,PASS);
	 			stmt = conn.createStatement(); 
	 			String sql1; 
	 			sql1 = "SELECT email,password FROM raam";
	 			ResultSet rs = stmt.executeQuery(sql1);
	 			
	 			while(rs.next())
	 			{
	 				 eid[i]=rs.getString(1);
	 				 passwd[j]=rs.getString(2);
	 				i++;
		 			j++;
	 			}
	 		
	 			rs.close();
	 			stmt.close(); 
	 			conn.close();
	 		
	 		}
	 		catch(SQLException se)
	 		{ 
	 			se.printStackTrace();
	 			}
	 		for(int k=0;k<=i;k++)
	 		{
	 		if(eid[k].equals(email1) && passwd[k].equals(Password1))
	 		{
	 			return "ajaxtext";
	 		}
	 	
	 		}
	 		return "logdetails";
	 		}
}