package com.ensimag.group2_projet.Client;
//package fr.ensimag.client;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import com.ensimag.api.bank.IAccount;
import com.ensimag.api.bank.IBankAction;
import com.ensimag.api.bank.IBankMessage;
import com.ensimag.api.bank.IBankNode;
import com.ensimag.api.bank.IUser;
import com.ensimag.api.message.EnumMessageType;
import com.ensimag.api.message.IResult;
import com.ensimag.group2_projet.Server.Implem.BankActionImplemAddMoney;
import com.ensimag.group2_projet.Server.Implem.BankActionImplemCloseAccount;
import com.ensimag.group2_projet.Server.Implem.BankActionImplemOpenAccount;
import com.ensimag.group2_projet.Server.Implem.BankMessageImplem;
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
        //IBankNode ibn3 = (IBankNode) Naming.lookup("rmi://localhost/myBankNode3");
        //IBankNode ibn4 = (IBankNode) Naming.lookup("rmi://localhost/myBankNode4");
        IBankNode ibn5 = (IBankNode) Naming.lookup("rmi://localhost/myBankNode5");
        
        try {
        	
        	//Création d'un compte depuis le noeud 5 sur le noeud 4
            IUser user = new UserImplem("linares","clement","22"); 
            IBankAction action = new BankActionImplemOpenAccount(user);
            IBankMessage ibm = new BankMessageImplem(ibn5.getId(),ibn5.getId(),23,4,EnumMessageType.BROADCAST,action);
            
            System.out.println("Creation 1");
            ibn5.onMessage(ibm);
            
            List<IResult<? extends Serializable>> listRes = ibn5.getResultForMessage(23);
            long accountNumber = 0;
            for(IResult<? extends Serializable> elem : listRes){
            	IAccount account = (IAccount)(elem.getData());
            	accountNumber = account.getAccountNumber();
            	System.out.println(account.getAccountNumber());
            }
            
            
            //Ajout d'argent sur le compte crée précédement depuis le noeud 1
            System.out.println("Ajout 1");
            IBankAction action2 = new BankActionImplemAddMoney(200,accountNumber);
            IBankMessage ibm2 = new BankMessageImplem(ibn1.getId(),ibn1.getId(),10,4,EnumMessageType.BROADCAST,action2);
            ibn1.onMessage(ibm2);
            
            List<IResult<? extends Serializable>> listRes2 = ibn1.getResultForMessage(10);
  
            for(IResult<? extends Serializable> elem : listRes2){
            	int money = (Integer) elem.getData();
            	
            	System.out.println("Money on count "+ accountNumber +" : "+money);
            }
            
            //Suppression du compte depuis le noeud 2
            System.out.println("Supression 1");
            IBankAction action3 = new BankActionImplemCloseAccount(accountNumber);
            IBankMessage ibm3 = new BankMessageImplem(ibn2.getId(),ibn2.getId(),42,4,EnumMessageType.BROADCAST,action3);
            ibn2.onMessage(ibm3);
            
            List<IResult<? extends Serializable>> listRes3 = ibn2.getResultForMessage(42);
  
            for(IResult<? extends Serializable> elem : listRes3){
            	boolean close = (Boolean) elem.getData();
            	
            	System.out.println("Account  ? : "+ close);
            }
            
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}