package com.ensimag.group2_projet.Server.Main;

import java.rmi.Naming;

import com.ensimag.api.bank.IBankNode;
import com.ensimag.group2_projet.Server.Implem.BankNodeImplem;

public class Main_3 {
	private void start(){
        try {
        	
            String url = "rmi://localhost/";
            IBankNode ibn2 = (IBankNode) Naming.lookup(url+"myBankNode2");
            
            //Creation du banque node
            IBankNode bankNode3 = new BankNodeImplem(3);
            
            //Creation des liaisons entre les banques nodes
            ibn2.addNeighboor(bankNode3);
            bankNode3.addNeighboor(ibn2);
            
            //Enregistrement du banque node
            Naming.rebind(url+"myBankNode3", bankNode3);
        
        } catch (Exception e) {
        	e.printStackTrace();
        }     
        System.out.println("system 3 is ready");
	}

	public static void main(String[] args)
	{
		Main_3 main = new Main_3();
		main.start();
	}
}
