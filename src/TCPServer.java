import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class TCPServer {
	public static void main(String argv[]) throws Exception {
		String clientSentence;
		String results;

		// create the WordList from the words file
		WordList wordList = new WordList("data/words.txt");

		// create server socket
		ServerSocket welcomeSocket = new ServerSocket(6789);

		// while the socket is open, accept connections and process them
		while (true) {

			System.out.println("ServerSocket created, blocking on accept and waiting for incoming requests!");

			// accept incoming connection
			Socket connectionSocket = welcomeSocket.accept();
			
			System.out.println("ServerSocket accepted incoming request from: " + connectionSocket.getPort());
			
			// set up input and output streams
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			System.out.println("Accepted TCP connection from" 
					+ connectionSocket.getInetAddress() 
					+ ":" + connectionSocket.getPort());
			
			// process client requests
			try {
				while (true) {
					// read a line from the client
					clientSentence = inFromClient.readLine();

					// find words with the given prefix
					List<String> foundWords = wordList.findWordsWithPrefix(clientSentence);

					// join the results into a single string
					results = String.join(", ", foundWords);

					// send the results back to the client
					outToClient.writeBytes("The following words start with " + clientSentence + ": " + results + '\n');
				}
			} catch (Exception e) {
				// TODO: handle exception, if client closed connection, print:
				System.out.println("Client closed connection.");
			}
		}
	}
}
