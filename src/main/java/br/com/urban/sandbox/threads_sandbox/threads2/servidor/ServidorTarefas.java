package br.com.urban.sandbox.threads_sandbox.threads2.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTarefas {

	public static void main(String[] args) throws IOException {
		System.out.println("Iniciando servidor...");
		ServerSocket servidor = new ServerSocket(12345);
		
		while(true){
			Socket socket = servidor.accept();
			System.out.println("Aceitou novo cliente na porta " + socket.getPort());
			
			DistribuirTarefas distribuirTarefas = new DistribuirTarefas(socket);
			Thread threadClient = new Thread(distribuirTarefas);
			threadClient.start();
		}
	}

}
