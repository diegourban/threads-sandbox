package br.com.urban.sandbox.threads_sandbox;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banheiro {

	private Lock lock = new ReentrantLock();

	public void fazNumero1() {
		String name = Thread.currentThread().getName();

		System.out.println(name + " batendo na porta");

		// lock.lock(); // explícito
		synchronized (this) { // implícito

			System.out.println(name + " Entrando no banheiro");
			System.out.println(name + " Fazendo coisa rápida");

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

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
			System.out.println(name + " Fazendo coisa demorada");

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(name + " Dando descarga");
			System.out.println(name + " Lavando mão");
			System.out.println(name + " Saindo do banheiro");
		}
		// lock.unlock();
	}

}
