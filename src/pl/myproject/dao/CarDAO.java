package pl.myproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.myproject.model.Car;
import pl.myproject.util.ConnectionProvider;

public class CarDAO {
	private final static String CREATE = "INSERT INTO car( brand, model, plate, produced, firstregistration, engine, value, rentperhour, distance, available ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private final static String READ = "SELECT * FROM car;";
	private final static String UPDATE = "UPDATE car SET brand = ?, model = ?, plate = ?, produced = ?, firstregistration = ?, engine = ?, value = ?, rentperhour = ?, distance = ?, available = ? WHERE idcar = ?;";
	private final static String DELETE = "DELETE FROM car WHERE idcar = ?;";
	
	public boolean create(Car car){
		Connection conn = null;
		PreparedStatement prepstmt = null;
		boolean result = false;
		try{
			conn = ConnectionProvider.getConnection();
			prepstmt = conn.prepareStatement(CREATE);
			prepstmt.setString(1, car.getBrand());
			prepstmt.setString(2, car.getModel());
			prepstmt.setString(3, car.getPlate());
			prepstmt.setString(4, car.getProduced());
			prepstmt.setString(5, car.getFirstRegistration());
			prepstmt.setString(6, car.getEngineSize());
			prepstmt.setString(7, car.getValue());
			prepstmt.setString(8, car.getRentPerHour());
			prepstmt.setString(9, car.getDistance());
			prepstmt.setString(10, car.getAvailable());
			int rowAffected = prepstmt.executeUpdate();
			if(rowAffected > 0){
				result=true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			releaseResource(conn, prepstmt, null);
		}
		return result;
	}
	public List<Car> read(){
		Connection conn = null;
		PreparedStatement prepstmt = null;
		ResultSet res = null;
		Car resultCar = null;
		List<Car> carList = new ArrayList<Car>();
		try{
			conn = ConnectionProvider.getConnection();
			prepstmt = conn.prepareStatement(READ);
			res = prepstmt.executeQuery();
			while (res.next()){
				resultCar = new Car();
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
				carList.add(resultCar);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			releaseResource(conn, prepstmt, res);
		}
		return carList;
	}
	public boolean update(Car car, int id){
		Connection conn = null;
		PreparedStatement prepstmt = null;
		boolean result = false;
		try{
			conn = ConnectionProvider.getConnection();
			prepstmt = conn.prepareStatement(UPDATE);
			prepstmt.setString(1, car.getBrand());
			prepstmt.setString(2, car.getModel());
			prepstmt.setString(3, car.getPlate());
			prepstmt.setString(4, car.getProduced());
			prepstmt.setString(5, car.getFirstRegistration());
			prepstmt.setString(6, car.getEngineSize());
			prepstmt.setString(7, car.getValue());
			prepstmt.setString(8, car.getRentPerHour());
			prepstmt.setString(9, car.getDistance());
			prepstmt.setString(10, car.getAvailable());
			prepstmt.setInt(11, id);
			int rowAffected = prepstmt.executeUpdate();
			if(rowAffected > 0){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			releaseResource(conn, prepstmt, null);
		}
		return result;
	}
	public boolean delete(int id){
		Connection conn = null;
		PreparedStatement prepstmt = null;
		boolean result = false;
		try{
			conn = ConnectionProvider.getConnection();
			prepstmt = conn.prepareStatement(DELETE);
			prepstmt.setInt(1, id);
			int rowAffected = prepstmt.executeUpdate();
			if(rowAffected > 0){
				result = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
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
