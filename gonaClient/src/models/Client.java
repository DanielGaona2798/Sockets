package models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import views.MainWindow;

public class Client extends Connection implements ActionListener{

	private int id;
	private MainWindow mainWindow;
	
	public Client(String ip, int port) throws IOException{
		super(ip, port);
		mainWindow = new MainWindow(this);
		
	}

	@Override
	void executeTask() {
		try {
			String[] string = readResponse().split("#");
			if (string[0].equals("/number")) {
				mainWindow.setTextArea(string[1]);
			}else if (string[0].equals("/default")) {
				JOptionPane.showMessageDialog(null, string[1]);
			}else if (string[0].equals("/file")){
				saveFile();
				JOptionPane.showMessageDialog(null, "descarga finalizada");
			}
		} catch (IOException e) {
		}
	}



	public int getId() {
		return id;
	}
	
	public static void main(String[] args) {
		try {
			new Client("localhost", 2001);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveFile() {
		try{
			setInput(new DataInputStream(getSocket().getInputStream()));
			String nameFile = getInput().readUTF().toString();
			int tam = getInput().readInt();
			FileOutputStream fos = new FileOutputStream(new File("src/datos/" + nameFile));
			BufferedOutputStream out = new BufferedOutputStream(fos);
			BufferedInputStream in = new BufferedInputStream(getSocket().getInputStream());
			byte[] buffer = new byte[tam];
			for (int i = 0; i < buffer.length; i++) {
				buffer[i] = (byte) in.read();
			}
			out.write(buffer);
			out.flush();
		} catch (IOException e1) {
			System.out.println("Recibir "+ e1.toString());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "a") {
			try {
				send(mainWindow.getNumberwords());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


}