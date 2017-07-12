package pl.myproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pl.myproject.model.Customer;
import pl.myproject.util.ConnectionProvider;
import pl.myproject.util.NamedParameterStatement;

public class CustomerDAO {

	private final static String CREATE = "INSERT INTO client( name, surname, born, idcardnumber, street, housenumber, city, country, gender, telephone, file, created, edited) VALUES (:name, :surname, :born, :idcardnumber, :street, :housenumber, :city, :country, :gender, :telephone, :file, ?, ?);";
	private final static String READ = "SELECT * FROM client";
	private final static String READ_BY_ID = "SELECT * FROM client WHERE idcustomer = :idcustomer";
	private final static String UPDATE = "UPDATE client SET name = :name, surname = :surname, born = :born, idcardnumber= :idcardnumber, street = :street, housenumber = :housenumber, city = :city, country = :country, gender = :gender, telephone = :telephone, edited = ? WHERE  idcustomer = :idcustomer";
	private final static String DELETE = "DELETE FROM client WHERE idcustomer = :idcustomer";

	public boolean create(Customer customer) throws SQLException {
		boolean result = false;
		NamedParameterStatement named = createData(customer);
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}
		return result;
	}

	public List<Customer> read() throws SQLException {
		List<Customer> clientList = new ArrayList<Customer>();
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), READ);
		ResultSet res = named.executeQuery();
		while (res.next()) {
			clientList.add(readData(res));
		}
		return clientList;
	}

	public Customer showCustomer(int id) throws SQLException {
		Customer customer = new Customer();
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), READ_BY_ID);
		named.setInt("idcustomer", id);
		ResultSet res = named.executeQuery();
		while (res.next()) {
			customer = readData(res);
		}
		return customer;
	}

	public boolean update(Customer customer, int id) throws SQLException {
		boolean result = false;
		NamedParameterStatement named = updateData(customer, id);
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}
		return result;
	}

	public boolean delete(int id) throws SQLException {
		boolean result = false;
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), DELETE);
		named.setInt("idcustomer", id);
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}

		return result;
	}

	private NamedParameterStatement createData(Customer customer) throws SQLException {
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), CREATE);

		named.setString("name", customer.getName());
		named.setString("surname", customer.getSurname());
		named.setString("born", customer.getBorn());
		named.setString("idcardnumber", customer.getIdCardNumber());
		named.setString("street", customer.getStreet());
		named.setString("housenumber", customer.getHouseNumber());
		named.setString("city", customer.getCity());
		named.setString("country", customer.getCountry());
		named.setString("gender", customer.getGender());
		named.setString("telephone", customer.getTelephone());
		named.setString("file", customer.getFile());
		java.util.Date myDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		named.getStatement().setDate(12, sqlDate);
		named.getStatement().setDate(13, sqlDate);
		return named;
	}

	private Customer readData(ResultSet res) throws SQLException {
		Customer resultCustomer = new Customer();
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
		resultCustomer.setFile(res.getString("file"));
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		resultCustomer.setCreateDate(format.format(res.getDate("created")));
		resultCustomer.setEdited(format.format(res.getDate("edited")));
		return resultCustomer;
	}

	private NamedParameterStatement updateData(Customer customer, int id) throws SQLException {
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), UPDATE);

		named.setString("name", customer.getName());
		named.setString("surname", customer.getSurname());
		named.setString("born", customer.getBorn());
		named.setString("idcardnumber", customer.getIdCardNumber());
		named.setString("street", customer.getStreet());
		named.setString("housenumber", customer.getHouseNumber());
		named.setString("city", customer.getCity());
		named.setString("country", customer.getCountry());
		named.setString("gender", customer.getGender());
		named.setString("telephone", customer.getTelephone());
		java.util.Date myDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		named.getStatement().setDate(11, sqlDate);
		named.setInt("idcustomer", id);
		return named;
	}

}
