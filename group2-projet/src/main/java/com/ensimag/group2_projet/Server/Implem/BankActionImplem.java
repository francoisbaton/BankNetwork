package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;
import com.ensimag.api.bank.IBankAction;

public abstract class BankActionImplem implements IBankAction, Serializable{

	protected BankActionImplem() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4868888980320597519L;
	
}
