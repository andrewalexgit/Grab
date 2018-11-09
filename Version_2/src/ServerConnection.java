/*
*	Handles connection to server
*
*	Developed by, Andrew C.
**/

import java.net.*;
import java.io.*;

public class ServerConnection extends ServerObj {

	private ServerIO serverio;
	private ServerSocket server;
	private Socket socket;
	
	public ServerConnection(int port) {
		super(port);
	}

	public boolean setup() {

		try {

			server = new ServerSocket(getPort());
			socket = server.accept();
			serverio = new ServerIO(socket);
			return true;

		} catch (IOException ioe) {

			// <0> Failed to establish connection due to IO Exception
			System.out.println("<0>");
			return false;

		} catch (Exception ex) {

			// <1> Failed to establish connection due to Exception
			System.out.println("<1>");
			return false;

		}
	}

	public String toString() {
		return getName() + " on port " + getPort();
	}

	/***********************************
	 *	Server Input and Output methods.
	 **/
	public String listen() {

		try {

			return serverio.listen();

		} catch (IOException ioe) {

			// <2> Incoming data was corrupted and is being thrown away.
			System.out.println("<2>");
			return "<2> Incoming data was corrupted and is being thrown away.";

		}
	}

	public void write(String data) {
		serverio.write(data);
	}
	/*********************************/
}