import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

	public static void main(String argv[]) throws Exception {
		String sentence;
		String foundWords;

		// set up input reader for user input
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		// create TCP socket and connect to server
		Socket clientSocket = new Socket("localhost", 6789);
		System.out.println("Client successfully established TCP connection.\n"
				+ "Client(local) end of the connection uses port " 
				+ clientSocket.getLocalPort() 
				+ " and server(remote) end of the connection uses port "
				+ clientSocket.getPort());

		// set up input and output streams for the socket
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		// read user input
		sentence = inFromUser.readLine();
		while (sentence.toLowerCase().compareTo("exit") != 0) {
			// send the user input to the server
			outToServer.writeBytes(sentence + '\n');

			// read the server's response
			foundWords = inFromServer.readLine();

			// print the server's response
			System.out.println(foundWords);
			sentence = inFromUser.readLine();
		}

		clientSocket.close();
	}
}
