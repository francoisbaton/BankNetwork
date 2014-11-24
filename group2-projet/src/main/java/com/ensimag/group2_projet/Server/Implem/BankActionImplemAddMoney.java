package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;

import com.ensimag.api.bank.IBankNode;

public class BankActionImplemAddMoney extends BankActionImplem{
	
	private int montant;
	private long idAccount;

	public BankActionImplemAddMoney() throws RemoteException {
		super();
		this.montant = 0;
		this.idAccount = 0;
		// TODO Auto-generated constructor stub
	}
	
	public BankActionImplemAddMoney(int montant) throws RemoteException {
		super();
		this.montant = montant;
		this.idAccount = 0;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5002284935280796993L;

	public long getIdAccount(){
		return this.idAccount;
	}
	
	public long getMontant(){
		return this.montant;
	}
	
	

	
	public Serializable execute(IBankNode node) throws Exception {
		// TODO Auto-generated method stub
		return node.getAccount(this.getIdAccount()).add(this.montant);
	}

}
