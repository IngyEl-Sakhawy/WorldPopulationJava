package day6.classes;

import java.util.*;
import java.util.stream.Collectors;

public class CitiesSearch {

    
public static void mostPopulatedCityOfEachCountry(Map<String, Country> allCountries, Map<Integer, City> allCities) {
        allCountries.values().forEach(country -> {
            City maxCity = allCities.values().stream()
                .filter(city -> city.getCountryCode().equals(country.getCode()))
                .max(Comparator.comparingInt(City::getPopulation))
                .orElse(null);
            
            if (maxCity != null) {
                System.out.println("Country: " + country.getName() + ", Max Populated City: " + maxCity.getName());
            }
        });
    }

    public static void mostPopulatedCityOfEachContinent(Set<String> allContinents, Map<String, Country> allCountries, Map<Integer, City> allCities) {
        allContinents.forEach(continent -> {
            City maxCity = allCities.values().stream()
                .filter(city -> {
                    Country country = allCountries.get(city.getCountryCode());
                    return country != null && country.getContinent().equals(continent);
                })
                .max(Comparator.comparingInt(City::getPopulation))
                .orElse(null);

            if (maxCity != null) {
                System.out.println("Continent: " + continent + ", Max Populated City: " + maxCity.getName());
            }
        });
    }

    public static void mostPopulatedCapital(Map<String, Country> allCountries, Map<Integer, City> allCities) {
        List<City> capitalCities = allCountries.values().stream()
                    .map(country -> allCities.get(country.getCapital()))
                    .filter(city -> city != null)
                    .collect(Collectors.toList());

            capitalCities.stream()
                    .max(Comparator.comparingInt(City::getPopulation))
                    .ifPresent(maxCity -> {
                        System.out.println("Most Populated Capital City is: " + maxCity.getName());
                    });
    }
}


