package com.ensimag.group2_projet.Client;
//package fr.ensimag.client;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import com.ensimag.api.bank.IAccount;
import com.ensimag.api.bank.IBankAction;
import com.ensimag.api.bank.IBankMessage;
import com.ensimag.api.bank.IBankNode;
import com.ensimag.api.bank.IUser;
import com.ensimag.api.message.EnumMessageType;
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
        IBankNode ibn3 = (IBankNode) Naming.lookup("rmi://localhost/myBankNode3");
        IBankNode ibn4 = (IBankNode) Naming.lookup("rmi://localhost/myBankNode4");
        IBankNode ibn5 = (IBankNode) Naming.lookup("rmi://localhost/myBankNode5");
        
        try {

            System.out.println(ibn1.getId()); 

            IUser user = new UserImplem("linares","clement","22");
           
            /*ibn1.openAccount(user);
            
            List<IAccount> list = ibn1.getAccounts();
            
            if(list.isEmpty()){
            	System.out.println("ok");
            }else{
            	for(IAccount acc : list){
            		System.out.println("Nom :" + acc.getAccountUser().getFirstName());
            		System.out.println("Prénom :" + acc.getAccountUser().getName());
            		System.out.println(acc.getAccountNumber());
            		System.out.println(ibn1.closeAccount(acc.getAccountNumber()));
            	}
            }*/
            
            IBankAction action = new BankActionImplemOpenAccount(user);
            IBankMessage ibm = new BankMessageImplem(ibn1.getId(),ibn1.getId(),23,ibn5.getId(),EnumMessageType.BROADCAST,action);
            
            ibn1.onMessage(ibm);
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}