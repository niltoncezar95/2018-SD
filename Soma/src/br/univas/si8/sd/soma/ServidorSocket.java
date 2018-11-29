package br.univas.si8.sd.soma;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {

	public static void main(String[] args) {
		Socket socketCliente = null;
		int portaServidor = 8000;
		int valorA, valorB, soma;

		try {
			ServerSocket listenSocket = new ServerSocket(portaServidor);
			while (true) {
				socketCliente = listenSocket.accept();

				IOHelper ioh = new IOHelper(socketCliente);

				System.out.println("Lendo os dados .");

				// String buffer = (String) ioh.receive();

				// System.out.println("Dados recebidos ." + buffer.toString());

				ObjectOutputStream envia = new ObjectOutputStream(socketCliente.getOutputStream());
				ObjectInputStream recebe = new ObjectInputStream(socketCliente.getInputStream());

				valorA = recebe.readInt();
				valorB = recebe.readInt();
				System.out.println("Estou com os seguintes valores A:" + valorA + " B:" + valorB);
				soma = valorA + valorB;
				envia.writeInt(soma);
				envia.flush();
			}
		} catch (Exception e) {
			System.out.println("Erro:" + e.getMessage());
		} finally {
			try {
				if (socketCliente != null) {
					socketCliente.close();
				}
			} catch (IOException e) {
			}
		}
	}
}
