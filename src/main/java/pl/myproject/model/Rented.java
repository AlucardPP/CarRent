package pl.myproject.model;

public class Rented {
	private int idRented;
	private String employee;
	private String client;
	private String cars;
	private String fromDate;
	private String tillDate;
	private String days;
	private String price;
	private int payed;
	private int rented;
	private int carId;
	private String status;


	public int getIdRented() {
		return idRented;
	}

	public void setIdRented(int idRented) {
		this.idRented = idRented;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getCars() {
		return cars;
	}

	public void setCars(String cars) {
		this.cars = cars;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getTillDate() {
		return tillDate;
	}

	public void setTillDate(String tillDate) {
		this.tillDate = tillDate;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getPayed() {
		return payed;
	}

	public void setPayed(int payed) {
		this.payed = payed;
	}

	public int getRented() {
		return rented;
	}

	public void setRented(int rented) {
		this.rented = rented;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Rented() {
	}

	public Rented(String employee, String client, String cars, String from, String till, String days, String price,
			int isPayed, int isRented, int carId, String status) {
		this.employee = employee;
		this.client = client;
		this.cars = cars;
		this.fromDate = from;
		this.tillDate = till;
		this.days = days;
		this.price = price;
		this.payed = isPayed;
		this.rented = isRented;
		this.carId = carId;
		this.status = status;
	}

	public Rented(int idRented, String employee, String client, String cars, String from, String till, String days,
			String price, int isPayed, int isRented, int carId, String status) {
		this.idRented = idRented;
		this.employee = employee;
		this.client = client;
		this.cars = cars;
		this.fromDate = from;
		this.tillDate = till;
		this.days = days;
		this.price = price;
		this.payed = isPayed;
		this.rented = isRented;
		this.carId = carId;
		this.status = status;
	}

}
