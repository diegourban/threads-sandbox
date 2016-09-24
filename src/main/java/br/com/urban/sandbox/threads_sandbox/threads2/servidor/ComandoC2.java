package br.com.urban.sandbox.threads_sandbox.threads2.servidor;

import java.io.PrintStream;

public class ComandoC2 implements Runnable {

	private PrintStream saidaCliente;

	public ComandoC2(PrintStream saidaCliente) {
		this.saidaCliente = saidaCliente;
	}

	@Override
	public void run() {
		System.out.println("Executando comando c2");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		saidaCliente.println("Comando c2 executando com sucesso!");
	}

}
