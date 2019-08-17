package kont2019.bike;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalTimeStringConverter;

public class BikeRentalController {

	private final BikeRental bikeRental;
	private final Person me;
	private final Bike bike;

	public BikeRentalController() {
		bikeRental = new BikeRental();
		final GeoLocation here = new GeoLocation(63, 10);
		bikeRental.addPlace(here);
		bike = new Bike();
		bikeRental.addBike(bike, here);
		me = new Person("Hallvard");
	}

	private final LocalTimeStringConverter localTimeStringConverter = new LocalTimeStringConverter();

	@FXML
	public void initialize() {
		setFromTime(LocalTime.now());
		setToTime(LocalTime.now().plus(1, ChronoUnit.HOURS));
		Platform.runLater(() -> toInput.requestFocus());
	}

	@FXML
	private TextField fromInput;

	@FXML
	private TextField toInput;

	/**
	 * @return a LocalDataTime object corresponding to the from input field value
	 */
	private LocalTime getFromTime() {
		return localTimeStringConverter.fromString(fromInput.getText());
	}
	/**
	 * Updates the from input field value according to the LocalDateTime argument
	 * @param time
	 */
	private void setFromTime(final LocalTime time) {
		fromInput.setText(localTimeStringConverter.toString(time));
	}

	private LocalTime getToTime() {
		return localTimeStringConverter.fromString(toInput.getText());
	}
	private void setToTime(final LocalTime time) {
		toInput.setText(localTimeStringConverter.toString(time));
	}

	@FXML
	public void plus1HourAction() {
		setToTime(getToTime().plus(1, ChronoUnit.HOURS));
	}

	@FXML
	public void minus1HourAction() {
		setToTime(getToTime().minus(1, ChronoUnit.HOURS));
	}

	private LocalDateTime toLocalDateTime(final LocalTime time) {
		return LocalDateTime.now().withHour(time.getHour()).withMinute(time.getMinute());
	}

	@FXML
	public void rentAction() {
		bikeRental.rentBike(me, bike, toLocalDateTime(getFromTime()) , toLocalDateTime(getToTime()));
	}
}
