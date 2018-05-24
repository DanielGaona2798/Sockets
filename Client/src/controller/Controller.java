package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import models.Client;
import views.DialogRegsitry;
import views.MainWindow;


public class Controller implements ActionListener{

	private static MainWindow mainwindow;
	private DialogRegsitry dialogRegsitry;
	private Client client;
	
	public Controller() {
		mainwindow = new MainWindow(this);
		dialogRegsitry = new DialogRegsitry(this);
		dialogRegsitry.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case ACCEPT:
			try {
				client = new Client("localhost", 2001, dialogRegsitry.getName());
				dialogRegsitry.setVisible(false);
				mainwindow.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case SEND:
			try {
				client.send("/message#" + client.getName() + "#" + mainwindow.getNumberwords());
				mainwindow.clean();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		}
	}

	public static void showMessage(String[] string) {
		mainwindow.setTextArea(string[1]+ ":"+ string [2]);
	}
	public static void main(String[] args) {
		new Controller();
	}
}
