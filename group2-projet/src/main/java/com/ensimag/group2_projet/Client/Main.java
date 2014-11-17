package com.ensimag.group2_projet.Client;
//package fr.ensimag.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import com.ensimag.api.bank.IAccount;
import com.ensimag.api.bank.IBankNode;
import com.ensimag.api.bank.IUser;
import com.ensimag.group2_projet.Server.Implem.UserImplem;




/**
 *
 * @author reignier
 */
public class Main {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException
    {
        IBankNode ibn = (IBankNode) Naming.lookup("rmi://localhost/myBankNode");
        
        try {
            System.out.println(ibn.getId()); 

            IUser user = new UserImplem("linares","clement","22");
            
            //IBankAction iba = new BankActionImplemOpenAccount(user);
            //IBankMessage ibm = new BankMessageImplem(23, 23, 10, 23, EnumMessageType.SINGLE_DEST, iba);
            //ibn.onMessage(ibm);
           
            ibn.openAccount(user);
            System.out.println(ibn.getAccount(0).getTotal());
            
            if(ibn.closeAccount(0))
            	System.out.println("Compte supprimé avec succès\n");
            
            List<IAccount> list = ibn.getAccounts();
            
            if(list.isEmpty())
            	System.out.println("ok");
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}