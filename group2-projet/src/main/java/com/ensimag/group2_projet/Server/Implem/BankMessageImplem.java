package com.ensimag.group2_projet.Server.Implem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.ensimag.api.bank.IBankAction;
import com.ensimag.api.bank.IBankMessage;
import com.ensimag.api.message.EnumMessageType;

public class BankMessageImplem extends UnicastRemoteObject implements IBankMessage {

	
	protected BankMessageImplem() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public long getMessageId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getOriginalBankSenderId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getSenderId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setSenderId(long senderId) {
		// TODO Auto-generated method stub
		
	}

	public long getDestinationBankId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public EnumMessageType getMessageType() {
		// TODO Auto-generated method stub
		return null;
	}

	public IBankAction getAction() {
		// TODO Auto-generated method stub
		return null;
	}

	public IBankMessage clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
