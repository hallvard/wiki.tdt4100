package ord2019.part5;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TreatmentUnitController implements TreatmentListener {

	private final TreatmentUnit treatmentUnit;
	private final Patient patient;

	public TreatmentUnitController() {
		treatmentUnit = new TreatmentUnit();
		final Doctor doctor1 = new Doctor("Snerkel", "flu");
		final Doctor doctor2 = new Doctor("Rosenblom", "noseblead");
		treatmentUnit.addDoctor(doctor1);
		treatmentUnit.addDoctor(doctor2);
		patient = new Patient("Børge");
		patient.addConditions("flu", "noseblead");
		treatmentUnit.addTreatmentListener(this);
	}

	@FXML
	public void initialize() {
		final KeyFrame kf1 = new KeyFrame(Duration.seconds(5), e -> treatmentUnit.addPatient(patient));
		final KeyFrame kf2 = new KeyFrame(Duration.seconds(10), e -> treat());
		final KeyFrame kf3 = new KeyFrame(Duration.seconds(15), e -> treat());
		new Timeline(kf1, kf2, kf3).play();
	}

	private void treat() {
		final Doctor doctor = treatmentUnit.getDoctor(patient);
		doctor.treat();
		treatmentUnit.treatmentFinished(doctor);
	}

	@FXML
	private Label patientMessage;

	@Override
	public void treatmentStarted(final Doctor doctor, final Patient patient, final TreatmentUnit treatmentUnit) {
		final String message = patient.getName() + " bes gå til doktor " + doctor.getName();
		patientMessage.setText(message);
	}

	@Override
	public void treatmentFinished(final Doctor doctor, final Patient patient, final TreatmentUnit treatmentUnit) {
		final String message = "Ha en god dag, " + patient.getName() + "!";
		patientMessage.setText(message);
	}
}
