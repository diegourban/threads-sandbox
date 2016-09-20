package br.com.urban.sandbox.threads_sandbox;

public class PrincipalBanheiro {
	
	public static void main(String[] args) {
		Banheiro banheiro = new Banheiro();
		
		Thread convidado1 = new Thread(new TarefaNumero1(banheiro), "João");
		Thread convidado2 = new Thread(new TarefaNumero2(banheiro), "Pedro");
		
		convidado1.start();
		convidado2.start();
	}

}
