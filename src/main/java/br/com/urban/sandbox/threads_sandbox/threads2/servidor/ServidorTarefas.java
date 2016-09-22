package br.com.urban.sandbox.threads_sandbox.threads2.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {

	private ServerSocket servidor;
	private ExecutorService threadPool;
	
	// volatile evita o uso desse cache e faz que as threads sempre acessem a memória principal.
	//private volatile boolean estaRodando;
	
	private AtomicBoolean estaRodando; // wrapper de volatile boolean


	public ServidorTarefas() throws IOException {
		System.out.println("Iniciando servidor...");
		servidor = new ServerSocket(12345);

		// ExecutorService threadPool = Executors.newFixedThreadPool(2);
		threadPool = Executors.newCachedThreadPool(); // cresçe dinamicamente
		this.estaRodando = new AtomicBoolean(true);
	}

	public static void main(String[] args) throws IOException {
		ServidorTarefas servidorTarefas = new ServidorTarefas();
		servidorTarefas.rodar();
	}

	private void rodar() throws IOException {
		while (this.estaRodando.get()) {
			try {
				Socket socket = servidor.accept();
				System.out.println("Aceitou novo cliente na porta " + socket.getPort());
				DistribuirTarefas distribuirTarefas = new DistribuirTarefas(socket, this);
				threadPool.execute(distribuirTarefas);
			} catch (SocketException  e) {
				System.out.println("SocketException, está rodando? " + this.estaRodando);
			}
		}
	}

	void parar() throws IOException {
		this.estaRodando.set(false);;
		System.out.println("Desligando servidor...");
		threadPool.shutdown();
		servidor.close();
		System.out.println("Sevidor desligado");
	}

}
