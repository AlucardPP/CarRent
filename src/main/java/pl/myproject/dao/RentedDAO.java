package pl.myproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import pl.myproject.model.Car;
import pl.myproject.model.Rented;
import pl.myproject.util.ConnectionProvider;
import pl.myproject.util.NamedParameterStatement;

public class RentedDAO {
	private final static String CREATE = "INSERT INTO rented (employee, client, cars, fromday, tillday, days, price, paid, isrented, carid) VALUES (:employee, :client, :cars, :fromday, :tillday, :days, :price, :paid, :isrented, :carid);";
	private final static String READ = "SELECT * FROM rented;";
	private final static String UPDATECAR = "UPDATE car SET available = :available WHERE idcar = :idcar;";
	private final static String UPDATERENTED = "UPDATE rented SET paid = :paid, isrented = :isrented WHERE idrented = :idrented;";
	private final static String DELETE = "DELETE FROM rented WHERE idrented = :idrented;";
	private final static String READCARTABLE = "SELECT * FROM car WHERE idcar = :idcar;";

	public boolean create(Rented rented, double pricePerHour, String from, String till, int id)
			throws SQLException, NumberFormatException, ParseException {
		NamedParameterStatement prepstmt = createData(rented, pricePerHour, from, till, id);
		boolean result = false;
		int rowAffected = prepstmt.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}
		return result;
	}

	public List<Rented> read() throws SQLException {
		List<Rented> rentedList = new ArrayList<Rented>();
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), READ);
		ResultSet res = named.executeQuery();
		while (res.next()) {
			rentedList.add(readData(res));
		}
		return rentedList;
	}

	public boolean udateRented(Rented rented, int id, int paid, int isRented) throws SQLException {
		NamedParameterStatement named = updateRented(rented, id, paid, isRented);
		boolean result = false;
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}
		return result;
	}

	public boolean updateCar(Car car, int id, String available) throws SQLException {
		NamedParameterStatement named = updateCarData(car, id, available);
		boolean result = false;
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}
		return result;

	}

	public boolean delete(int idRented) throws SQLException {
		boolean result = false;
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), DELETE);
		named.setInt("idrented", idRented);
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}
		return result;
	}

	public Car readCarTable(int id) throws SQLException {
		Car carDataList = null;
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), READCARTABLE);
		named.setInt("idcar", id);
		ResultSet res = named.executeQuery();
		while (res.next()) {
			carDataList = readCarData(res);
		}
		return carDataList;
	}

	private NamedParameterStatement createData(Rented rented, double pricePerHour, String from, String till, int id)
			throws SQLException, ParseException, NumberFormatException {
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), CREATE);
		named.setString("employee", rented.getEmployee());
		named.setString("client", rented.getClient());
		named.setString("cars", rented.getCars());
		named.setString("fromday", rented.getFromDate());
		named.setString("tillday", rented.getTillDate());
		named.setString("days", daysRented(from, till));
		named.setString("price", price(daysRented(from, till), pricePerHour));
		named.setInt("paid", rented.getPayed());
		named.setInt("isrented", rented.getRented());
		named.setInt("carid", id);

		return named;

	}

	private Rented readData(ResultSet res) throws SQLException {
		Rented resultRented = new Rented();
		resultRented.setIdRented(res.getInt("idrented"));
		resultRented.setEmployee(res.getString("employee"));
		resultRented.setClient(res.getString("client"));
		resultRented.setCars(res.getString("cars"));
		resultRented.setFromDate(res.getString("fromday"));
		resultRented.setTillDate(res.getString("tillday"));
		resultRented.setDays(res.getString("days"));
		resultRented.setPrice(res.getString("price"));
		resultRented.setPayed(res.getInt("paid"));
		resultRented.setRented(res.getInt("isrented"));
		resultRented.setCarId(res.getInt("carid"));
		return resultRented;

	}

	private NamedParameterStatement updateRented(Rented rented, int id, int paid, int isRented) throws SQLException {
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), UPDATERENTED);
		named.setInt("paid", paid);
		named.setInt("isrented", isRented);
		named.setInt("idrented", id);
		return named;
	}

	private NamedParameterStatement updateCarData(Car car, int id, String available) throws SQLException {
		NamedParameterStatement named = new NamedParameterStatement(ConnectionProvider.getConnection(), UPDATECAR);
		named.setString("available", available);
		named.setInt("idcar", id);
		return named;
	}

	private Car readCarData(ResultSet res) throws SQLException {

		Car resultCar = new Car();
		resultCar.setIdCar(res.getInt("idcar"));
		resultCar.setBrand(res.getString("brand"));
		resultCar.setModel(res.getString("model"));
		resultCar.setPlate(res.getString("plate"));
		resultCar.setProduced(res.getString("produced"));
		resultCar.setFirstRegistration(res.getString("firstregistration"));
		resultCar.setEngineSize(res.getString("engine"));
		resultCar.setValue(res.getString("value"));
		resultCar.setRentPerHour(res.getString("rentperhour"));
		resultCar.setDistance(res.getString("distance"));
		resultCar.setAvailable(res.getString("available"));

		return resultCar;
	}

	public String daysRented(String fromDate, String tillDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

		Date date1 = sdf.parse(fromDate);
		Date date2 = sdf.parse(tillDate);

		long howMany = date2.getTime() - date1.getTime() + 1;
		long date = TimeUnit.DAYS.convert(howMany, TimeUnit.MILLISECONDS);

		String days = String.valueOf(date);
		return days;
	}

	public String price(String days, double pricePerHour) {
		long howLong = Long.valueOf(days);
		double money = howLong * pricePerHour * 24;
		String moneyToPay = String.valueOf(money);
		return moneyToPay;
	}

}
