package pl.myproject.model;

public class Employee {
	private int idEmployee;
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
	private String education;
	private String salary;
	private String role;
	private String email;

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEdited() {
		return edited;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employee(int id, String name, String surname, String born, String idCardNumber, String street,
			String houseNumber, String city, String country, String gender, String telephone, String createDate,
			String edited, String education, String salary, String role, String email) {
		this.idEmployee = id;
		this.name = name;
		this.surname = surname;
		this.born = born;
		this.idCardNumber = idCardNumber;
		this.street = street;
		this.houseNumber=houseNumber;
		this.city=city;
		this.country=country;
		this.gender=gender;
		this.telephone=telephone;
		this.createDate=createDate;
		this.edited=edited;
		this.education=education;
		this.salary=salary;
		this.role=role;
		this.email=email;

	}
	public Employee( String name, String surname, String born, String idCardNumber, String street,
			String houseNumber, String city, String country, String gender, String telephone, String education, String salary, String role, String email) {
	
		this.name = name;
		this.surname = surname;
		this.born = born;
		this.idCardNumber = idCardNumber;
		this.street = street;
		this.houseNumber=houseNumber;
		this.city=city;
		this.country=country;
		this.gender=gender;
		this.telephone=telephone;
		this.education=education;
		this.salary=salary;
		this.role=role;
		this.email=email;

	}
	public Employee(){}

}
