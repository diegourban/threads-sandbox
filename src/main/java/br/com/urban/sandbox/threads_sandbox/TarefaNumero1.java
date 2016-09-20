package br.com.urban.sandbox.threads_sandbox;

public class TarefaNumero1 implements Runnable {

	private Banheiro banheiro;

	public TarefaNumero1(Banheiro banheiro) {
		super();
		this.banheiro = banheiro;
	}

	@Override
	public void run() {
		banheiro.fazNumero1();
	}

}
