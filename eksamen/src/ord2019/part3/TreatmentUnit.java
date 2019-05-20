package ord2019.part3;

/**
 * Default logic for linking doctors and patients.
 */
public class TreatmentUnit extends AbstractTreatmentUnit {

	@Override
	protected boolean startTreatment(final Doctor doctor) {
		for (final Patient patient : getWaitingPatients()) {
			if (doctor.canTreat(patient) > 0.0) {
				doctor.setPatient(patient);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean startTreatment(final Patient patient) {
		for (final Doctor doctor : getAvailableDoctors()) {
			if (doctor.canTreat(patient) > 0.0) {
				doctor.setPatient(patient);
				return true;
			}
		}
		return false;
	}
}
