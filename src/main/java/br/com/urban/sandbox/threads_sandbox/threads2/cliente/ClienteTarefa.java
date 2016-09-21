package br.com.urban.sandbox.threads_sandbox.threads2.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTarefa {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 12345);
		
		System.out.println("Conexão estabelecida");
		
		PrintStream saida = new PrintStream(socket.getOutputStream());
		
		String comando = "hello";
		System.out.println("Enviando comando: " + comando);
		saida.println(comando);
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		saida.close();
		scanner.close();
		socket.close();
	}

}
