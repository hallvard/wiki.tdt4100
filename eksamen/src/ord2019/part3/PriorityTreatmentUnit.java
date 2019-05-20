package ord2019.part3;

/**
 * Logic for linking doctors and patients that take a priority into account.
 */
public class PriorityTreatmentUnit extends TreatmentUnit {

	protected double getPriority(final Patient patient) {
		return 0.0;
	}

	@Override
	protected boolean startTreatment(final Doctor doctor) {
		Patient patient = null;
		for (final Patient patient2 : getWaitingPatients()) {
			if (doctor.canTreat(patient2) > 0.0 && (patient == null || getPriority(patient2) > getPriority(patient))) {
				patient = patient2;
			}
		}
		if (patient != null) {
			doctor.setPatient(patient);
			return true;
		}
		return false;
	}
}
