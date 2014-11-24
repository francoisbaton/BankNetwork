package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;

import com.ensimag.api.bank.IBankNode;
import com.ensimag.api.message.IResult;

public class BankActionImplemAddResultList extends BankActionImplem {

	public BankActionImplemAddResultList() throws RemoteException {
		super();
		this.result = null;
	}
	
	public BankActionImplemAddResultList(IResult<Serializable> res) throws RemoteException {
		super();
		this.result = res;
	}
	

	private IResult<Serializable> result;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4878740294071512297L;

	public Serializable execute(IBankNode node) throws Exception {
		return node.deliverResult(this.result);
	}

	public IResult<Serializable> getResult() {
		return result;
	}

	public void setResult(IResult<Serializable> result) {
		this.result = result;
	}
	
	
	
}
