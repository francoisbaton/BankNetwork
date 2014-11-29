package com.ensimag.group2_projet.Server.Main;

import java.rmi.Naming;

import com.ensimag.api.bank.IBankNode;
import com.ensimag.group2_projet.Server.Implem.BankNodeImplem;

public class Main_2 {
	private void start(){
        try {
        	
            String url = "rmi://localhost/";
            IBankNode ibn1 = (IBankNode) Naming.lookup(url+"myBankNode1");
            
            //Creation du banque node
            IBankNode bankNode2 = new BankNodeImplem(2);
            
            //Creation des liaisons entre les banques nodes
            ibn1.addNeighboor(bankNode2);
            bankNode2.addNeighboor(ibn1);
            
            //Enregistrement du banque node
            Naming.rebind(url+"myBankNode2", bankNode2);
        
        } catch (Exception e) {
        	e.printStackTrace();
        }     
        System.out.println("system 2 is ready");
	}

	public static void main(String[] args)
	{
		Main_2 main = new Main_2();
		main.start();
	}
}

