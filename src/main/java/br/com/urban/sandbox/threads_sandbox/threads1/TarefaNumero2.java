package br.com.urban.sandbox.threads_sandbox.threads1;

public class TarefaNumero2 implements Runnable {

	private Banheiro banheiro;

	public TarefaNumero2(Banheiro banheiro) {
		super();
		this.banheiro = banheiro;
	}

	@Override
	public void run() {
		banheiro.fazNumero2();
	}

}
