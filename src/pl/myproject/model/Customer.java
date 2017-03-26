package pl.myproject.model;



public class Customer {

	private int idCustomer;
	private String name;
	private String surname;
	private String born;
	private String idCardNumber;
	private String street;
	private String houseNumber;
	private String city;
	private String country;
	private String gender;
	private String telephone;
	private String createDate;
	private String edited;
	
	
	


	public String getEdited() {
		return edited;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBorn() {
		return born;
	}

	public void setBorn(String born) {
		this.born = born;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Customer() {
	}

	public Customer( String name, String surname, String born, String idCardNumber, String street,
			String houseNumber, String city, String country, String gender, String telephone) {

		
		this.name = name;
		this.surname = surname;
		this.born = born;
		this.idCardNumber = idCardNumber;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.country = country;
		this.gender = gender;
		this.telephone = telephone;
	
		
	}
	public Customer( int idCustomer, String name, String surname, String born, String idCardNumber, String street,
			String houseNumber, String city, String country, String gender, String telephone, String created, String edited) {

		this.idCustomer=idCustomer;
		this.name = name;
		this.surname = surname;
		this.born = born;
		this.idCardNumber = idCardNumber;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.country = country;
		this.gender = gender;
		this.telephone = telephone;
		this.createDate=created;
		this.edited=edited;
		
	}
	

}
