package com.ensimag.group2_projet.Server.Main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.ensimag.api.bank.IBankNode;
import com.ensimag.group2_projet.Server.Implem.BankNodeImplem;

public class Main {
	
	private void start(){
        try {
            // create on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            //Creation des banques nodes
            IBankNode bankNode1 = new BankNodeImplem(1);
            
            IBankNode bankNode2 = new BankNodeImplem(2);
            
            IBankNode bankNode3 = new BankNodeImplem(3);
            
            IBankNode bankNode4 = new BankNodeImplem(4);
            
            IBankNode bankNode5 = new BankNodeImplem(5);
            
            //Creation des liaisons entre les banques nodes
            bankNode1.addNeighboor(bankNode2);
            
            bankNode2.addNeighboor(bankNode1);
            bankNode2.addNeighboor(bankNode5);
            
            bankNode3.addNeighboor(bankNode4);
            bankNode3.addNeighboor(bankNode5);
            
            bankNode4.addNeighboor(bankNode3);
            bankNode4.addNeighboor(bankNode5);
            
            bankNode5.addNeighboor(bankNode2);
            bankNode5.addNeighboor(bankNode3);
            bankNode5.addNeighboor(bankNode4);
            
            //Enregistrement des banques
            registry.rebind("myBankNode1", bankNode1);
            registry.rebind("myBankNode2", bankNode2);
            registry.rebind("myBankNode3", bankNode3);
            registry.rebind("myBankNode4", bankNode4);
            registry.rebind("myBankNode5", bankNode5);
            
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
