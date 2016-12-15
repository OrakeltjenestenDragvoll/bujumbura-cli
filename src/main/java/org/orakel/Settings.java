package org.orakel;

import java.io.*;

public class Settings {
	private String comPort;
	private String URL;
	private String apiKey;
	
	public Settings(KnappSys ksys){
		  FileInputStream fstream;
		try {
			fstream = new FileInputStream("settings.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			this.comPort = br.readLine();
			this.URL = br.readLine();
			this.apiKey = br.readLine();
			
			br.close();
			in.close();
			
		} catch (FileNotFoundException e) {
			ksys.newMessage("Error while reading settings file");
			ksys.newMessage("Set settings and restart program");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void save(){
		try{
			FileWriter fstream = new FileWriter("settings.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(comPort);
			out.newLine();
			out.write(URL);
			out.newLine();
			out.write(apiKey);
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	public String getComPort() {
		return comPort;
	}

	public void setComPort(String comPort) {
		this.comPort = comPort;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
