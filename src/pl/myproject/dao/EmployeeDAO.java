package pl.myproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pl.myproject.model.Employee;
import pl.myproject.util.ConnectionProvider;
//Format!
public class EmployeeDAO {
	private final static String CREATE = "INSERT INTO employee ( name, surname, born, idcardnumber, street, housenumber, city, country, gender, telephone, education, salary, role, email, created, edited) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
	private final static String READ = "SELECT * FROM employee;";
	private final static String UPDATE = "UPDATE employee SET name = ?, surname = ?, born =?, idcardnumber=?, street = ?, housenumber = ?, city = ?, country = ?, gender = ?, telephone = ?, education = ?, salary = ?, role = ?, email = ?,  edited = ? WHERE idemployee = ?;";
	private final static String DELETE = "DELETE FROM employee WHERE idemployee = ?;";

	public boolean create(Employee employee) {
		Connection conn = null;
		PreparedStatement prepstmt = null;
		
		boolean result = false;
		try {
			//wnêtrze try do zewnêtrznej metody plizz 
			java.util.Date myDate = new java.util.Date();
			conn = ConnectionProvider.getConnection();
			prepstmt = conn.prepareStatement(CREATE);
			prepstmt.setString(1, employee.getName());
			prepstmt.setString(2, employee.getSurname());
			prepstmt.setString(3, employee.getBorn());
			prepstmt.setString(4, employee.getIdCardNumber());
			prepstmt.setString(5, employee.getStreet());
			prepstmt.setString(6, employee.getHouseNumber());
			prepstmt.setString(7, employee.getCity());
			prepstmt.setString(8, employee.getCountry());
			prepstmt.setString(9, employee.getGender());
			prepstmt.setString(10, employee.getTelephone());
			prepstmt.setString(11, employee.getEducation());
			prepstmt.setString(12, employee.getSalary());
			prepstmt.setString(13, employee.getRole());
			prepstmt.setString(14, employee.getEmail());
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			prepstmt.setDate(15, sqlDate);
			prepstmt.setDate(16, sqlDate);
			
			int rowsAffected = prepstmt.executeUpdate();
			if (rowsAffected > 0 ) {
				
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResource(conn, prepstmt, null);
		}
		return result;

	}

	public List<Employee> read() {
		Connection conn = null;
		PreparedStatement prepstmt = null;
		ResultSet res = null;
		Employee resultEmployee = null;
		List<Employee> emp = new ArrayList<Employee>();
		try {
			//wnêtrze try do zewnêtrznej metody plizz 
			conn = ConnectionProvider.getConnection();
			prepstmt = conn.prepareStatement(READ);
			res = prepstmt.executeQuery();
			while (res.next()) {
				resultEmployee = new Employee();
				resultEmployee.setIdEmployee(res.getInt("idemployee"));
				resultEmployee.setName(res.getString("name"));
				resultEmployee.setSurname(res.getString("surname"));
				resultEmployee.setBorn(res.getString("born"));
				resultEmployee.setIdCardNumber(res.getString("idcardnumber"));
				resultEmployee.setStreet(res.getString("street"));
				resultEmployee.setHouseNumber(res.getString("housenumber"));
				resultEmployee.setCity(res.getString("city"));
				resultEmployee.setCountry(res.getString("country"));
				resultEmployee.setGender(res.getString("gender"));
				resultEmployee.setTelephone(res.getString("telephone"));
				resultEmployee.setEducation(res.getString("education"));
				resultEmployee.setSalary(res.getString("salary"));
				resultEmployee.setRole(res.getString("role"));
				resultEmployee.setEmail(res.getString("email"));
				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				resultEmployee.setCreateDate(format.format(res.getDate("created")));
				resultEmployee.setEdited(format.format(res.getDate("edited")));
				emp.add(resultEmployee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResource(conn, prepstmt, res);
		}
		return emp;

	}

	public boolean update(Employee employee, int id) {
		Connection conn = null;
		PreparedStatement prepstmt = null;
		boolean result = false;
		try {
			//wnêtrze try do zewnêtrznej metody plizz 
			java.util.Date myDate = new java.util.Date();
			conn = ConnectionProvider.getConnection();
			prepstmt = conn.prepareStatement(UPDATE);
			prepstmt.setString(1, employee.getName());
			prepstmt.setString(2, employee.getSurname());
			prepstmt.setString(3, employee.getBorn());
			prepstmt.setString(4, employee.getIdCardNumber());
			prepstmt.setString(5, employee.getStreet());
			prepstmt.setString(6, employee.getHouseNumber());
			prepstmt.setString(7, employee.getCity());
			prepstmt.setString(8, employee.getCountry());
			prepstmt.setString(9, employee.getGender());
			prepstmt.setString(10, employee.getTelephone());
			prepstmt.setString(11, employee.getEducation());
			prepstmt.setString(12, employee.getSalary());
			prepstmt.setString(13, employee.getRole());
			prepstmt.setString(14, employee.getEmail());
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			prepstmt.setDate(15, sqlDate);
			prepstmt.setInt(16, id);
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
			//to siê powtarza 3 razy w 3 ró¿nych klasach - mo¿e jakaœ klasa nadrzêdna GenericDAO - która to uwspólni + tworzenie connection
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
