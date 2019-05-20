package ord2019.part3;

/**
 * Logic for linking doctors and patients that take a priority into account, and
 * that is allowed to suspend treatment of an patient with lower priority.
 */
public class EmergencyPriorityTreatmentUnit extends PriorityTreatmentUnit {

	@Override
	public boolean startTreatment(final Patient patient) {
		if (! super.startTreatment(patient)) {
			Patient patientToSuspend = null;
			for (final Doctor doctor : getAllDoctors()) {
				if (doctor.canTreat(patient) > 0.0) {
					final Patient existingPatient = doctor.getPatient();
					if (existingPatient != null && getPriority(existingPatient) < getPriority(patient) && (patientToSuspend == null || getPriority(existingPatient) < getPriority(patientToSuspend))) {
						patientToSuspend = existingPatient;
					}
				}
			}
			if (patientToSuspend != null) {
				getDoctor(patientToSuspend).setPatient(patient);
				startTreatment(patientToSuspend);
				return true;
			}
		}
		return false;
	}
}
