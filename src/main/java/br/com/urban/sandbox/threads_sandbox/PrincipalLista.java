package br.com.urban.sandbox.threads_sandbox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class PrincipalLista {
	
	public static void main(String[] args) throws InterruptedException {

		/*
		Lista lista = new Lista();

        //criando 10 thread e inicializando
        for(int i = 0; i<10; i++) {
            new Thread(new TarefaAdicionarElementos(lista, i)).start();
        }

        //dorme por 2s
        Thread.sleep(2000);

        //imprimindo a nossa lista
        for (int i = 0; i < lista.tamanho(); i++) {
            System.out.println(i + " - " + lista.pegaElemento(i));
        } 
        */
		
		
		// List<String> lista = new ArrayList<String>(); // não é thread safe
		//List<String> lista = Collections.synchronizedList(new ArrayList<String>()); // usar synchronizedList
		List<String> lista = new Vector<String>(); // ou vector que é thread safe

        for (int i = 0; i < 10; i++) {
            new Thread(new TarefaAdicionarElementos(lista, i)).start();
        }

        Thread.sleep(2000);

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + " - " + lista.get(i));
        }
    }

}
