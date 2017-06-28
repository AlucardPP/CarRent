package pl.myproject.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import pl.myproject.util.ConnectionProvider;
import pl.myproject.util.NamedParameterStatement;

public class Validate {
	private final static String READ_EMPLOYE = " SELECT * FROM user WHERE email = :email AND password = :password ; ";

	public static boolean checkUser(String email, String password) {
		boolean validate = false;
		try {
			NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(),
					READ_EMPLOYE);
			named.setString("email", email);
			named.setString("password", password);
			ResultSet res = named.executeQuery();
			validate = res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return validate;
	}

	public static String getMD5Hash(String s) throws NoSuchAlgorithmException {

		String result = s;
		if (s != null) {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(s.getBytes());
			BigInteger hash = new BigInteger(1, md.digest());
			result = hash.toString(16);
			while (result.length() < 32) {
				result = "0" + result;
			}
		}
		return result;
	}

}
