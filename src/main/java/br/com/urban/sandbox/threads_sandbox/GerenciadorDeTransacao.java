package br.com.urban.sandbox.threads_sandbox;

public class GerenciadorDeTransacao {

	public void begin() {

		System.out.println("Começando a transação");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
