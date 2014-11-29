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
import com.ensimag.api.message.EnumMessageType;
import com.ensimag.api.message.IAck;
import com.ensimag.api.message.IResult;
import com.ensimag.api.node.INode;

public class BankNodeImplem extends UnicastRemoteObject implements IBankNode{
	
	/**
	 * Liste des attributs de BankNodeImplem
	 */
	private static final long serialVersionUID = 4570225617460237787L;
	private IBank bank;
	private long id;
	private ArrayList<INode<IBankMessage>> neighboors;
	private List<IResult<? extends Serializable>> listRes;
	private boolean alreadySeen;
	private List<INode<IBankMessage>> waitAck; //liste des voisins a qui on a envoyé un message
	private INode<IBankMessage> up; //correspond au noeud dont on vient recevoir un message
	
	protected BankNodeImplem() throws RemoteException {
		super();
		this.id = 0;
		this.bank = new BankImplem();
		this.neighboors = new ArrayList<INode<IBankMessage>>();
		this.listRes = new  ArrayList<IResult<? extends Serializable>>();
		this.alreadySeen = false;
		this.waitAck = new ArrayList<INode<IBankMessage>>();
		this.up = null;
	}
	
	public BankNodeImplem(long id) throws RemoteException {
		this.id = id;
		this.bank = new BankImplem(id);
		this.neighboors = new ArrayList<INode<IBankMessage>>();
		this.listRes = new  ArrayList<IResult<? extends Serializable>>();
		this.alreadySeen = false;
		this.waitAck = new ArrayList<INode<IBankMessage>>();
		this.up = null;
	}

	//Redefinition d equals pour savoir l id des voisins
	@Override
	public boolean equals(Object o){
		if(o == this){
			return true;
		}else{
			if(o instanceof BankNodeImplem){
				try {
					if(((BankNodeImplem) o).getId() == this.id){
						return true;
					}else{
						return false;
					}
				} catch (RemoteException e) {
					e.printStackTrace();
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

	
	public void onMessage(IBankMessage message) throws RemoteException {
		try {
			
			System.out.println("onMessage : " + this.id);
			//Execute l'action du message si l id de la destination == id bank
			if(message.getDestinationBankId() == this.bank.getBankId()){
				//Cree le résultat de l'action
				IResult<Serializable> res = new ResultImplem(message.getMessageId(),message.getAction().execute(this));
				
				//On assigne le Node up en le cherchant dans la liste de voisins
				for(INode<IBankMessage> voisin : this.neighboors){	
					if(voisin.getId() == message.getSenderId()){
						this.up = voisin;
					}
				}
				
				//Propage ACK
				IAck ack = new AckImplem(this.bank.getBankId(), message.getMessageId());
				this.up.onAck(ack);	
				
				//Propagation du résultat a l'original sender
				if(message.getMessageType() != EnumMessageType.DELIVERY){
					IBankMessage returnMessage = new BankMessageImplem(this.getId(), this.getId(), message.getMessageId(),
							message.getOriginalBankSenderId(), EnumMessageType.DELIVERY, new BankActionImplemAddResultList(res));

					for(INode<IBankMessage> voisin : this.neighboors){
						this.waitAck.add(voisin);
						voisin.onMessage(returnMessage);
					}
				}
			
			}
			else{//Propagation du message	
				
				if(this.neighboors.size() == 1 && this.bank.getBankId()!=message.getOriginalBankSenderId()){
					//on est sur un noeud puit qui n'est pas l' "original sender"
					IAck ack = new AckImplem(this.bank.getBankId(), message.getMessageId());
					for(INode<IBankMessage> voisin : this.neighboors){
						voisin.onAck(ack);
					}
					
				}else{ 
					
					if(!this.alreadySeen){ //on réceptionne le message pour la première fois
						this.alreadySeen = true;
						
						//On assigne le Node up en le cherchant dans la liste de voisins
						for(INode<IBankMessage> voisin : this.neighboors){	
							if(voisin.getId() == message.getSenderId()){
								this.up = voisin;
							}
						}
						
						for(INode<IBankMessage> voisin : this.neighboors){	
							if(voisin.getId() != message.getSenderId() && voisin.getId() != message.getOriginalBankSenderId()){
								//On change le sender et on propage le message
								IBankMessage clonedMessage = message.clone();
								clonedMessage.setSenderId(this.bank.getBankId());
								this.waitAck.add(voisin);
								voisin.onMessage(clonedMessage);
							}
						}
						
					}else{ // Si pas la premiere fois => envoie ACK
						this.alreadySeen = false;
						IAck ack = new AckImplem(this.bank.getBankId(), message.getMessageId());	
						
						//On assigne le Node up en le cherchant dans la liste de voisins
						for(INode<IBankMessage> voisin : this.neighboors){	
							voisin.onAck(ack);
						}
					}
				}
			}
		} catch (Exception e) {
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
		System.out.println("onACK : " + this.id);
		this.alreadySeen = false;
		long senderID = ack.getAckSenderId();
		boolean found = false;
		
		for(INode<IBankMessage> node : this.waitAck){
			if(node.getId() == senderID){
				found = true;
				this.waitAck.remove(node);
				break;
			}
		}
		
		if(found){
			if(this.waitAck.isEmpty() && this.up!=null){
				IAck ackCloned = new AckImplem(this.bank.getBankId(), ack.getAckMessageId());
				this.up.onAck(ackCloned);
			}
		}
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
		
		return this.listRes.add(result);
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
