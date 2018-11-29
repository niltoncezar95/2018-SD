package br.edu.univas.si7.sd;

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
				
				//aqui deve criar a thread
				ProcessaConexao pc = new ProcessaConexao(sock);
				Thread th = new Thread(pc);
				th.start();
				
//				System.out.println(" Fechando a conexao .");
//				sock.close();
			}
			// server.close ();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}