package com.gct;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Create server to client connect and transfer
 * 
 * @author thuynghi
 *
 */
public class Server {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// The server socket
		ServerSocket serverSocket = null;
		// The default port number
		int portNumber = 2222;
		
//		List<Thread> listThread = new ArrayList<Thread>();

		/**
		 * open a server socket on the portNumber (default 2222). Note the we
		 * can not choose a port less than 1023 if we are not privileged users
		 * (root)
		 */
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}

		/**
		 * Create a client socket for each connection and pass it to a new
		 * client thread
		 */
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();

				Thread clientThread = new Thread(new ClientThread(clientSocket));
				
				clientThread.start();

			} catch (IOException e) {
				System.out.println(e);
			}

		}
	}
}
