package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
	private ArrayList<INode<IBankMessage>> neighboors;
	
	protected BankNodeImplem() throws RemoteException {
		super();
		this.id = 0;
		this.bank = new BankImplem();
		this.neighboors = new ArrayList<INode<IBankMessage>>();
		// TODO Auto-generated constructor stub
	}
	
	public BankNodeImplem(long id) throws RemoteException {
		this.id = id;
		this.bank = new BankImplem(id);
		this.neighboors = new ArrayList<INode<IBankMessage>>();
	}

	//Redefinition d equals pour savoir l id des voisins
	@Override
	public boolean equals(Object o){
		
		if(o == this){
			return true;
		}else{
			if(o instanceof BankNodeImplem){
				if(((BankNodeImplem) o).id == this.id){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
	}

	public long getId() throws RemoteException {
		return this.id;
	}

	public void onMessage(IBankMessage message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Handles the ack of a given message
	 * 
	 * @param ack
	 *            the ack
	 * @throws RemoteException
	 */
	public void onAck(IAck ack) throws RemoteException {
		//TODO
		
	}

	public void removeNeighboor(INode<IBankMessage> neighboor)
			throws RemoteException {
		
			this.neighboors.remove(neighboor);
		
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
		
		// Verifie si la banque est déjà voisine avec redef de equals
		if(! this.neighboors.contains(neighboor)){
			this.neighboors.add(neighboor);
		}else{
			System.out.println("Déjà ajouté");
		}
	}



	public List<IAccount> getAccounts() throws RemoteException {
		return this.bank.getAccounts();
	}



	public IAccount getAccount(long number) throws AccountNotFoundException,
			RemoteException {
		return this.bank.getAccount(number);
	}



	public IAccount openAccount(IUser user) throws RemoteException {
		return this.bank.openAccount(user);
	}



	public boolean closeAccount(long number) throws AccountNotFoundException,
			RemoteException {
		return this.bank.closeAccount(number);
	}

}
