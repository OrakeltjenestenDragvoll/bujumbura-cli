package org.orakel;

import java.util.Date;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class KnappSys implements ActionListener{
	private Settings settings;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    private Gui view;
    private SettingsGui settingsView;
	
	public KnappSys() {
		view = new Gui();
		view.addController(this);
		setSettings(new Settings(this));
		settingsView = new SettingsGui();
		settingsView.addController(this);
		System.out.println(settings.getComPort());
		Serial main = new Serial();
		main.initialize(this);
				
	}	

	public static void main(String[] args) throws Exception {
		new KnappSys();
	}

	private void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Settings getSettings() {
		return settings;
	}

	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getActionCommand();
		if(source == "Test SQL connection")
		{
			this.newMessage("Testing SQL connection");
			MySQLConnection mysql = new MySQLConnection(this);
			mysql.close();
			this.newMessage("Testing complete");
			
		}
		if(source == "Settings")
		{
			this.newMessage("settings");
			settingsView.setSettings(settings);
			settingsView.setVisible();
		}
		if(source == "Save")
		{
			settingsView.disableVisible();
			settings.setComPort(settingsView.getCom());
			settings.setSqlURL(settingsView.getServer());
			settings.setSqlDB(settingsView.getDbName());
			settings.setSqlUser(settingsView.getDbUser());
			settings.setSqlPassword(settingsView.getDbPwd());
			settings.save();
			this.newMessage("Settings saved");
		}
		if(source == "Cancel")
		{
			this.newMessage("Settings discarded");
			settingsView.disableVisible();
		}
	}
	public void newMessage(String s)
	{
		view.addText(s);
	}

	public void prepareAndSendRecord(String serialInput){
		int reportCode = 0;
		
		serialInput = serialInput.replaceAll("[^\\d]", "");
		if (serialInput.length()>0){
			reportCode = Integer.parseInt(serialInput);
			Date date = new Date();
			view.addText(reportCode +" "+ dateFormat.format(date) + " " + hourFormat.format(date) );
			MySQLConnection mysql = new MySQLConnection(this);
			mysql.insertRecord(reportCode, dateFormat.format(date), hourFormat.format(date));
			mysql.close();
		}
	}
}
