package pl.myproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pl.myproject.model.Customer;
import pl.myproject.util.ConnectionProvider;

public class CustomerDAO {

	private final static String CREATE = "INSERT INTO client( name, surname, born, idcardnumber, street, housenumber, city, country, gender, telephone, created, edited) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private final static String READ = "SELECT * FROM client";
	private final static String UPDATE = "UPDATE client SET name = ?, surname = ?, born =?, idcardnumber=?, street = ?, housenumber = ?, city = ?, country = ?, gender = ?, telephone = ?, edited = ? WHERE  idcustomer = ?";
	private final static String DELETE = "DELETE FROM client WHERE idcustomer = ?";

	public boolean create(Customer customer) {
		Connection conn = null;
		PreparedStatement prepstmt = null;
		ResultSet keys = null;
		boolean result = false;
		try {
			//wnêtrze try do zewnêtrznej metody plizz 
			//dlaczego takie dziwne ?
			java.util.Date myDate = new java.util.Date();

			conn = ConnectionProvider.getConnection();
			prepstmt = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);

			prepstmt.setString(1, customer.getName());
			prepstmt.setString(2, customer.getSurname());
			prepstmt.setString(3, customer.getBorn());
			prepstmt.setString(4, customer.getIdCardNumber());
			prepstmt.setString(5, customer.getStreet());
			prepstmt.setString(6, customer.getHouseNumber());
			prepstmt.setString(7, customer.getCity());
			prepstmt.setString(8, customer.getCountry());
			prepstmt.setString(9, customer.getGender());
			prepstmt.setString(10, customer.getTelephone());
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			prepstmt.setDate(11, sqlDate);
			prepstmt.setDate(12, sqlDate);
			int rowAffected = prepstmt.executeUpdate();
			keys = prepstmt.getGeneratedKeys();

			if (rowAffected > 0 && keys.next()) {
				customer.setIdCustomer(keys.getInt(1));
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResource(conn, prepstmt, keys);
		}
		return result;
	}

	public List<Customer> read() {
		Connection conn = null;
		PreparedStatement prepstmt = null;
		ResultSet res = null;
		Customer resultCustomer = null;
		List<Customer> clientList = new ArrayList<Customer>();

		try {
			//wnêtrze try do zewnêtrznej metody plizz 
			conn = ConnectionProvider.getConnection();
			prepstmt = conn.prepareStatement(READ);
			res = prepstmt.executeQuery();

			while (res.next()) {
				resultCustomer = new Customer();
				resultCustomer.setIdCustomer(res.getInt("idcustomer"));
				resultCustomer.setName(res.getString("name"));
				resultCustomer.setSurname(res.getString("surname"));
				resultCustomer.setBorn(res.getString("born"));
				resultCustomer.setIdCardNumber(res.getString("idcardnumber"));
				resultCustomer.setStreet(res.getString("street"));
				resultCustomer.setHouseNumber(res.getString("housenumber"));
				resultCustomer.setCity(res.getString("city"));
				resultCustomer.setCountry(res.getString("country"));
				resultCustomer.setGender(res.getString("gender"));
				resultCustomer.setTelephone(res.getString("telephone"));
				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				resultCustomer.setCreateDate(format.format(res.getDate("created")));
				resultCustomer.setEdited(format.format(res.getDate("edited")));
				clientList.add(resultCustomer);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResource(conn, prepstmt, res);
		}
		return clientList;
	}

	public boolean update(Customer customer, int id) {
		Connection conn = null;
		PreparedStatement prepstmt = null;
		boolean result = false;
		try {
			//wnêtrze try do zewnêtrznej metody plizz 
			java.util.Date myDate = new java.util.Date();
			conn = ConnectionProvider.getConnection();
			prepstmt = conn.prepareStatement(UPDATE);
			prepstmt.setString(1, customer.getName());
			prepstmt.setString(2, customer.getSurname());
			prepstmt.setString(3, customer.getBorn());
			prepstmt.setString(4, customer.getIdCardNumber());
			prepstmt.setString(5, customer.getStreet());
			prepstmt.setString(6, customer.getHouseNumber());
			prepstmt.setString(7, customer.getCity());
			prepstmt.setString(8, customer.getCountry());
			prepstmt.setString(9, customer.getGender());
			prepstmt.setString(10, customer.getTelephone());
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			prepstmt.setDate(11, sqlDate);
			prepstmt.setInt(12, id);
			int rowAffected = prepstmt.executeUpdate();
			if (rowAffected > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResource(conn, prepstmt, null);
		}
		return result;
	}

	public boolean delete(int id) {
		Connection conn = null;
		PreparedStatement prepstmt = null;
		boolean result = false;
		try {
			//wnêtrze try do zewnêtrznej metody plizz 
			conn = ConnectionProvider.getConnection();
			prepstmt = conn.prepareStatement(DELETE);
			prepstmt.setInt(1, id);

			int rowAffected = prepstmt.executeUpdate();
			if (rowAffected > 0) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResource(conn, prepstmt, null);
		}
		return result;
	}

	private void releaseResource(Connection conn, PreparedStatement prepstmt, ResultSet res) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			if (prepstmt != null && !prepstmt.isClosed()) {
				prepstmt.close();
			}
			if (res != null && !res.isClosed()) {
				res.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
