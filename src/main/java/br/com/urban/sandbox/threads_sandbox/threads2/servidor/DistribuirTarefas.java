package br.com.urban.sandbox.threads_sandbox.threads2.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DistribuirTarefas implements Runnable {

	private Socket socket;
	private ServidorTarefas servidor;
	private ExecutorService threadPool;
	private BlockingQueue<String> filaComandos;

	public DistribuirTarefas(Socket socket, ServidorTarefas servidor, ExecutorService threadPool, BlockingQueue<String> filaComandos) {
		this.socket = socket;
		this.servidor = servidor;
		this.threadPool = threadPool;
		this.filaComandos = filaComandos;
	}

	@Override
	public void run() {
		try {
			System.out.println("Distribuindo tarefas para " + socket);

			Scanner entrada = new Scanner(socket.getInputStream());
			
			PrintStream saidaCliente = new PrintStream(socket.getOutputStream());
			
			while(entrada.hasNextLine()) {
				String comando = entrada.nextLine();
				
				System.out.println("Recebendo comando: " + comando);
				
				switch (comando) {
				case "c1":
					saidaCliente.println("Confirmação comando c1");
					ComandoC1 c1 = new ComandoC1(saidaCliente);
					this.threadPool.execute(c1);
					break;
				case "c2":
					saidaCliente.println("Confirmação comando c2");
					ComandoC2ChamaWS c2WS = new ComandoC2ChamaWS(saidaCliente);
					ComandoC2AcessaBanco c2Banco = new ComandoC2AcessaBanco(saidaCliente);
					Future<String> futureWS = this.threadPool.submit(c2WS);
					Future<String> futureBanco = this.threadPool.submit(c2Banco);
					
					threadPool.submit(new JuntaResultadosFutureWSFutureBanco(futureBanco, futureWS, saidaCliente));
					break;
				case "c3":
					this.filaComandos.put(comando);
					saidaCliente.println("Comando c3 adicionado na fila");
					break;
				case "fim":
					saidaCliente.println("Parando o servidor...");
					servidor.parar();
					break;
				default:
					saidaCliente.println("Comando não reconhecido");
				}
			}

			saidaCliente.close();
			entrada.close();
		}  catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

