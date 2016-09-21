package br.com.urban.sandbox.threads_sandbox.threads1;

public class PoolDeConexao {
	public String getConnection() {

		System.out.println("Emprestando conexão");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "connection";
	}
}
