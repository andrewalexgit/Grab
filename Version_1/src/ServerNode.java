import java.net.*;
import java.io.*;

/*
	ServerNode Class Version 1.1.2
	ServerNode.java documentation can be found in /docs folder.
	Last tested by Andrew Campagna 10/28/2018.

	Notes: ServerNode works excellent working as a singular server process, but
	the next step from here will be testing the creation of multiple nodes running
	as seperate threaded processes.
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

	/* Waits for a client to successfully connect, then constructs input 
	   and output streams for the server. **/
	public boolean setup() throws IOException {
		socket = server.accept();
		inputStream = new InputStreamReader(socket.getInputStream());
		input = new BufferedReader(inputStream);
		output = new PrintStream(socket.getOutputStream());
        return true;
	}

	/* Listens for incoming data, and concatenates it into one String object until
	   the input stream hits a 'key' word to end the listening stream. **/
	public String listen(String key) throws IOException {
		String line = "";
		String temp = "";
		while (!temp.equals(key)) {
			temp = input.readLine();
			line += temp;
		}
		return line.substring(0, (line.length() - key.length()));
	}

	/* No-argument listener that uses preset 'key' value 'end' to end listening stream. **/
	public String listen() throws IOException {
		String line = "";
		String temp = "";
		while (!temp.equals("end")) {
			temp = input.readLine();
			line += temp;
		}
		return line.substring(0, (line.length() - 3));
	}

	/* Simple output stream writing method **/
	public void write(String line) throws IOException {
		output.println(line);
	}

	/* Kills the present ServerNode and decrements the static NodeCount **/
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
