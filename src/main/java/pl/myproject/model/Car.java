package pl.myproject.model;



public class Car {
	private int idCar;
	private String brand;
	private String model;
	private String plate;
	private String produced;
	private String firstRegistration;
	private String engineSize;
	private String value;
	private String rentPerHour;
	private String distance;
	private String available;
	private String file;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int getIdCar() {
		return idCar;
	}

	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getProduced() {
		return produced;
	}

	public void setProduced(String produced) {
		this.produced = produced;
	}

	public String getFirstRegistration() {
		return firstRegistration;
	}

	public void setFirstRegistration(String firstRegistration) {
		this.firstRegistration = firstRegistration;
	}

	public String getEngineSize() {
		return engineSize;
	}

	public void setEngineSize(String engineSize) {
		this.engineSize = engineSize;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRentPerHour() {
		return rentPerHour;
	}

	public void setRentPerHour(String rentPerHour) {
		this.rentPerHour = rentPerHour;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public Car() {
	}

	public Car(String brand, String model, String plate, String produced, String firstRegistration, String engine,
			String value, String rentPerHour, String distance, String available, String file) {
		this.brand = brand;
		this.model = model;
		this.plate = plate;
		this.produced = produced;
		this.firstRegistration = firstRegistration;
		this.engineSize = engine;
		this.value = value;
		this.rentPerHour = rentPerHour;
		this.distance = distance;
		this.available = available;
		this.file = file;
	}

	public Car(String brand, String model, String plate, String produced, String firstRegistration, String engine,
			String value, String rentPerHour, String distance, String available) {
		this.brand = brand;
		this.model = model;
		this.plate = plate;
		this.produced = produced;
		this.firstRegistration = firstRegistration;
		this.engineSize = engine;
		this.value = value;
		this.rentPerHour = rentPerHour;
		this.distance = distance;
		this.available = available;
		
	}

	public Car(int idCar, String brand, String model, String plate, String produced, String firstRegistration,
			String engine, String value, String rentPerHour, String distance, String available) {
		this.idCar = idCar;
		this.brand = brand;
		this.model = model;
		this.plate = plate;
		this.produced = produced;
		this.firstRegistration = firstRegistration;
		this.engineSize = engine;
		this.value = value;
		this.rentPerHour = rentPerHour;
		this.distance = distance;
		this.available = available;
	}

}
