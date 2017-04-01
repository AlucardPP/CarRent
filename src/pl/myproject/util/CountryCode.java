package pl.myproject.util;

import java.util.HashMap;

import java.util.Locale;

public class CountryCode {

	public static HashMap<String, String> getCode() {
		HashMap<String, String> countryMap = new HashMap<String, String>();
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			countryMap.put(obj.getCountry(), obj.getDisplayCountry());
		}
		return countryMap;

	}

	//
	// public final List<Country> countriesCode(){
	// List<Country> countries = new ArrayList<Country>();
	//
	// Locale[] locales = Locale.getAvailableLocales();
	// for (Locale locale : locales) {
	//
	// String code = locale.getCountry();
	// String name = locale.getDisplayCountry();
	//
	// if (!"".equals(code) && !"".equals(name)) {
	// countries.add(new Country( code, name));
	// }
	// }
	//
	// Collections.sort(countries, new CountryComparator());
	// return countries;
	// }

}
