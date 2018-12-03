package grab;

/*
*	Handles reading and writing through the server object
*
*	Developed by, Andrew C.
**/

import java.net.*;
import java.io.*;

public class ServerIO {

	private InputStreamReader inputStream;
	private BufferedReader input;
	private PrintStream output;

	public ServerIO(Socket socket) throws IOException {
            inputStream = new InputStreamReader(socket.getInputStream());
            output = new PrintStream(socket.getOutputStream());
            input = new BufferedReader(inputStream);
	}
	
	/*
	 *  New listen method no longer requires a key!
	 **/
	public String listen() throws IOException {
            String data = "";
		while(data.equals("")) {
                    data += input.readLine();
		}
            return data;
	}

	// Imported write method from Version_1
	public void write(String data) {
		output.println(data);
	}
        
        // Closes data streams
        public void closeAll() throws IOException {
            inputStream.close();
            input.close();
            output.close();
        }

}