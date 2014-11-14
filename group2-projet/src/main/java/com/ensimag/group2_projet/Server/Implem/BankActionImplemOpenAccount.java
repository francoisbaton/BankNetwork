package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;

import com.ensimag.api.bank.IBankNode;
import com.ensimag.api.bank.IUser;

public class BankActionImplemOpenAccount extends BankActionImplem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5940274330213966935L;
	private IUser user;
	
	public BankActionImplemOpenAccount() throws RemoteException {
		super();
		this.user = new UserImplem();
	}

	public BankActionImplemOpenAccount(IUser user) throws RemoteException{
		super();
		this.user = user;
	}

	public Serializable execute(IBankNode node) throws Exception {
		return node.openAccount(user);
	}
	
	

}
