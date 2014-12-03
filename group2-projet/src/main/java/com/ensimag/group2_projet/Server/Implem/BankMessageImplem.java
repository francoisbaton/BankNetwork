package com.ensimag.group2_projet.Server.Implem;


import java.io.Serializable;
import java.rmi.RemoteException;

import com.ensimag.api.bank.IBankAction;
import com.ensimag.api.bank.IBankMessage;
import com.ensimag.api.message.EnumMessageType;


public class BankMessageImplem implements IBankMessage, Serializable {

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
		this.bankAction = null;
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
	
	@Override
	public boolean equals(Object o){
		if(o == this){
			return true;
		}else{
			if(o instanceof BankMessageImplem){
				if(((BankMessageImplem) o).getOriginalBankSenderId() == this.originalSenderId
						&& ((BankMessageImplem) o).getMessageId() == this.messageId
						&& ((BankMessageImplem) o).getDestinationBankId() == this.targetBankId
						&& ((BankMessageImplem) o).getMessageType() == this.messageType){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
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
			bankMessageCloned = new BankMessageImplem(this.originalSenderId, this.senderId,
					this.messageId, this.targetBankId, this.messageType,this.bankAction);
			return bankMessageCloned;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

	}

}
