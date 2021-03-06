package com.ensimag.group2_projet.Server.Implem;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;

import javax.security.auth.login.AccountNotFoundException;

import com.ensimag.api.bank.IAccount;
import com.ensimag.api.bank.IBank;
import com.ensimag.api.bank.IUser;

public class BankImplem implements IBank, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -859025434892140986L;
	private long bankId;
	private List<IAccount> accountBank;
	
	protected BankImplem() throws RemoteException {
		super();
		bankId=0;
		accountBank=new ArrayList<IAccount>();
	}

	protected BankImplem(long bankId, List<IAccount> accountBank) throws RemoteException {
		this.bankId=bankId;
		this.accountBank=accountBank;
	}
	
	public BankImplem(long bankId) throws RemoteException {
		this.bankId=bankId;
		this.accountBank = new ArrayList<IAccount>();
	}
	
	public List<IAccount> getAccounts() throws RemoteException {
		// TODO Auto-generated method stub
		return this.accountBank;
	}

	public IAccount getAccount(long number) throws AccountNotFoundException,
			RemoteException {
		for(int i=0; i<accountBank.size();i++){
			if(accountBank.get(i).getAccountNumber()==number)
				return accountBank.get(i);
		}
		throw new AccountNotFoundException();
	}

	public IAccount openAccount(IUser user) throws RemoteException {
		IAccount newAccount= new AccountImplem(user,0,0);
		accountBank.add(newAccount);
		return newAccount;
	}

	public boolean closeAccount(long number) throws AccountNotFoundException,
			RemoteException {
		for(int i=0; i<accountBank.size();i++){
			if(accountBank.get(i).getAccountNumber()==number){
				return this.accountBank.remove(accountBank.get(i));
			}
		}
		throw new AccountNotFoundException();
	}

	public long getBankId() {
		return this.bankId;
	}
	
	

}
