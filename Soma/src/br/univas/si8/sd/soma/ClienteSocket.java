package br.univas.si8.sd.soma;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteSocket {

	public static void main(String[] args) {
		int valorA = 18;
		int valorB = 10;
		String enderecoServidor = "127.0.0.1";
		int portaServidor = 8000;
		Socket socketServidor = null;

		try {
			socketServidor = new Socket(enderecoServidor, portaServidor);
			ObjectInputStream recebe = new ObjectInputStream(socketServidor.getInputStream());
			ObjectOutputStream envia = new ObjectOutputStream(socketServidor.getOutputStream());

			envia.writeInt(valorA);
			envia.writeInt(valorB);
			envia.flush();

			int soma = recebe.readInt();
			System.out.println("Total de " + valorA + " + " + valorB + " = " + soma);

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			if (socketServidor != null)
				try {
					socketServidor.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}
	}

}
