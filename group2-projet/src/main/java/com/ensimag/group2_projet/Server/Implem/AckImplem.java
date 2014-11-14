package com.ensimag.group2_projet.Server.Implem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.ensimag.api.message.IAck;

public class AckImplem extends UnicastRemoteObject implements IAck{

	public AckImplem() throws RemoteException {
		super();
		this.senderId = 0;
		this.messageId = 0;
	}
	
	public AckImplem(long senderId, long messageId) throws RemoteException {
		super();
		this.senderId = senderId;
		this.messageId = messageId;
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long senderId;
	private long messageId;
	
	
	
	public long getAckSenderId() {
		return this.senderId;
	}
	public long getAckMessageId() {
		return this.messageId;
	}
	
}
