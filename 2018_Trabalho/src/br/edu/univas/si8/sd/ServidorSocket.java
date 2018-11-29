package br.edu.univas.si8.sd;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServidorSocket {

	public static void main(String[] args) {
		try {
		System.out.println("Iniciando Servidor .");
		int port = 3210;
		
		//cria um socket para ficar escutando
		
		ServerSocket server = new ServerSocket(port);
		
			while(true) {
				System.out.println("Aceitando Conexão .");
				Socket sock = server.accept();
				
				IOHelper ioh = new IOHelper(sock);
						
				System.out.println("Lendo os dados .");
				
				String buffer = (String) ioh.receive();
				System.out.println("Dados recebidos ." + buffer.toString());
				
				//processamento qualquer de pedido
				String resposta = "Hora atual: " + new Date();
				
				System.out.println("Enviando a resposta: " + resposta);
				
				ioh.send(resposta);
				System.out.println("Fechando a conexao .");
				sock.close();
			}
		
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
