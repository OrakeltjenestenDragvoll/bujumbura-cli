package org.orakel;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SettingsGui extends JFrame
{
	private static final long serialVersionUID = -2121378086913449527L;
	JPanel pane = new JPanel();
	JLabel comLabel = new JLabel("Com Port");
	JTextField comField = new JTextField(20);
	JLabel serverLabel = new JLabel("Server Address");
	JTextField serverField = new JTextField(20);
	JLabel dbNameLabel = new JLabel("Database name");
	JTextField dbNameField = new JTextField(20);
	JLabel dbUserLabel = new JLabel("Database username");
	JTextField dbUserField = new JTextField(20);
	JLabel dbPwdLabel = new JLabel("Database password");
	JTextField dbPwdField = new JTextField(20);
	
	JButton save = new JButton("Save");
	JButton cancel = new JButton("Cancel");
	
    SettingsGui()
	{
		super("Bujumbura: Settings");
		setBounds(100,100,600,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = this.getContentPane();
		con.add(pane);
		setVisible(false);
		
		
		pane.setLayout(new GridLayout(6,2,5,5));
		
		pane.add(comLabel);
		pane.add(comField);
		pane.add(serverLabel);
		pane.add(serverField);
		pane.add(dbNameLabel);
		pane.add(dbNameField);
		pane.add(dbUserLabel);
		pane.add(dbUserField);
		pane.add(dbPwdLabel);
		pane.add(dbPwdField);
		
			
		pane.add(save);
		pane.add(cancel);
		
	}
    public void setSettings(Settings settings){
		comField.setText(settings.getComPort());
		serverField.setText(settings.getSqlURL());
		dbNameField.setText(settings.getSqlDB());
		dbUserField.setText(settings.getSqlUser());
		dbPwdField.setText(settings.getSqlPassword());    	
    }
    public void addController(ActionListener controller)
    {
		save.addActionListener(controller);
		cancel.addActionListener(controller);
    	
    }
    public void setVisible()
    {
    	this.setVisible(true);
    }
    public void disableVisible()
    {
    	this.setVisible(false);
    }
    public String getCom()
    {
    	return comField.getText();
    }
    public String getServer()
    {
    	return serverField.getText();
    }
    public String getDbName()
    {
    	return dbNameField.getText();
    }
    public String getDbUser()
    {
    	return dbUserField.getText();
    }
    public String getDbPwd()
    {
    	return dbPwdField.getText();
    }

}
