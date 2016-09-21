package br.com.urban.sandbox.threads_sandbox.threads1;

public class TarefaAdicionarElementos implements Runnable {

	private int numeroDoThread;

	private Lista lista;
	public TarefaAdicionarElementos(Lista lista, int numeroDoThread) {
		this.lista = lista;
		this.numeroDoThread = numeroDoThread;
	}
	
	/*
	private List<String> lista;
	public TarefaAdicionarElementos(List<String> lista, int numeroDoThread) {
		this.lista = lista;
		this.numeroDoThread = numeroDoThread;
	}
	*/

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			lista.adicionaElementos("Thread " + numeroDoThread + " - " + i);
			//lista.add("Thread " + numeroDoThread + " - " + i);
		}
	}
}
