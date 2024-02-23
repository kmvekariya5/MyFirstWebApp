package com.komal.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Component;

@Component
public class AuthenticService {

		public boolean authentic( String name, String p)
		{
			boolean isValidName = name.equalsIgnoreCase("komal");
			boolean isValidPassword =p.equalsIgnoreCase("komal");
			return (isValidName && isValidPassword);
		}
}
