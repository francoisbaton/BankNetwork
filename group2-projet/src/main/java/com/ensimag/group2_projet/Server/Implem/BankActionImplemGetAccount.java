package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;
import com.ensimag.api.bank.IBankNode;



public class BankActionImplemGetAccount extends BankActionImplem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long number;
	
	public BankActionImplemGetAccount() throws RemoteException {
		super();
		this.number = 0;
	}
	
	public BankActionImplemGetAccount(long numberAccount) throws RemoteException {
		super();
		this.number = numberAccount;
	}
	

	public Serializable execute(IBankNode node) throws Exception {
		return node.getAccount(this.number);
	}

}
