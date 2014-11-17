package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;

import com.ensimag.api.bank.IBankNode;

public class BankActionImplemCloseAccount extends BankActionImplem {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5542925532428218146L;
	private long number;
	
	
	public BankActionImplemCloseAccount() throws RemoteException {
		super();
		this.number = 0;
	}
	
	public BankActionImplemCloseAccount(long number) throws RemoteException {
		this.number = number;
	}

	public Serializable execute(IBankNode node) throws Exception {
		return node.closeAccount(number);
	}



}
