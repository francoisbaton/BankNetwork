package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;
import com.ensimag.api.message.IResult;

public class ResultImplem implements IResult<Serializable>, Serializable{
	
	private long messageId;
	private Serializable data;

	public ResultImplem() throws RemoteException {
		super();
		this.messageId = 0;
		this.data = null;
	}
	
	public ResultImplem(long messageId, Serializable T) throws RemoteException {
		super();
		this.messageId = messageId;
		this.data = T;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Serializable getData() {
		return this.data;
	}

	public long getMessageId() {
		return this.messageId;
	}

}
