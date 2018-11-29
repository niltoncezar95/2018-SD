package br.edu.univas.si7.sd.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServidorSocket {

	public static void main(String[] args) {
		try {
			System.out.println(" Iniciando servidor .");
			int port = 3210;

			// cria um socket para ficar escutando
			ServerSocket server = new ServerSocket(port); 

			while (true) {
				System.out.println(" Aceitando a conexao .");
				Socket sock = server.accept();
				
				IOHelper ioh = new IOHelper(sock);

				System.out.println(" Lendo os dados .");
				
				String buffer = (String) ioh.receive();
				System.out.println(" Dados recebidos : " + buffer.toString());

				// processamento qualquer do pedido
				String resposta = "Hora certa: " + new Date();

				System.out.println(" Enviando a resposta : " + resposta);

				ioh.send(resposta);
				System.out.println(" Fechando a conexao .");
				sock.close();
			}
			// server.close ();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}