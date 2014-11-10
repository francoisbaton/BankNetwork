package com.ensimag.group2_projet.Server.Implem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.ensimag.api.bank.IUser;

public class UserImplem extends UnicastRemoteObject implements IUser{

	String name;
	String firstName;
	String age;
	
	
	protected UserImplem() throws RemoteException {
		super();
		name = "unknown";
		firstName = "unknown";
		age = "unknown";
	}

	public String getName(){
		// TODO Auto-generated method stub
		//return null;
		return name;
	}

	public String getFirstName(){
		// TODO Auto-generated method stub
		//return null;
		return firstName;
	}

	public String getAge() {
		// TODO Auto-generated method stub
		//return null;
		return age;
	}

	
}
