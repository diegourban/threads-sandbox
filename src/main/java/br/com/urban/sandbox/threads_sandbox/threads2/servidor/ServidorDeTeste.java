package br.com.urban.sandbox.threads_sandbox.threads2.servidor;

public class ServidorDeTeste {

	// atributo e main comentados
	private boolean estaRodando;
	
	public static void main(String[] args) {
		ServidorDeTeste servidorDeTeste = new ServidorDeTeste();
		servidorDeTeste.rodar();
	}

	private void rodar() {
		try {
			new Thread(new Runnable() {

				public void run() {
					System.out.println("Servidor começando, estaRodando = " + estaRodando);
					while (!estaRodando) {
					}

					if (estaRodando) {
						throw new RuntimeException("Deu erro na thread...");
					}

					System.out.println("Servidor rodando, estaRodando = " + estaRodando);

					while (estaRodando) {
					}

					System.out.println("Servidor terminando, estaRodando = " + estaRodando);
				}
			}).start();
		} catch (Exception e) {
			System.out.println("Catch na thread MAIN " + e.getMessage());
		}
	}

	// método alterandoAtributo comentado
}
