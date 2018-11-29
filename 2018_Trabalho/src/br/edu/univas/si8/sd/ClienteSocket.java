package br.edu.univas.si8.sd;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteSocket {

	public static void main(String[] args) {
		
		int port = 3210;
		
		try {
			Socket sock = new Socket("localhost", port); // Cria um socket
			
			IOHelper ioh = new IOHelper(sock);
			
			Scanner sc = new Scanner(System.in);
			String msg = sc.next() + sc.nextLine(); // Lê um trecho do teclado
			
			while(!msg.equals("exit")) {
			
			//envia a mensagem para o socket via ObjectOutputStream
			System.out.println("Enviando" + msg);
			ioh.send(msg);
			
			//receve a resposta do socket via ObjectInputStream
			String resp = (String) ioh.receive();
			}
		sock.close();
		
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
