package br.com.urban.sandbox.threads_sandbox.threads2.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class DistribuirTarefas implements Runnable {

	private Socket socket;
	private ServidorTarefas servidor;

	public DistribuirTarefas(Socket socket, ServidorTarefas servidor) {
		this.socket = socket;
		this.servidor = servidor;
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
					break;
				case "c2":
					saidaCliente.println("Confirmação comando c2");
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
		}

	}

}
