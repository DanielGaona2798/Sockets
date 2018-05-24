package models;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;


public class ClientConnections extends Connection{


	public ClientConnections(Socket newConnection) throws IOException {
		super(newConnection);
	}

	@Override
	void executeTask() {
		try {
			String [] string = readResquest().split("#");
			switch (string[0]) {
			case "/message":
				sendMessage(string);
				
				break;
			}

		} catch (IOException e) {
		}
	}
	
	private void sendMessage(String[] string) {
		Server.sendMessageALL(string);
	}

	private void getHour() throws IOException{
		Calendar calendar = Calendar.getInstance();
		int hora =calendar.get(Calendar.HOUR_OF_DAY);
		int minutos = calendar.get(Calendar.MINUTE);
		int segundos = calendar.get(Calendar.SECOND);
		send("/number#"+hora +":"+ minutos +":" + segundos);
	}

	private void sendFile(){
		sendFile(new File("src/datos/1.jpg"));
	}
}
