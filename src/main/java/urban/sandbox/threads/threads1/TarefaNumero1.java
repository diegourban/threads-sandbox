package urban.sandbox.threads.threads1;

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
