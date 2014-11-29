package com.ensimag.group2_projet.Server.Main;

import java.rmi.Naming;

import com.ensimag.api.bank.IBankNode;
import com.ensimag.group2_projet.Server.Implem.BankNodeImplem;

public class Main_4 {
	private void start(){
        try {
        	
            String url = "rmi://localhost/";
            IBankNode ibn3 = (IBankNode) Naming.lookup(url+"myBankNode3");
            
            //Creation du banque node
            IBankNode bankNode4 = new BankNodeImplem(4);
            
            //Creation des liaisons entre les banques nodes
            ibn3.addNeighboor(bankNode4);
            bankNode4.addNeighboor(ibn3);
            
            //Enregistrement du banque node
            Naming.rebind(url+"myBankNode4", bankNode4);
        
        } catch (Exception e) {
        	e.printStackTrace();
        }     
        System.out.println("system 4 is ready");
	}

	public static void main(String[] args)
	{
		Main_4 main = new Main_4();
		main.start();
	}
}
