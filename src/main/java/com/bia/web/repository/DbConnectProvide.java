package com.bia.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectProvide {
	
		static {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}catch(ClassNotFoundException exc) {
				exc.printStackTrace();
			}
		}
		static  Connection getConnection() throws SQLException {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping?useSSL=false","root","root");
		}
	}

