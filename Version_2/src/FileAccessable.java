/*
*	Interface for file type servers
*
*	Developed by, Andrew C.
**/

import java.util.*;

public interface FileAccessable {


	/**********************
	 * File system methods
	 **/
	boolean loadFile();
	void clear();
	void refresh();
	void writeFile(String data);
	ArrayList<String> getData();
	/**********************/

	/***********************
	 * Server object methods
	 **/
	boolean init();
	String listenServ();
	void writeServ(String data);
	/**********************/

}
