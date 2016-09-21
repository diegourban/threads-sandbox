package br.com.urban.sandbox.threads_sandbox.threads1;

public class RespostaMain {

	public static void main(String[] args) {

		Runnable tarefa = new ImprimeString();
		Thread thread = new Thread(tarefa);
		thread.start();
	}
}
