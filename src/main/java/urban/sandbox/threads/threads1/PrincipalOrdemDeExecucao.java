package urban.sandbox.threads.threads1;

public class PrincipalOrdemDeExecucao {

	   public static void main(String[] args) {
	        new Thread(new TarefaImprimeNumeros()).start();
	        new Thread(new TarefaImprimeNumeros()).start();
	    }
	}