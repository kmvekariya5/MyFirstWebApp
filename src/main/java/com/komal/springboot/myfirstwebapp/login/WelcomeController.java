package com.komal.springboot.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@ControllerAdvice
@Order(-1)
class NoResourceFoundExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public final ResponseEntity<Object> handleResourceNotFound(Exception ex) throws Exception {
        throw ex;
    }

}
@Controller
@SessionAttributes("name")
public class WelcomeController {

//	@Autowired
//	private AuthenticService auth ;
//	
//	public LoginController() {}
//	public LoginController( AuthenticService auth)
//	{
//		this.auth = auth;
//	}
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String gotoWelcomePage( ModelMap model )
	{
		model.put("name", getLoggedInUserName());
		return "/WEB-INF/jsp/welcome.jsp";
		//return "welcome";
	}
	
	private String getLoggedInUserName()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
//	@RequestMapping(value = "login", method=RequestMethod.POST)
//	public String gotoWelcomePage( @RequestParam String name, @RequestParam String pword, ModelMap model)
//	{
//		
//		
//		model.put("name", name);
//		model.put("pword", pword);
//
//		if(auth.authentic(name, pword))
//		{
//			return "/WEB-INF/jsp/welcome.jsp";
//		}
//		else
//		{
//			model.put("error", "Invalid data");
//			return "/WEB-INF/jsp/loginPage.jsp";
//		}
//	}
}
