package pl.myproject.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.myproject.model.Car;

import pl.myproject.util.NamedParameterStatement;

//format kodu! A¿ chce siê p³akaæ 
public class CarDAO {
	private final static String CREATE = "INSERT INTO car( brand, model, plate, produced, firstregistration, engine, value, rentperhour, distance, available ) VALUES (:brand, :model, :plate, :produced, :firstregistration, :engine, :value, :rentperhour, :distance, :available);";
	private final static String READ = "SELECT * FROM car;";
	private final static String UPDATE = "UPDATE car SET brand = :brand, model = :model, plate = :plate, produced = :produced, firstregistration = :firstregistration, engine = :engine, value = :value, rentperhour = :rentperhour, distance = :distance, available = :available WHERE idcar = :idcar;";
	private final static String DELETE = "DELETE FROM car WHERE idcar = :idcar;";

	public boolean create(Car car, Connection conn) throws SQLException {
		NamedParameterStatement named = createData(CREATE, car, conn);
		boolean result = false;
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}
		return result;
	}

	public List<Car> read(Connection conn) throws SQLException {
		List<Car> carList = new ArrayList<Car>();
		NamedParameterStatement named = new NamedParameterStatement(conn, READ);
		ResultSet res = named.executeQuery(); //
		while (res.next()) {
			carList.add(readData(res));
		}
		return carList;
	}

	public boolean update(Car car, int id, Connection conn) throws SQLException {

		NamedParameterStatement named = updateData(UPDATE, car, conn, id);
		boolean result = false;
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}
		return result;
	}

	public boolean delete(int id, Connection conn) throws SQLException {

		boolean result = false;
		NamedParameterStatement named = new NamedParameterStatement(conn, DELETE);
		named.setInt("idcar", id);
		int rowAffected = named.executeUpdate();
		if (rowAffected > 0) {
			result = true;
		}

		return result;
	}

	private NamedParameterStatement createData(String querry, Car car, Connection conn) throws SQLException {
		NamedParameterStatement named = new NamedParameterStatement(conn, querry);
		named.setString("brand", car.getBrand());
		named.setString("model", car.getModel());
		named.setString("plate", car.getPlate());
		named.setString("produced", car.getProduced());
		named.setString("firstregistration", car.getFirstRegistration());
		named.setString("engine", car.getEngineSize());
		named.setString("value", car.getValue());
		named.setString("rentperhour", car.getRentPerHour());
		named.setString("distance", car.getDistance());
		named.setString("available", car.getAvailable());
		return named;
	}

	private Car readData(ResultSet res) throws SQLException {

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

	private NamedParameterStatement updateData(String querry, Car car, Connection conn, int id) throws SQLException {
		NamedParameterStatement named = new NamedParameterStatement(conn, querry);
		named.setString("brand", car.getBrand());
		named.setString("model", car.getModel());
		named.setString("plate", car.getPlate());
		named.setString("produced", car.getProduced());
		named.setString("firstregistration", car.getFirstRegistration());
		named.setString("engine", car.getEngineSize());
		named.setString("value", car.getValue());
		named.setString("rentperhour", car.getRentPerHour());
		named.setString("distance", car.getDistance());
		named.setString("available", car.getAvailable());
		named.setInt("idcar", id);
		return named;
	}
}
