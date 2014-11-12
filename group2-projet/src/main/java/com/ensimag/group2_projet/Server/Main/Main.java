package com.ensimag.group2_projet.Server.Main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.ensimag.api.bank.IUser;
import com.ensimag.group2_projet.Server.Implem.BankNodeImplem;
import com.ensimag.group2_projet.Server.Implem.UserImplem;

public class Main {
	
	private void start(){
        try {
            // create on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
             
            // create a new service named myMessage
            BankNodeImplem bankNode = new BankNodeImplem(23);
            IUser user = new UserImplem("linares","clement","22");
            
            bankNode.openAccount(user);
            
            registry.rebind("myBankNode", bankNode);
        } catch (Exception e) {
            e.printStackTrace();
        }     
        System.out.println("system is ready");
    }
   
    public static void main(String[] args)
    {
        Main main = new Main();
        main.start();
   }

}
