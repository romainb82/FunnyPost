package entite.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.Connection;

import entite.IBaseDonnee;
import entite.IPost;

public class BaseDonnee implements IBaseDonnee {

	@Override
	public java.sql.Connection initConnection() throws Exception {
		String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/funnypost";
		String username = "root";
		String pwd = "";

		Class.forName(driver);

		java.sql.Connection conn = DriverManager.getConnection(url, username, pwd);

		if(conn != null) {
			System.out.println("CONNECTE A LA BASE DE DONNEE");
		}
		return conn;
	}

}
