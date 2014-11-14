package com.ensimag.group2_projet.Server.Implem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.ensimag.api.bank.IBankAction;

public abstract class BankActionImplem extends UnicastRemoteObject implements IBankAction{

	protected BankActionImplem() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4868888980320597519L;
	
}
