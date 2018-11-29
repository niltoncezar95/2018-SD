package br.edu.univas.si7.sd;

import java.io.IOException;
import java.util.HashMap;

public class Room {

	private static Room instance = null;
	private HashMap<String, IOHelper> participants = 
			new HashMap<String, IOHelper>();
	
	private Room() {}
	
	public static Room getInstance() {
		if(instance == null) {
			instance = new Room();
		}
		return instance;
	}

	public void add(IOHelper participant, String ip) {
		participants.put(ip, participant);
		System.out.println("Quantidade de clientes: " + participants.size());
	}
	
	public void remove(String ip) {
		participants.remove(ip);
	}
	
	public void sendAll(String msg) {
		for (IOHelper io : participants.values()) {
			try {
				io.send(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
