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
		String ipDoCliente = sock.getInetAddress().getHostAddress();
		try {
			IOHelper ioh = new IOHelper(sock);

			Room.getInstance().add(ioh, ipDoCliente);
			
			while(true) {
				System.out.println(" Lendo os dados .");
				String buffer = (String) ioh.receive();
				System.out.println(" Dados recebidos : " + buffer.toString());

				// processamento qualquer do pedido
//				String resposta = "Hora certa: " + new Date();
//				System.out.println(" Enviando a resposta : " + resposta);
//				ioh.send(resposta);
				Room.getInstance().sendAll(buffer);
			}
		} catch (IOException ioe) {
			Room.getInstance().remove(ipDoCliente);
			ioe.printStackTrace();
		} catch (ClassNotFoundException e) {
			Room.getInstance().remove(ipDoCliente);
			e.printStackTrace();
		}
		
	}

}
