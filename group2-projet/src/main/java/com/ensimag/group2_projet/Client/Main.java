package com.ensimag.group2_projet.Client;
//package fr.ensimag.client;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.ensimag.api.bank.IBankAction;
import com.ensimag.api.bank.IBankMessage;
import com.ensimag.api.bank.IBankNode;
import com.ensimag.api.message.EnumMessageType;
import com.ensimag.api.message.IMessage;
import com.ensimag.group2_projet.Server.Implem.BankActionImplem;
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
        IBankNode ibn = (IBankNode) Naming.lookup("rmi://localhost/myBankNode");
        
        try {
            System.out.println(ibn.getId());
            UserImplem user = new UserImplem("toto", "jack", "24");
            IBankAction ibankAction = new BankActionImplemOpenAccount(user);
            //BankActionImplemOpenAccount action = new BankActionImplemOpenAccount(user);
            IBankMessage mess = new BankMessageImplem(23,23,0,23,EnumMessageType.SINGLE_DEST,ibankAction);
            //Creation du compte en théorie
            //IBankMessage ibMessage = new BankMessageImplem();
            //ibn.onMessage(mess);
            if(mess instanceof IBankMessage){
            	System.out.println("Clermont caca");
            }else{
            	System.out.println("Ajaccio caca");
            }
            //IUser user = new UserImplem("linares","clement","22");
            
            //ibn.openAccount(user);
            //List<IAccount> list = ibn.getAccounts();
            //for(IAccount a : list){
            //	System.out.println(a.getAccountUser().getFirstName());
            //}
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}