import java.io.*;

/*
	Server Class Version 1.0.1
	Server.java documentation can be found in /docs folder.
	Last tested by Andrew Campagna 10/23/2018.

	Notes: Everything seems to be running fine so far.
**/

public class Server {

	public static void main(String[] args) throws IOException {

		boolean running = true;
		String data = "";

		System.out.println("############# GRAB ########### VERSION 1.0.0 ############");
		// Init a server node.
		ServerNode grabNode = new ServerNode();
		System.out.println("Currently there is " + grabNode.getNodeCount() + " nodes running");
		// Utilize the server node's boolean connection method to perform
		// instructions after a client connects
		System.out.print("Waiting for client to connect...");
		if (grabNode.setup()) {
			System.out.print("Done!\n");
			// Control logic
			do {
				data = grabNode.listen();
				if (data.equals("end@")) {
					running = false;
					System.out.println("Shutting down...");
					grabNode.write("end");
				} else {
					System.out.println(data);
					grabNode.write(grabNode + " > Message recieved!");
				}
			} while(running);
		}

		System.out.println("Goodbye!");
	}
}
