package br.edu.univas.si8.sd.calc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class IOHelper {
	Socket sock;

	public IOHelper(Socket sock) throws IOException {
		this.sock = sock;
	}

	public void send(Object objetc) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
		out.writeObject(objetc);
		out.flush();
	}

	public Object receive() throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
		return in.readObject();
	}
}
