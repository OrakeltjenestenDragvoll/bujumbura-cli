package org.orakel;

import java.io.*;

public class Settings {
	private String comPort;
	private String sqlURL;
	private String sqlDB;
	private String sqlUser;
	private String sqlPassword;
	
	public Settings(KnappSys ksys){
		  FileInputStream fstream;
		try {
			fstream = new FileInputStream("settings.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			this.comPort = br.readLine();
			this.sqlURL = br.readLine();
			this.sqlDB = br.readLine();
			this.sqlUser = br.readLine();
			this.sqlPassword = br.readLine();
			
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
			out.write(sqlURL);
			out.newLine();
			out.write(sqlDB);
			out.newLine();
			out.write(sqlUser);
			out.newLine();
			out.write(sqlPassword);
			out.close();
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

	public String getSqlURL() {
		return sqlURL;
	}

	public void setSqlURL(String sqlURL) {
		this.sqlURL = sqlURL;
	}

	public String getSqlDB() {
		return sqlDB;
	}

	public void setSqlDB(String sqlDB) {
		this.sqlDB = sqlDB;
	}

	public String getSqlUser() {
		return sqlUser;
	}

	public void setSqlUser(String sqlUser) {
		this.sqlUser = sqlUser;
	}

	public String getSqlPassword() {
		return sqlPassword;
	}

	public void setSqlPassword(String sqlPassword) {
		this.sqlPassword = sqlPassword;
	}

}
