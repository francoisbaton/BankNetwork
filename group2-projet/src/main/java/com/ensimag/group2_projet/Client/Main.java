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
           
            ibn.openAccount(user);
            //System.out.println(ibn.getAccount().getTotal());
            
            /*if(ibn.closeAccount(0))
            	System.out.println("Compte supprimé avec succès\n");*/
            
            List<IAccount> list = ibn.getAccounts();
            
            
            if(list.isEmpty()){
            	System.out.println("ok");
            }else{
            	for(IAccount acc : list){
            		System.out.println("Nom :" + acc.getAccountUser().getFirstName());
            		System.out.println("Prénom :" + acc.getAccountUser().getName());
            		System.out.println(acc.getAccountNumber());
            		System.out.println(ibn.closeAccount(acc.getAccountNumber()));
            	}
            }
            
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}