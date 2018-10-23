import java.net.*;
import java.io.*;

/*
	ServerNode Class Version 1.0.1
	ServerNode.java documentation can be found in /docs folder.
	Last tested by Andrew Campagna 10/23/2018.

	Notes: Everything seems to be running efficiently!
**/

public class ServerNode {

	private static int NodeCount = 0;
	
	private int port;
	private String name;
	private DataInputStream input;
	private DataOutputStream output;
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

	// When a client connects to the server, both an inputstream and outputstream are established
	public boolean setup() throws IOException {
		socket = server.accept();
		input = new DataInputStream(new BufferedInputStream(socket.getInputStream())); 
        output = new DataOutputStream(socket.getOutputStream());
        return true;
	}

	// Concatenates together data from the inputstream until keyword 'end' which then returns the data in a String
	public String listen(String key) throws IOException {
		String line = "";
		String temp = "";
		while (!temp.equals(key)) {
			temp = input.readUTF();
			line += temp;
		}
		return line.substring(0, (line.length() - key.length()));
	}

	// Default listen method
	public String listen() throws IOException {
		String line = "";
		String temp = "";
		while (!temp.equals("end")) {
			temp = input.readUTF();
			line += temp;
		}
		return line.substring(0, (line.length() - 3));
	}

	// Simple way to serve the connected client responses
	public void write(String line) throws IOException {
		output.writeUTF(line);
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
