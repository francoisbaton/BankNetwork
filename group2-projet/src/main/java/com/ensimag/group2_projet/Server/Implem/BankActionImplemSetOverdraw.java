package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;

import com.ensimag.api.bank.IBankNode;

public class BankActionImplemSetOverdraw extends BankActionImplem{

	private int montant;
	private long idAccount;
	
	public BankActionImplemSetOverdraw() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BankActionImplemSetOverdraw(long montant) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3469872763685919531L;

	public long getIdAccount(){
		return this.idAccount;
	}
	
	public long getMontant(){
		return this.montant;
	}

	
	public Serializable execute(IBankNode node) throws Exception {
		// TODO Auto-generated method stub
		return node.getAccount(this.getIdAccount()).setAllowedOverdraw(this.montant);
	}

}
