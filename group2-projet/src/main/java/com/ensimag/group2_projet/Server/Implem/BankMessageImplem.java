package com.ensimag.group2_projet.Server.Implem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.ensimag.api.bank.IBankAction;
import com.ensimag.api.bank.IBankMessage;
import com.ensimag.api.message.EnumMessageType;

public class BankMessageImplem extends UnicastRemoteObject implements IBankMessage {

	private long senderId;
	private long originalSenderId;
	private long messageId;
	private long targetBankId;
	private EnumMessageType messageType;
	private IBankAction bankAction;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2331890684694465315L;

	public BankMessageImplem() throws RemoteException {
		super();
		this.senderId = 0;
		this.originalSenderId = 0;
		this.messageId = 0;
		this.targetBankId = 0;
		this.messageType = EnumMessageType.BROADCAST;
		this.bankAction = new BankActionImplem();
	}
	
	public BankMessageImplem(long originalSenderId, long senderId, long messageId,
			long targetBankId, EnumMessageType messageType, IBankAction bankAction) throws RemoteException {
		super();
		this.senderId = senderId;
		this.originalSenderId = originalSenderId;
		this.messageId = messageId;
		this.targetBankId = targetBankId;
		this.messageType = messageType;
		this.bankAction = bankAction;
	}
	

	
	public long getMessageId() {
		return this.messageId;
	}

	public long getOriginalBankSenderId() {
		return this.originalSenderId;
	}

	public long getSenderId() {
		return this.senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
		
	}

	public long getDestinationBankId() {
		return this.targetBankId;
	}

	public EnumMessageType getMessageType() {
		return this.messageType;
	}

	public IBankAction getAction() {
		return this.bankAction;
	}

	public IBankMessage clone() {
		
		BankMessageImplem bankMessageCloned;
		//TODO deepcopy de bankAction?
		try {
			bankMessageCloned = new BankMessageImplem(this.originalSenderId, this.originalSenderId,
					this.messageId, this.targetBankId, this.messageType,this.bankAction);
			return bankMessageCloned;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

	}

}
