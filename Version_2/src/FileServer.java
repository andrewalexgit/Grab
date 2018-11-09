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

	ArrayList<String> data = new ArrayList<String>();

	public FileServer(int port, File filepath) {
		super(port);
		this.filepath = filepath;
	}

	/**********************
	 * File system methods
	 **/
	/*
	 * Loads file into data ArrayList
	 **/
	public boolean loadFile() {
		if (!in.hasNext()) {
			//<3> No data available to load from file
			System.out.println("<3>");
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
		loadFile();
	}

	/*
	 * Returns available data in ArrayList
	 **/
	public ArrayList getData() {
		return data;
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
				in = new Scanner(filepath);
			} catch (FileNotFoundException fnfe) {
				//<4> Given filepath does not exist
				System.out.println("<4>");
				return false;
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
