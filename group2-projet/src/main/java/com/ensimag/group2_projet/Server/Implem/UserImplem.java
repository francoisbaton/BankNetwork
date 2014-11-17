package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;
import com.ensimag.api.bank.IUser;

public class UserImplem implements IUser, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2132616573118932797L;
	private String name;
	private String firstName;
	private String age;
	
	
	protected UserImplem() throws RemoteException {
		super();
		name = "unknown";
		firstName = "unknown";
		age = "unknown";
	}
	
	public UserImplem(String name, String firstName, String age) throws RemoteException{
		this.name = name;
		this.firstName = firstName;
		this.age = age;
	}

	public String getName(){
		return name;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getAge() {
		return age;
	}

	
}
