package br.com.urban.sandbox.threads_sandbox.threads2.servidor;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DistribuirTarefas implements Runnable {

	private Socket socket;

	public DistribuirTarefas(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			System.out.println("Distribuindo tarefas para " + socket);

			Scanner entrada = new Scanner(socket.getInputStream());
			while(entrada.hasNextLine()) {
				System.out.println("Recebendo comando: " + entrada.nextLine());
			}

			entrada.close();
		}  catch (IOException e) {
			e.printStackTrace();
		}

	}

}
