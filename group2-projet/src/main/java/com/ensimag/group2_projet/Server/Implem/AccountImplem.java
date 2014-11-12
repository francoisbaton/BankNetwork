package com.ensimag.group2_projet.Server.Implem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.ensimag.api.bank.IAccount;
import com.ensimag.api.bank.IUser;
import com.ensimag.api.bank.NotEnoughMoneyException;

public class AccountImplem extends UnicastRemoteObject implements IAccount{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2686599075236078656L;
	private IUser user;
	private long cash;
	private int overdraw;
	private long accountNumber;
	
	protected AccountImplem() throws RemoteException {
		super();
		this.user = new UserImplem();
		this.cash = 0;
		this.overdraw = 0;
		this.accountNumber = 0;
	}

	protected AccountImplem(IUser user, long cash, int overdraw,long accountNumber) throws RemoteException {
		this.user = user;
		this.cash = cash;
		this.overdraw = overdraw;
		this.accountNumber = accountNumber;
	}
	
	public IUser getAccountUser() {
		return user;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Add the amount of cash to the account
	 * 
	 * @param amount
	 *            the amount to add
	 * @return the new cash amount
	 */
	public int add(int amount) {
		//Add cash
		this.cash += amount;
		
		return (int) this.cash;
	}

	/**
	 * Remove the amount on the account
	 * 
	 * @param amount
	 *            the amount to remove
	 * @return the new cash amount
	 * @throws NotEnoughMoneyException
	 *             if there is not enough money to remove the amount of money,
	 *             overdraw included
	 */
	public int remove(int amount) throws NotEnoughMoneyException {
		if(this.cash - amount < this.overdraw ){
			throw new NotEnoughMoneyException(this);
		}else{
			this.cash -= amount;
		}
		
		return (int) this.cash;
	}

	/**
	 * @return the total of available money on the account
	 */
	public int getTotal() {
		
		return (int) cash;
	}

	/**
	 * Set the overdraw for the account
	 * 
	 * @param overdraw
	 *            the overdraw to set
	 * @return the set overdraw
	 */
	public int setAllowedOverdraw(int overdraw) {
		
		this.overdraw = overdraw;
		return this.overdraw;
	}
	
	
}
