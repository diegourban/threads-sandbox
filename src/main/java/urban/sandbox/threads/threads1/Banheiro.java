package urban.sandbox.threads.threads1;

public class Banheiro {
	
	private boolean ehSujo = true;

	//private Lock lock = new ReentrantLock();

	public void fazNumero1() {
		String name = Thread.currentThread().getName();

		System.out.println(name + " batendo na porta");

		// lock.lock(); // explícito
		synchronized (this) { // implícito

			System.out.println(name + " Entrando no banheiro");
			
			while (this.ehSujo) {
	            esperaLaFora(name);
	        }
			
			System.out.println(name + " Fazendo coisa rápida");

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.ehSujo = true;

			System.out.println(name + " Dando descarga");
			System.out.println(name + " Lavando mão");
			System.out.println(name + " Saindo do banheiro");

		}
		// lock.unlock();
	}

	public void fazNumero2() {
		String name = Thread.currentThread().getName();

		System.out.println(name + " batendo na porta");

		// lock.lock(); // explícito
		synchronized (this) { // implícito
			System.out.println(name + " Entrando no banheiro");
			
			while (this.ehSujo) {
	            esperaLaFora(name);
	        }
			
			System.out.println(name + " Fazendo coisa demorada");

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.ehSujo = true;

			System.out.println(name + " Dando descarga");
			System.out.println(name + " Lavando mão");
			System.out.println(name + " Saindo do banheiro");
		}
		// lock.unlock();
	}
	
	public void limpa() {
		String nome = Thread.currentThread().getName();

	    System.out.println(nome + " batendo na porta");

	    synchronized (this) {

	        System.out.println(nome + " entrando no banheiro");

	        if (!this.ehSujo) {
	            System.out.println(nome + ", não está sujo, vou sair");
	            return;
	        }

	        System.out.println(nome + " limpando o banheiro");
	        this.ehSujo = false;

	        try {
	            Thread.sleep(13000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        this.notifyAll();

	        System.out.println(nome + " saindo do banheiro");
	    }
	}
	
	private void esperaLaFora(String nome) {

	    System.out.println(nome + ", eca, banheiro está sujo");
	    try {
	        this.wait();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

}
