package com.ensimag.group2_projet.Server.Main;

import java.rmi.Naming;

import com.ensimag.api.bank.IBankNode;
import com.ensimag.group2_projet.Server.Implem.BankNodeImplem;

public class Main_5 {
	private void start(){
        try {
        	
            String url = "rmi://localhost/";
            IBankNode ibn3 = (IBankNode) Naming.lookup(url+"myBankNode3");
            IBankNode ibn4 = (IBankNode) Naming.lookup(url+"myBankNode4");
            
            //Creation du banque node
            IBankNode bankNode5 = new BankNodeImplem(5);
            
            //Creation des liaisons entre les banques nodes
            ibn3.addNeighboor(bankNode5);
            bankNode5.addNeighboor(ibn3);
            
            ibn4.addNeighboor(bankNode5);
            bankNode5.addNeighboor(ibn4);
            
            //Enregistrement du banque node
            Naming.rebind(url+"myBankNode5", bankNode5);
        
        } catch (Exception e) {
        	e.printStackTrace();
        }     
        System.out.println("system 5 is ready");
	}

	public static void main(String[] args)
	{
		Main_5 main = new Main_5();
		main.start();
	}
}
