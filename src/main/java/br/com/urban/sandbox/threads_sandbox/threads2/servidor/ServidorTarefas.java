package br.com.urban.sandbox.threads_sandbox.threads2.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {

	private ServerSocket servidor;
	private ExecutorService threadPool;
	
	// volatile evita o uso desse cache e faz que as threads sempre acessem a memória principal.
	//private volatile boolean estaRodando;
	
	private AtomicBoolean estaRodando; // wrapper de volatile boolean
	
	private BlockingQueue<String> filaComandos;


	public ServidorTarefas() throws IOException {
		System.out.println("Iniciando servidor...");
		servidor = new ServerSocket(12345);

		//ThreadFactory defaultFactory = Executors.defaultThreadFactory();
		//threadPool = Executors.newFixedThreadPool(2, new FabricaDeThreads(defaultFactory));
		threadPool = Executors.newCachedThreadPool(); // cresçe dinamicamente
		this.estaRodando = new AtomicBoolean(true);
		this.filaComandos = new ArrayBlockingQueue<String>(2);
		iniciarConsumidores();
	}
	
	private void iniciarConsumidores() {
		int quantidadeConsumidores = 2;
		for(int i = 0; i < quantidadeConsumidores; i++) {
			TarefaConsumir tarefa = new TarefaConsumir(filaComandos);
			threadPool.execute(tarefa);
		}
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
				DistribuirTarefas distribuirTarefas = new DistribuirTarefas(socket, this, this.threadPool, this.filaComandos);
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
