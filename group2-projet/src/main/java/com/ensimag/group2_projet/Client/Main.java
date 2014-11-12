package com.ensimag.group2_projet.Client;
//package fr.ensimag.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.ensimag.api.bank.IBankNode;





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
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}