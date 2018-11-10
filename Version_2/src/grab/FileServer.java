package grab;

/*
*	File type server object
*
*	Developed by, Andrew C.
**/

import java.io.*;
import java.util.*;

public class FileServer extends ServerConnection implements FileAccessable {
	
	private File filepath;
	private Scanner in;
	private BufferedWriter out;

	ArrayList<String> data = new ArrayList<String>();

	public FileServer(int port, File filepath) {
		super(port);
		this.filepath = filepath;
	}

	/**********************
	 * Global
	 **/
	public void kill() {

		try {
			in.close();
			out.close();
		} catch (IOException ioe) {
			// Error when closing input and outputs
			System.out.println("<6>");
		}
	}

	/**********************
	 * File system methods
	 **/
	/*
	 * Loads file into data ArrayList
	 **/
	public boolean loadFile() {
		if (!in.hasNext()) {
			return false;
		} else {
			while(in.hasNext()) {
				data.add(in.next());
			}
			return true;
		}
	}

	/*
	 * Clears data and prepares it for a refresh
	 **/
	public void clear() {
		data.clear();
	}

	/*
	 * Clears and reloads data
	 **/
	public void refresh() {

		data.clear();

		try {
			in = new Scanner(filepath);
		} catch (FileNotFoundException fnfe) {
			//<9> Given filepath does not exist
			System.out.println("<9>");
		}

		loadFile();
	}

	/*
	 * Returns available data in ArrayList
	 **/
	public ArrayList<String> getData() {
		return data;
	}

	public void writeFile(String data) {

		try {
			out.append(data);
			out.flush();
		} catch (IOException ioe) {
			//<8> Error when appending data to file
			System.out.println("<8>");
		}
	}

	public String toString() {
		return toString();
	}

	/***********************
	 * Server object methods
	 **/
	/*
	 * Initializes the server connection and file server input/output
	 **/
	public boolean init() {
		if (setup()) {
			try {
				refresh();
				out = new BufferedWriter(new FileWriter(filepath.getAbsoluteFile(), true));
			} catch (FileNotFoundException fnfe) {
				//<4> Given filepath does not exist
				System.out.println("<4>");
				return false;
			} catch (IOException ioe) {
				//<7> BufferedWriter failed to enstantiate
				System.out.println("<7>");
			}
			return true;
		} else {
			//<5> Failed to setup server connection for this FileServer
			System.out.println("<5>");
			return false;
		}
	}

	/*
	 * Accesses servers listening method
	 **/
	public String listenServ() {
		return listen();
	}

	/*
	 * Accesses servers writing method
	 **/
	public void writeServ(String data) {
		super.write(data);
	}
}

