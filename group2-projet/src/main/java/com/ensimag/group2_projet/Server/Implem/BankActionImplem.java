package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.ensimag.api.bank.IBankAction;
import com.ensimag.api.bank.IBankNode;

//TODO
public class BankActionImplem extends UnicastRemoteObject implements IBankAction{

	public BankActionImplem() throws RemoteException {
		super();
	}

	public BankActionImplem clone(){
		BankActionImplem bankActionCloned;
		try {
			bankActionCloned = new BankActionImplem();
			return bankActionCloned;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Serializable execute(IBankNode node) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
