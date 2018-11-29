package br.edu.univas.si7.sd;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class ProcessaConexao implements Runnable {
	
	private Socket sock;
	
	public ProcessaConexao(Socket sock) {
		this.sock = sock;
	}
	
	@Override
	public void run() {
		try {
			IOHelper ioh = new IOHelper(sock);

			while(true) {
				System.out.println(" Lendo os dados .");
				String buffer = (String) ioh.receive();
				System.out.println(" Dados recebidos : " + buffer.toString());

				// processamento qualquer do pedido
				String resposta = "Hora certa: " + new Date();
				System.out.println(" Enviando a resposta : " + resposta);

				ioh.send(resposta);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
