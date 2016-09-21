package br.com.urban.sandbox.threads_sandbox;

public class PrincipalLista {
	
	public static void main(String[] args) throws InterruptedException {

		
		Lista lista = new Lista();

        //criando 10 thread e inicializando
        for(int i = 0; i<10; i++) {
            new Thread(new TarefaAdicionarElementos(lista, i)).start();
        }
        
        new Thread(new TarefaImprimir(lista)).start();

		/*
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
        */
    }

}
