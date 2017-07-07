package pl.myproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import pl.myproject.model.Employee;
import pl.myproject.model.User;
import pl.myproject.util.ConnectionProvider;
import pl.myproject.util.NamedParameterStatement;

public class UserDAO {
	private final static String GET_EMPLOYEE = "SELECT * FROM employee WHERE email = :email;";
	private final static String CREATE = "INSERT INTO user(user_id, email, password, user_role, username) VALUES (:user_id, :email, :password, :user_role. :username);";
	private final static String READ_USER_BY_EMAIL = "SELECT * FROM user WHERE email = :email;";
	private final static String READ_USER_BY_ID = "SELECT * FROM user WHERE user_id = :user_id;";
	private final static String UPDATE_PASSWORD = "UPDATE user SET password = :password WHERE user_id = :user_id ";
	private final static String DELETE = "DELETE FROM user WHERE user_id = :user_id;";
	private final static String UPDATE_USER = "UPDATE user SET email = :email, username = :username, user_role = :user_role WHERE user_id = :user_id";

	public boolean create(String email) throws SQLException {
		boolean result = false;
		NamedParameterStatement named = createData(CREATE, email);
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0)
			result = true;

		return result;
	}

	public User readUserByEmail(String email) throws SQLException {
		User user = new User();
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(),
				READ_USER_BY_EMAIL);
		named.setString("email", email);
		ResultSet res = named.executeQuery();
		while (res.next())
			readUser(user, res);
		return user;
	}

	public User readUserById(int id) throws SQLException {
		User user = new User();
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(),
				READ_USER_BY_ID);
		named.setInt("user_id", id);
		ResultSet res = named.executeQuery();
		while (res.next())
			readUser(user, res);
		return user;
	}

	public boolean update(int id, String username, String email, String role) throws SQLException {
		boolean result = false;
		NamedParameterStatement named = updateData(username, id, email, role);
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0)
			result = true;

		return result;

	}

	public boolean delete(int id) throws SQLException {
		boolean result = false;
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), DELETE);
		named.setInt("user_id", id);
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0)
			result = true;
		return result;

	}

	public boolean updatePassword(int id, String password) throws SQLException {
		boolean result = false;
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(),
				UPDATE_PASSWORD);
		named.setString("password", password);
		named.setInt("user_id", id);
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0)
			result = true;

		return result;
	}

	private NamedParameterStatement updateData(String username, int id, String email, String role) throws SQLException {
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), UPDATE_USER);
		named.setString("email", email);
		named.setString("username", username);
		named.setString("user_role", role);
		named.setInt("user_id", id);
		return named;
	}

	public Employee getEmployee(String email) throws SQLException {
		Employee employee = null;
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), GET_EMPLOYEE);
		named.setString("email", email);
		ResultSet res = named.executeQuery();
		while (res.next()) {
			employee = readData(res);
		}
		return employee;

	}

	private NamedParameterStatement createData(String querry, String email) throws SQLException {
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), querry);
		Employee employee = getEmployee(email);
		named.setInt("user_id", employee.getIdEmployee());
		named.setString("email", email);
		named.setString("password", "test");
		named.setString("user_role", employee.getRole());
		String name = employee.getName();
		String surname = employee.getSurname();
		String username = name + " " + surname;
		named.setString("username", username);
		return named;
	}

	private Employee readData(ResultSet res) throws SQLException {
		Employee employee = new Employee();
		employee.setIdEmployee(res.getInt("idemployee"));
		employee.setName(res.getString("name"));
		employee.setSurname(res.getString("surname"));
		employee.setBorn(res.getString("born"));
		employee.setIdCardNumber(res.getString("idcardnumber"));
		employee.setStreet(res.getString("street"));
		employee.setHouseNumber(res.getString("housenumber"));
		employee.setCity(res.getString("city"));
		employee.setCountry(res.getString("country"));
		employee.setGender(res.getString("gender"));
		employee.setTelephone(res.getString("telephone"));
		employee.setEducation(res.getString("education"));
		employee.setSalary(res.getString("salary"));
		employee.setRole(res.getString("role"));
		employee.setEmail(res.getString("email"));
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		employee.setCreateDate(format.format(res.getDate("created")));
		employee.setEdited(format.format(res.getDate("edited")));
		return employee;
	}

	private User readUser(User user, ResultSet res) throws SQLException {

		user.setUser_id(res.getInt("user_id"));
		user.setEmail(res.getString("email"));
		user.setPassword(res.getString("password"));
		user.setRole(res.getString("user_role"));
		user.setUsername(res.getString("username"));
		return user;

	}

}
