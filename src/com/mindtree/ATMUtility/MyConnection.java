package com.mindtree.ATMUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {
	private static Connection connection;

	public static Connection getMyConnection() throws SQLException, FileNotFoundException, IOException {
		if (connection == null) {
			Properties dataProperties = new Properties();
			dataProperties.load(new FileInputStream(
					new File("F:\\javaeeworkspace\\ATM\\src\\com\\mindtree\\ATMUtility\\sourdeFile.Properties")));
			String url = dataProperties.getProperty("url");
			String username = dataProperties.getProperty("username");
			String password = dataProperties.getProperty("password");
			connection = DriverManager.getConnection(url, username, password);
			return connection;
		} else {
			return connection;
		}
	}

}
