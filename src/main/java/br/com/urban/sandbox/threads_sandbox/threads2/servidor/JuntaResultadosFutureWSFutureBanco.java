package br.com.urban.sandbox.threads_sandbox.threads2.servidor;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JuntaResultadosFutureWSFutureBanco implements Callable<Void> {

	private Future<String> futureBanco;
	private Future<String> futureWS;
	private PrintStream saidaCliente;

	public JuntaResultadosFutureWSFutureBanco(Future<String> futureBanco, Future<String> futureWS,
			PrintStream saidaCliente) {
				this.futureBanco = futureBanco;
				this.futureWS = futureWS;
				this.saidaCliente = saidaCliente;
	}

	@Override
	public Void call() {
		System.out.println("Aguardando resultados do Future WS e Banco");
		try {
			String resultadoWS = this.futureWS.get(20, TimeUnit.SECONDS);
			String resultadBanco = this.futureBanco.get(20, TimeUnit.SECONDS);
			this.saidaCliente.println("Resultado comando c2: " + resultadoWS + ", " + resultadBanco);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			System.out.println("Timeout na execução do comando c2");
			this.saidaCliente.println("Timeout na execução do comando c2");
			this.futureWS.cancel(true);
			this.futureBanco.cancel(true);
		}
		
		System.out.println("Finalizou JuntaResultadosFutureWSFutureBanco");
		
		return null;
	}

}
