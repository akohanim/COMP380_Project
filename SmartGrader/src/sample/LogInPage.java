/*package sample;
//TODO: I believe this file is no longer necessary, please confirm.
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LogInPage {
	private JFrame frame;
	private JTextField FirstNameTextField;
	private JTextField LastNameTextField;
	private JTextField PasswordTextField;
	private JTextField EmailTextField;
	
	File Accounts;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInPage window = new LogInPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LogInPage() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1066, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel SignUpPanel = new JPanel();
		SignUpPanel.setBounds(0, 0, 1050, 436);
		frame.getContentPane().add(SignUpPanel);
		SignUpPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name: ");
		lblNewLabel.setBounds(122, 60, 58, 34);
		SignUpPanel.add(lblNewLabel);
		
		FirstNameTextField = new JTextField();
		FirstNameTextField.setBounds(198, 60, 358, 34);
		SignUpPanel.add(FirstNameTextField);
		FirstNameTextField.setColumns(10);
		
		LastNameTextField = new JTextField();
		LastNameTextField.setColumns(10);
		LastNameTextField.setBounds(198, 118, 358, 34);
		SignUpPanel.add(LastNameTextField);
		
		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setBounds(122, 118, 58, 34);
		SignUpPanel.add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(122, 176, 58, 34);
		SignUpPanel.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(122, 228, 58, 34);
		SignUpPanel.add(lblPassword);
		
		PasswordTextField = new JTextField();
		PasswordTextField.setColumns(10);
		PasswordTextField.setBounds(198, 228, 358, 34);
		SignUpPanel.add(PasswordTextField);
		
		EmailTextField = new JTextField();
		EmailTextField.setColumns(10);
		EmailTextField.setBounds(198, 170, 358, 34);
		SignUpPanel.add(EmailTextField);

		JButton SignUpButton = new JButton("SignUpButton");
		
		
		SignUpButton.setBounds(244, 297, 137, 34);
		SignUpPanel.add(SignUpButton);
		
		JLabel SignUpErrorLabel = new JLabel("This Email is Registered");
		SignUpErrorLabel.setForeground(Color.RED);
		SignUpErrorLabel.setBounds(202, 355, 354, 34);
		SignUpPanel.add(SignUpErrorLabel);
		
		JButton SignInButton = new JButton("Sign In");
		
		SignInButton.setBounds(228, 26, 117, 23);
		SignUpPanel.add(SignInButton);
		
		SignUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SignUpUser signUpUser = new SignUpUser(FirstNameTextField.getText(), LastNameTextField.getText(), EmailTextField.getText(), PasswordTextField.getText());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		SignInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SignInUser logInUser = new SignInUser(EmailTextField.getText(), PasswordTextField.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	
}
*/