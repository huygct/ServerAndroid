package com.gct;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {

	private Socket clientSocket = null;
	
	
	public ClientThread(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		try {
			ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
			ObjectOutputStream  os = new ObjectOutputStream (clientSocket.getOutputStream());
			Robot robot = new Robot();
			
			Data data;
			while (true) {
				try {
					data = (Data) is.readObject();
					
					switch (data.getId()) {
					case "mouse":
						
						break;
					case "command":
						setCommand(data);
						break;
					case "click":
						
						break;
					case "twiceclick":
						
						break;
					case "taskrun" :
						
						
						break;
					case "endtask":
					
						break;
					default:
						break;
					}
					
					System.out.println(data.getId());
					System.out.println(data.getMessage());
					
//					robot.mouseMove(Integer.parseInt(data.getId()), Integer.parseInt(data.getMessage()));
					
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void setCommand(Data data) {
		String _command = data.getMessage();
		
		if(_command == "shutdown") {
			System.out.println(data.getMessage());
		}
		if(_command == "restart") {
			System.out.println(data.getMessage());
		}
	}

	private final String TASKLIST = "tasklist /v /fi \"STATUS eq running\"" ;
	private final String KILL = "taskkill /IM ";

	public boolean isProcessRunning(String serviceName) throws Exception {

		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {

			System.out.println(line);
			if (line.contains(serviceName)) {
				return true;
			}
		}

		return false;

	}

	public void killProcess(String serviceName) throws Exception {

		Runtime.getRuntime().exec(KILL + serviceName);

	}

}