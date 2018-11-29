package br.edu.univas.si7.sd;

import java.io.IOException;
import java.net.Socket;

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

			System.out.println("O cliente " + ipDoCliente + " conectou.");
			Room.getInstance().add(ioh, ipDoCliente);
			
			while(true) {
				System.out.println(" Lendo os dados .");
				String buffer = (String) ioh.receive();
				System.out.println(" Dados recebidos : " + buffer.toString());

				Room.getInstance().sendAll(buffer);
			}
		} catch (IOException ioe) {
			System.out.println("O cliente " + ipDoCliente + " saiu.");
			Room.getInstance().remove(ipDoCliente);
		} catch (ClassNotFoundException e) {
			Room.getInstance().remove(ipDoCliente);
			e.printStackTrace();
		}
		
	}

}
