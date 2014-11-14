package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.ensimag.api.bank.IBankAction;
import com.ensimag.api.bank.IBankNode;

public abstract class BankActionImplem extends UnicastRemoteObject implements IBankAction{

	protected BankActionImplem() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4868888980320597519L;

	
	

	/*public BankActionImplem clone(){
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


	public Serializable execute(IBankNode node) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/

	
	
	
}
