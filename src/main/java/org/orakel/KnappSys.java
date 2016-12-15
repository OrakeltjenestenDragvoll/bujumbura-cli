package org.orakel;

import java.util.ArrayList;
import java.util.Date;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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
		prepareAndSendRecord("7");
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
			this.newMessage("Testing endpoint");
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
			settings.setURL(settingsView.getServer());
			settings.setApiKey(settingsView.getDbName());
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


			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(settings.getURL());
			post.setHeader("User-Agent", "Bujumbura 2.0");

			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("api_key", settings.getApiKey()));
			switch (reportCode) {
				case 4:
					urlParameters.add(new BasicNameValuePair("skranke", "1"));
					break;
				case 2:
					urlParameters.add(new BasicNameValuePair("telefon", "1"));
					break;
				case 1:
					urlParameters.add(new BasicNameValuePair("random", "1"));
					break;
				case 3:
					urlParameters.add(new BasicNameValuePair("random", "1"));
					urlParameters.add(new BasicNameValuePair("telefon", "1"));
					break;
				case 5:
					urlParameters.add(new BasicNameValuePair("random", "1"));
					urlParameters.add(new BasicNameValuePair("skranke", "1"));
					break;
				case 6:
					urlParameters.add(new BasicNameValuePair("skranke", "1"));
					urlParameters.add(new BasicNameValuePair("telefon", "1"));
					break;
				case 7:
					urlParameters.add(new BasicNameValuePair("random", "1"));
					urlParameters.add(new BasicNameValuePair("telefon", "1"));
					urlParameters.add(new BasicNameValuePair("skranke", "1"));
					break;
			}

			try {
				post.setEntity(new UrlEncodedFormEntity(urlParameters));
				client.execute(post);
			} catch (Exception e) {
				view.addText("Error: " + e.toString());
			}


		}
	}
}
