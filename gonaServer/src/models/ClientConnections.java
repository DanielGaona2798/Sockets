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
			switch (readResquest()) {
			case "1":
				send("/number#Hola");
				break;
			case "2":
				send("/number#Hola Como");
				break;
			case "3":
				send("/number#Hola Como Estas");
				break;
			case "4":
				send("/number#Hola Como estas? Todo");
				break;
			case "5":
				send("/number#Hola Como estas? Todo bien?");
				break;
			case "hora":
				getHour();
				break;
			case "file":
				sendFile();
				break;
			default:
				send("/default#caracteres exedidos");
				break;
			}

		} catch (IOException e) {
		}
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
