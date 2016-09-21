package br.com.urban.sandbox.threads_sandbox.threads2.cliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTarefa {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 12345);
		
		System.out.println("Conexão estabelecida");
		
		Scanner scanner = new Scanner(System.in);
		
		scanner.nextLine();
		
		socket.close();
	}

}
