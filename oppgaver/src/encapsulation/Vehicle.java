package encapsulation;

import java.util.Arrays;
import java.util.List;

public class Vehicle {
	
	private String registrationNumber;
	private char fuel;
	private char vehicleType;
	private List<Character> validFuels = Arrays.asList('D', 'B', 'E', 'H');
	private List<Character> invalidLetters = Arrays.asList('Æ', 'Ø', 'Å');
	
	public Vehicle(char fuel, char vehicleType, String registrationNumber){
		checkValidFuelAndVehicleType(fuel, vehicleType);
		this.fuel = fuel;
		this.vehicleType = vehicleType;
		setRegistrationNumber(registrationNumber);
	}
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	public char getFuel() {
		return fuel;
	}
	
	public char getVehicleType() {
		return vehicleType;
	}
	
	public void setRegistrationNumber(String regNr){
		checkRegistrationNumber(regNr);
		this.registrationNumber = regNr;
	}
	
	private void checkValidVehicleType(char vehicleType) {
		if (!(vehicleType == 'C' || vehicleType == 'M')){
			throw new IllegalArgumentException("vehicleType must be either C or M, but was " + vehicleType);
		}
	}

	private void checkValidFuel(char fuel) {
		if (!validFuels.contains(fuel)){
			throw new IllegalArgumentException("Valid fuels are 'D', 'B', 'E' and 'H', not :" + fuel);
		}
	}
	
	private void checkValidFuelAndVehicleType(char fuel, char vehicleType){
		checkValidFuel(fuel);
		checkValidVehicleType(vehicleType);
		if(fuel == 'H' && vehicleType == 'M'){
			throw new IllegalArgumentException("fuel can not be hydrogen (H) when vehicle type is motorcycle (C)");
		}
	}

	private void checkRegistrationNumber(String regNr) {
		String regLetters = regNr.substring(0, 2);
		String regDigits = regNr.substring(2);
		checkValidLetters(regLetters);
		checkValidDigits(regDigits);
	}

	private void checkValidDigits(String regDigits) {
		if(vehicleType == 'C' && regDigits.length()!=5){
			throw new IllegalArgumentException("Cars should have a registration number with 5 digits");
		}
		if(vehicleType == 'M' && regDigits.length()!=4){
			throw new IllegalArgumentException("Motorcycles should have a registration number with 4 digits");
		}
		if (!isAllDigits(regDigits)){
			throw new IllegalArgumentException(regDigits + " should be digits");
		}
	}

	private boolean isAllDigits(String regDigits) {
		for (int i = 0; i < regDigits.length(); i++){
			if (!Character.isDigit(regDigits.charAt(i))){
				return false;
			}
		}
		return true;
	}

	private void checkValidLetters(String regLetters) {
		if(!(Character.isUpperCase(regLetters.charAt(0)) && Character.isUpperCase(regLetters.charAt(1)))){
			throw new IllegalArgumentException("Registration numbers should start with two uppercase letters but was: " + regLetters);
		}
		if(fuel == 'E' && !(regLetters.equals("EL") || regLetters.equals("EK"))){
			throw new IllegalArgumentException("For electrical vehicles the letters should be EL or EK but was :" + regLetters);
		}
		if(fuel == 'H' && !(regLetters.equals("HY"))){
			throw new IllegalArgumentException("For hydrogen vehicles the letters should be HY :" + regLetters);
		}
		if((fuel == 'D' ||fuel == 'B') && (regLetters.equals("EL")||regLetters.equals("EK")||regLetters.equals("HY"))){
			throw new IllegalArgumentException("For diesel and petrol vehicles the letters should not be EL, EK or HY, but was: " + regLetters);
		}
		if(invalidLetters.contains(regLetters.charAt(0))||invalidLetters.contains(regLetters.charAt(1))){
			throw new IllegalArgumentException("Letters should not be Æ, Ø or Å, but was " + regLetters);
		}
	}
}
