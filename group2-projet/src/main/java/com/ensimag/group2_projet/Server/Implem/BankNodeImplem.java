package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import com.ensimag.api.bank.IAccount;
import com.ensimag.api.bank.IBankMessage;
import com.ensimag.api.bank.IBankNode;
import com.ensimag.api.bank.IUser;
import com.ensimag.api.message.IAck;
import com.ensimag.api.message.IResult;
import com.ensimag.api.node.INode;

public class BankNodeImplem extends UnicastRemoteObject implements IBankNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4570225617460237787L;
	private BankImplem bank;
	private long id;
	
	protected BankNodeImplem() throws RemoteException {
		super();
		this.id = 0;
		this.bank = new BankImplem();
		// TODO Auto-generated constructor stub
	}
	
	public BankNodeImplem(long id) throws RemoteException {
		this.id = id;
		this.bank = new BankImplem(id);
	}

	

	public long getId() throws RemoteException {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void onMessage(IBankMessage message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void onAck(IAck ack) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void removeNeighboor(INode<IBankMessage> neighboor)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public List<IResult<? extends Serializable>> getResultForMessage(
			long messageId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean deliverResult(IResult<Serializable> result)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void addNeighboor(INode<IBankMessage> neighboor)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}



	public List<IAccount> getAccounts() throws RemoteException {
		// TODO Auto-generated method stub
		return this.bank.getAccounts();
	}



	public IAccount getAccount(long number) throws AccountNotFoundException,
			RemoteException {
		// TODO Auto-generated method stub
		return this.bank.getAccount(number);
	}



	public IAccount openAccount(IUser user) throws RemoteException {
		// TODO Auto-generated method stub
		return this.bank.openAccount(user);
	}



	public boolean closeAccount(long number) throws AccountNotFoundException,
			RemoteException {
		// TODO Auto-generated method stub
		return this.bank.closeAccount(number);
	}

}
