package ursign.ursign_client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegisterNewAccount = new JLabel("Register New Account");
		lblRegisterNewAccount.setBounds(181, 11, 137, 14);
		frame.getContentPane().add(lblRegisterNewAccount);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(35, 60, 72, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(35, 118, 72, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblConfirePassword = new JLabel("Confirm Password");
		lblConfirePassword.setBounds(35, 174, 137, 14);
		frame.getContentPane().add(lblConfirePassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(35, 74, 283, 33);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(35, 130, 283, 34);
		frame.getContentPane().add(txtPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBounds(35, 188, 283, 33);
		frame.getContentPane().add(txtConfirmPassword);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Get value of all text fields 
				String username = txtUsername.getText();
				@SuppressWarnings("deprecation")
				String password = txtPassword.getText();
				@SuppressWarnings("deprecation")
				String confirmPassword = txtConfirmPassword.getText();
				
				//System.out.println("username: "+username);
				
				//Launch database interfacing class
				//dbClass db = new dbClass();
				
				//Check if any fields are left blank
				if(username.equals("")|| password.equals("")||confirmPassword.equals("")){
					//If any field left blank, inform user to fill in all fields
					JOptionPane.showMessageDialog(null, "Please fillout all fields", "Field Empty", JOptionPane.ERROR_MESSAGE);
				}
				//Check if both password fields match
				else if (!(confirmPassword.equals(password))){
					//If they don't match, inform user to re-enter password details
					txtPassword.setText(null);
					txtConfirmPassword.setText(null);
					JOptionPane.showMessageDialog(null, "Passwords do not match", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
				}
				//Check if username already exists in database
				else if (db.checkExsistingUsername(username)== true){
					//If username exists, clear username field and ask user to enter a new username
					txtUsername.setText(null);
					JOptionPane.showMessageDialog(null, "Username already exists, enter a new username", "Username Invaild", JOptionPane.ERROR_MESSAGE);
				}
				//If all of above are passes, attempt to add new user to database
				else{
					//Check if account was created successfully
					if (db.addNewAccount(username, password)== true){
						//If account is created successfully, inform user
						JOptionPane.showMessageDialog(null, "Account Created!");
					}
					else{
						//If account wasn't created inform user
						JOptionPane.showMessageDialog(null, "Account not created, unknown error");
					}
				}
				
				
			}
		});
		btnConfirm.setBounds(364, 136, 89, 23);
		frame.getContentPane().add(btnConfirm);
	}

}