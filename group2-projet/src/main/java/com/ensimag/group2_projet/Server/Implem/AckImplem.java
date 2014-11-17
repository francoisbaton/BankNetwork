package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;
import com.ensimag.api.message.IAck;

public class AckImplem implements IAck, Serializable{

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
