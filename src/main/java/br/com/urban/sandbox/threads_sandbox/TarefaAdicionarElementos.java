package br.com.urban.sandbox.threads_sandbox;

import java.util.List;

public class TarefaAdicionarElementos implements Runnable {

	//private Lista lista;
	private List<String> lista;
	private int numeroDoThread;

//	public TarefaAdicionarElementos(Lista lista, int numeroDoThread) {
//		this.lista = lista;
//		this.numeroDoThread = numeroDoThread;
//	}
	
	public TarefaAdicionarElementos(List<String> lista, int numeroDoThread) {
		this.lista = lista;
		this.numeroDoThread = numeroDoThread;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			//lista.adicionaElementos("Thread " + numeroDoThread + " - " + i);
			lista.add("Thread " + numeroDoThread + " - " + i);
		}
	}
}