package pl.myproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pl.myproject.model.Employee;

import pl.myproject.util.ConnectionProvider;
import pl.myproject.util.NamedParameterStatement;

public class EmployeeDAO {
	private final static String CREATE = "INSERT INTO employee ( name, surname, born, idcardnumber, street, housenumber, city, country, gender, telephone, education, salary, role, email, created, edited) VALUES ( :name, :surname, :born, :idcardnumber, :street, :housenumber, :city, :country, :gender, :telephone, :education, :salary, :role, :email, ?, ?); ";
	private final static String READ = "SELECT * FROM employee;";
	private final static String READ_BY_ID = "SELECT * FROM employee WHERE idemployee = :idemployee;";
	private final static String UPDATE = "UPDATE employee SET name = :name, surname = :surname, born = :born, idcardnumber= :idcardnumber, street = :street, housenumber = :housenumber, city = :city, country = :country, gender = :gender, telephone = :telephone, education = :education, salary = :salary, role = :role, email = :email,  edited = ? WHERE idemployee = :idemployee;";
	private final static String DELETE = "DELETE FROM employee WHERE idemployee = :idemployee;";

	public boolean create(Employee employee) throws SQLException {
		boolean result = false;
		NamedParameterStatement named = createData(employee);
		int rowsAffected = named.executeUpdate();
		if (rowsAffected > 0) {
			result = true;
		}
		return result;
	}

	public List<Employee> read() throws SQLException {
		List<Employee> emp = new ArrayList<Employee>();
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), READ);
		ResultSet res = named.executeQuery();
		while (res.next()) {
			emp.add(readEmployee(res));
		}
		return emp;

	}

	public Employee readById(int id) throws SQLException {
		Employee employee = new Employee();
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), READ_BY_ID);
		named.setInt("idemployee", id);
		ResultSet res = named.executeQuery();
		while (res.next()) {
			employee = readEmployee(res);
		}
		return employee;
	}

	public boolean update(Employee employee, int id) throws SQLException {
		boolean result = false;
		NamedParameterStatement named = updateData(employee, id);
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}

		return result;
	}

	public boolean delete(int id) throws SQLException {
		boolean result = false;
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), DELETE);
		named.setInt("idemployee", id);
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}
		return result;
	}

	private NamedParameterStatement createData(Employee employee) throws SQLException {
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), CREATE);
		named.setString("name", employee.getName());
		named.setString("surname", employee.getSurname());
		named.setString("born", employee.getBorn());
		named.setString("idcardnumber", employee.getIdCardNumber());
		named.setString("street", employee.getStreet());
		named.setString("housenumber", employee.getHouseNumber());
		named.setString("city", employee.getCity());
		named.setString("country", employee.getCountry());
		named.setString("gender", employee.getGender());
		named.setString("telephone", employee.getTelephone());
		named.setString("education", employee.getEducation());
		named.setString("salary", employee.getSalary());
		named.setString("role", employee.getRole());
		named.setString("email", employee.getEmail());
		java.util.Date myDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		named.getStatement().setDate(15, sqlDate);
		named.getStatement().setDate(16, sqlDate);
		return named;

	}

	private Employee readEmployee(ResultSet res) throws SQLException {
		Employee resultEmployee = new Employee();
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
		return resultEmployee;
	}

	private NamedParameterStatement updateData(Employee employee, int id) throws SQLException {
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), UPDATE);
		named.setString("name", employee.getName());
		named.setString("surname", employee.getSurname());
		named.setString("born", employee.getBorn());
		named.setString("idcardnumber", employee.getIdCardNumber());
		named.setString("street", employee.getStreet());
		named.setString("housenumber", employee.getHouseNumber());
		named.setString("city", employee.getCity());
		named.setString("country", employee.getCountry());
		named.setString("gender", employee.getGender());
		named.setString("telephone", employee.getTelephone());
		named.setString("education", employee.getEducation());
		named.setString("salary", employee.getSalary());
		named.setString("role", employee.getRole());
		named.setString("email", employee.getEmail());
		java.util.Date myDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		named.getStatement().setDate(15, sqlDate);
		named.setInt("idemployee", id);
		return named;
	}

}
