package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import com.ensimag.api.bank.IAccount;
import com.ensimag.api.bank.IBank;
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
	private IBank bank;
	private long id;
	private ArrayList<INode<IBankMessage>> neighboors;
	private List<IResult<? extends Serializable>> listRes;
	
	protected BankNodeImplem() throws RemoteException {
		super();
		this.id = 0;
		this.bank = new BankImplem();
		this.neighboors = new ArrayList<INode<IBankMessage>>();
		this.listRes = new  ArrayList<IResult<? extends Serializable>>();
		// TODO Auto-generated constructor stub
	}
	
	public BankNodeImplem(long id) throws RemoteException {
		this.id = id;
		this.bank = new BankImplem(id);
		this.neighboors = new ArrayList<INode<IBankMessage>>();
		this.listRes = new  ArrayList<IResult<? extends Serializable>>();
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

	public IBank getBank() throws RemoteException {
		return this.bank;
	}

	
	public long getId() throws RemoteException {
		return this.id;
	}

	//public void onMessage(MessageType message) throws RemoteException {
	public void onMessage(IBankMessage message) throws RemoteException {
		try {
			//Execute l'action du message si l id de la destination == id bank
			if(message.getDestinationBankId() == this.bank.getBankId()){
				
				message.getAction().execute(this);
				
			}else if(message.getOriginalBankSenderId() == this.bank.getBankId()){ // Requete client
				
				
			}else{//Propage le message
				
				//Si noeud puit
				if(this.neighboors.size() == 1){
					//Renvoie du ack
					AckImplem ack = new AckImplem();
					this.onAck(ack);
				}else{ // Noeud non puits
					//Si la premiere fois reception message
					if(!(this.listRes.contains(message))){
						
						
						/*for(INode<IBankMessage> voisin : this.neighboors){
							voisin.onMessage(message);
						}*/
					}else{ 
						// Si pas la premiere fois
						AckImplem ack = new AckImplem();
						//this.onAck(ack);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Handles the ack of a given message
	 * 
	 * @param ack
	 *            the ack
	 * @throws RemoteException
	 */
	public void onAck(IAck ack) throws RemoteException {
		//TODO emission du ack
		
		
	}

	public void removeNeighboor(INode<IBankMessage> neighboor)
			throws RemoteException {
		
			this.neighboors.remove(neighboor);
		
	}

	public List<IResult<? extends Serializable>> getResultForMessage(
			long messageId) throws RemoteException {
		
		 //On cree la liste de résultat a renvoyer
		 List<IResult<? extends Serializable>> listRes = new ArrayList<IResult<? extends Serializable>>();
		 
		 //Pour chaque elem dans la liste res de ma classe je regarde si le message est dans celle-ci
		 for( IResult<? extends Serializable> elem : this.listRes){
			 if(elem.getMessageId() == messageId ){
				//si c'est le cas, je l'add à ma liste à retourner
				 listRes.add(elem);
			 }
		 }
		 return listRes;
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
