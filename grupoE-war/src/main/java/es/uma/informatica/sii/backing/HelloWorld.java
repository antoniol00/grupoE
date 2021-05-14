package es.uma.informatica.sii.backing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class HelloWorld {

	public String getHello() {
		return "Hello";
	}
}
