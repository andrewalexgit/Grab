import java.io.*;
import java.util.*;

public class ExampleFileServer {

	public static void main(String[] args) throws Exception {

		ArrayList<String> data;
		FileServer fileserver = new FileServer(5000, new File("TestDataFile.text"));

		System.out.println("Initializing server, and waiting for connection...");

		if (fileserver.init()) {
			System.out.println("Success!");
			fileserver.loadFile();

			data = fileserver.getData();

			for (String s: data) {
				System.out.println(s);
			}

			String str = fileserver.listenServ();
			System.out.println("Writing "+ str +" to file!");
			fileserver.writeFile(str);
			fileserver.refresh();

			data = fileserver.getData();

			for (String s: data) {
				System.out.println(s);
			}

			System.out.println(fileserver.listenServ());
			fileserver.writeServ("Thanks for the data!");
		} else {
			System.out.println("Failed...");
		}
	}
}
