# CarRent
Utworzony administrator systemu "Jan Kowalski" ma login: "admin@o2.pl", hasło: "admin".

Dla nowo utworzonych pracowników domyślne hasło brzmi: "test"

DOSTĘP:
MAmy 3 role dla użytkownika:
-Regular
-Manager
-Admin ( na starcie mamy jednego użytkownika o tej roli)

Regular nie widzi zakłądki Employee, może dodawać klienta, Samochód, i zarządzać wypożyczaniem
-Manager może dodawać tylko regular użytkownika i ma dostęp jak regular
-Admin - może dodawać Employee/Manager/Regular Employee , ale nie może dodawać car/client lub wypożyczać samochód.
