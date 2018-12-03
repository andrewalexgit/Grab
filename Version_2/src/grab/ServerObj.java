package grab;

/*
*	Instance of a server object
*
*	Developed by, Andrew C.
**/

public abstract class ServerObj { 

	/*
	 * Keeps track of open server ID's.
	 **/
	static int serverid = 0;

	/*
	 * Holds string for name of the server
	 **/
	String name;

	/*
	 *	Every server object must be assigned a port, this is immutable 
	 *	for the lifetime of the server object.
	 **/
	int port;

	protected ServerObj(int port) {
		this.port = port;
		serverid++;
		name = "Server_" + serverid;
	}

	public int getPort() {
		return port;
	}

	public int getID() {
		return serverid;
	}

	public String getName() {
		return name;
	}

	public abstract String listen(); // Listen and return data.
	public abstract boolean setup(); // Initializes IO and returns success of connection.
	public abstract void write(String data); // Send data to client.
        public abstract void kill(); // Safely shut down the server.

}