import java.io.*;
import java.util.*;
import java.text.*;

public class ClockServer {
	
	public static void main(String[] args) throws IOException {

		boolean isRunning = true;
		String data = "";

		ServerNode server = new ServerNode();

		if (server.setup()) {
			do {
				data = server.listen();
				if (data.equals("datetime@")) {
					System.out.println(data);
					DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
					Date date = new Date();
					System.out.println(dateFormat.format(date));
					server.write(dateFormat.format(date));
				} else if (data.equals("end@")) {
					isRunning = false;
				}
			} while(isRunning);

			server.kill();
		}
	}
}