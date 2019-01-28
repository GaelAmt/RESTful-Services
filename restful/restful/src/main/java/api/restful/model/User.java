package api.restful.model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class User {
	
	private String login;
	private String password;
	public User() {
		
	}
	public User(String login, String password) {
		
		this.login = login;
		this.password = password;
		
	}
	public String getlogin() {
		return login;
	}
	public void setlogin(String login) {
		this.login = login;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	
	
}
