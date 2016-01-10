package com.fidelity.thor;

import java.util.Map;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/signup.html")
public class Usercontroller {
@RequestMapping(method=RequestMethod.GET)
public String Showing(Map model)
{
Userpojo data=new Userpojo();
model.put("data",data);
return "Udetails";
}
@RequestMapping(method =RequestMethod.POST)
public String result(@ModelAttribute() Userpojo userpojo, ModelMap model){

	XmlBeanFactory bean=new XmlBeanFactory(new ClassPathResource("Newdetails.xml"));
	JdbcUserDao jdbcUserDao=(JdbcUserDao) bean.getBean("userdao");
	jdbcUserDao.saveUserDetails(userpojo);
	return "logdetails";      
	}
	

	
}


