package views;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import models.Client;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField txtSend;
	private JTextArea jTextArea;
	
	public MainWindow(Client client) {
		setTitle("Sockets");
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		txtSend = new JTextField();
		txtSend.setBorder(new TitledBorder("Numero Palabras"));
		add(txtSend);
		
		jTextArea = new JTextArea();
		jTextArea.setBorder(new TitledBorder("Respuesta"));
		add(jTextArea);
		
		JButton btnAccept = new JButton("Aceptar");
		btnAccept.addActionListener(client);
		btnAccept.setActionCommand("a");
		add(btnAccept);
		
		setVisible(true);
	}
	
	public String getNumberwords(){
		return txtSend.getText();
	}
	
	public void setTextArea(String txt){
		jTextArea.setText(txt+"");
	}
}
