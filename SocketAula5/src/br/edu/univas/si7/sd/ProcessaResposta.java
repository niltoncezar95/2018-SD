package br.edu.univas.si7.sd;

import java.io.IOException;

public class ProcessaResposta implements Runnable {

	private IOHelper ioh;
	
	public ProcessaResposta(IOHelper ioh) {
		this.ioh = ioh;
	}
	
	@Override
	public void run() {

		// recebe a resposta do socket via ObjectInputStream
		try {
			while(true) {
				String resp = (String) ioh.receive();
				if (resp != null) {
					System.out.println("Resposta: " + resp);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
