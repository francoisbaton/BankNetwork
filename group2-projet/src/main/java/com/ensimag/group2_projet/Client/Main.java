package com.ensimag.group2_projet.Client;
//package fr.ensimag.client;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.ensimag.api.bank.IAccount;
import com.ensimag.api.bank.IBankAction;
import com.ensimag.api.bank.IBankMessage;
import com.ensimag.api.bank.IBankNode;
import com.ensimag.api.bank.IUser;
import com.ensimag.api.message.EnumMessageType;
import com.ensimag.api.message.IResult;
import com.ensimag.group2_projet.Server.Implem.BankActionImplemOpenAccount;
import com.ensimag.group2_projet.Server.Implem.BankMessageImplem;
import com.ensimag.group2_projet.Server.Implem.BankNodeImplem;
import com.ensimag.group2_projet.Server.Implem.UserImplem;





/**
 *
 * @author reignier
 */
public class Main {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException
    {
        IBankNode ibn1 = (IBankNode) Naming.lookup("rmi://localhost/myBankNode1");
        IBankNode ibn2 = (IBankNode) Naming.lookup("rmi://localhost/myBankNode2");
        IBankNode ibn3 = (IBankNode) Naming.lookup("rmi://localhost/myBankNode3");
        IBankNode ibn4 = (IBankNode) Naming.lookup("rmi://localhost/myBankNode4");
        IBankNode ibn5 = (IBankNode) Naming.lookup("rmi://localhost/myBankNode5");
        
        try {

            System.out.println(ibn5.getId()); 

            IUser user = new UserImplem("linares","clement","22");
            
            IBankAction action = new BankActionImplemOpenAccount(user);
            IBankMessage ibm = new BankMessageImplem(ibn5.getId(),ibn5.getId(),23,4,EnumMessageType.BROADCAST,action);
            
            System.out.println("Creation 1");
            ibn5.onMessage(ibm);
            //System.out.println("Creation 2");
            //ibn5.onMessage(ibm);
            
            List<IResult<? extends Serializable>> listRes = ibn5.getResultForMessage(23);
            
            for(IResult<? extends Serializable> elem : listRes){
            	IAccount account = (IAccount)(elem.getData());
            	System.out.println(account.getAccountNumber());
            }
            
     
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}