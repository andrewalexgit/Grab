import java.net.*;
import java.io.*;

/*
	ServerNode Class Version 1.1.1
	ServerNode.java documentation can be found in /docs folder.
	Last tested by Andrew Campagna 10/27/2018.

	Notes: Major updates to the way that the Server's Rx/Tx functions work. The update
	has been successfully tested with the following clients: Java, PHP, and 
	a C/C++ loaded ESP8266 module. This is a major update and will now open doors
	for implementing a new feature which creates profile objects for the client connections.
**/

public class ServerNode {

	private static int NodeCount = 0;
	
	private int port;
	private String name;
	private InputStreamReader inputStream;
	private BufferedReader input;
	private PrintStream output;
	private ServerSocket server;
	private Socket socket;

	public ServerNode(int port, String name) {
		this.port = port;
		this.name = name;

		try {
			server = new ServerSocket(port);
		} 

		catch (IOException ioe) {
			System.out.println(ioe);
		}

		NodeCount++;
	}

	// no-arg opens ports and names consecutive with the number of nodes created
	public ServerNode() {
		this(5000 + NodeCount, ("Node_"+ NodeCount));
	}

	public boolean setup() throws IOException {
		socket = server.accept();
		inputStream = new InputStreamReader(socket.getInputStream());
		input = new BufferedReader(inputStream);
		output = new PrintStream(socket.getOutputStream());
        return true;
	}

	public String listen(String key) throws IOException {
		String line = "";
		String temp = "";
		while (!temp.equals(key)) {
			temp = input.readLine();
			line += temp;
		}
		return line.substring(0, (line.length() - key.length()));
	}

	public String listen() throws IOException {
		String line = "";
		String temp = "";
		while (!temp.equals("end")) {
			temp = input.readLine();
			line += temp;
		}
		return line.substring(0, (line.length() - 3));
	}

	/********************************
	 *								*
	 *	   Main Server Functions 	*	
	 *								*
	 *********************************/

	// Simple way to serve the connected client responses
	public void write(String line) throws IOException {
		output.println(line);
	}

	// Kills the entire node safely
	public void kill() throws IOException {
		input.close();
		output.close();
		socket.close();
		server.close();

		if (NodeCount > 0) {
			NodeCount--;
		}
	}

	public String getNodeName() {
		return name;
	}

	public int getNodePort() {
		return port;
	}

	public String toString() {
		return "name: " + name + " port: " + port;
	}

	public static int getNodeCount() {
		return NodeCount;
	}
}
