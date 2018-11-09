import java.io.*;
import java.util.*;

public class Server {

	public static void main(String[] args) {

		FileServer fileserver = new FileServer(5000, new File("TestDataFile.text"));

		System.out.println("Initializing server, and waiting for connection...");

		if (fileserver.init()) {
			System.out.println("Success!");
			System.out.println(fileserver.listenServ());
			fileserver.writeServ("Thanks for the data");
		} else {
			System.out.println("Failed...");
		}
	}
}