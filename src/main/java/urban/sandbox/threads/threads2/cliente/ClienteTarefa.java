package urban.sandbox.threads.threads2.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTarefa {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket socket = new Socket("localhost", 12345);
		System.out.println("Conexão estabelecida");
		
		Thread threadEnviaComando = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Pode enviar comandos!");
					PrintStream saida = new PrintStream(socket.getOutputStream());
					Scanner teclado = new Scanner(System.in);
					while(teclado.hasNextLine()) {
						String comando = teclado.nextLine();
						if(comando.trim().equals("")) {
							break;
						}
						
						System.out.println("Enviando comando: " + comando);
						saida.println(comando);
					}
					teclado.close();
					saida.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		threadEnviaComando.start();
		
		Thread threadRecebeResposta = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Recendo dados do servidor");
					Scanner respostaServidor = new Scanner(socket.getInputStream());
					while(respostaServidor.hasNextLine()) {
						String linha = respostaServidor.nextLine();
						System.out.println(linha);
					}
					respostaServidor.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		threadRecebeResposta.start();
		
		threadEnviaComando.join(); // thread main deve aguardar a threadEnviaComando
		
		System.out.println("Fechando socket do cliente");
		socket.close();
	}

}
