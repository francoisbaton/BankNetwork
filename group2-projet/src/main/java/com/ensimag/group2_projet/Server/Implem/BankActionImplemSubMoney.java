package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;

import com.ensimag.api.bank.IBank;
import com.ensimag.api.bank.IBankNode;

public class BankActionImplemSubMoney extends BankActionImplem{

	private int montant;
	private long idAccount;
	
	public BankActionImplemSubMoney() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.montant = 0;
		this.idAccount = 0;
	}
	
	public BankActionImplemSubMoney(int montant, long idAccount) throws RemoteException {
		super();
		this.montant = montant;
		this.idAccount = idAccount;
		// TODO Auto-generated constructor stub
	}
	
	public long getIdAccount(){
		return this.idAccount;
	}
	
	public long getMontant(){
		return this.montant;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2868753001728895933L;

	public Serializable execute(IBankNode node) throws Exception {
		// TODO Auto-generated method stub
		//IBank bank = ((BankNodeImplem)node).getBank();
		return node.getAccount(this.getIdAccount()).remove(this.montant);
	}

}
