package org.orakel;

import java.sql.*;

/**
 * Klasse for � hente relevant info fra en MySQL-database.
 */
public class MySQLConnection {
	private Connection conn = null;
	private Statement stmt = null;
	private String url;
	private String dbName;
	private String user;
	private String pwd;
	private KnappSys kSys;

	/**
	 * Oppretter MySQL-connection.
	 * 
	 */
	public MySQLConnection(KnappSys sys) {
		this.kSys = sys;
		Settings settings = kSys.getSettings();
		url = settings.getSqlURL();
		dbName = settings.getSqlDB();
		user = settings.getSqlUser();
		pwd = settings.getSqlPassword();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			kSys.newMessage("Feil, avslutter.");
			System.exit(0);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			kSys.newMessage("Feil, avslutter.");
			System.exit(0);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			kSys.newMessage("Feil, avslutter.");
			System.exit(0);
		}
		try {
			DriverManager.setLoginTimeout(10);
			conn = DriverManager.getConnection(url+dbName,user,pwd);
			//				conn = DriverManager.getConnection(url + user+pwd);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			kSys.newMessage("Kunne ikke koble til MySQL-server. ");
			System.out.println(e);
			System.out.println(url+dbName + " | " + user + " | " + pwd);
		}
	}


	/**
	 * Adds a record to the history
	 * @param reportCode  	record type
	 * @param date			date of record
	 * @param time			time of record
	 * @author Kristian Laskemoen 
	 */
	public void insertRecord(int reportCode,String date,String time){
		try{
			stmt.executeUpdate( "INSERT INTO `knapp`.`history` (`id`, `type`, `day`,`hour`) " +
					"VALUES (NULL, '"+reportCode+"', '"+date+"', '"+time+"');" );
		}catch (SQLException e) {
			kSys.newMessage("Feil med registering av verdi.");
			System.exit(0);
		}
	}
	/**
	 * Close the SQL connection
	 */
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
