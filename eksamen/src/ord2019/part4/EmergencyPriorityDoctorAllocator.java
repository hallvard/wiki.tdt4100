package ord2019.part4;

/**
 * Logic for linking doctors and patients that take a priority into account.
 * The priority is implemented by a patient Comparator.
 * This DoctorAllocator is allowed to suspend treatment of an patient with lower priority.
 */
public class EmergencyPriorityDoctorAllocator extends PriorityDoctorAllocator {

	@Override
	public Doctor allocateDoctor(final Patient patient, final TreatmentUnit treatmentUnit) {
		final Doctor doctor = super.allocateDoctor(patient, treatmentUnit);
		if (doctor != null) {
			return doctor;
		}
		Patient patientToSuspend = null;
		for (final Doctor doctor2 : treatmentUnit.getAllDoctors()) {
			if (doctor2.canTreat(patient) > 0.0) {
				final Patient existingPatient = doctor2.getPatient();
				if (existingPatient != null && patientComparator.compare(patient, existingPatient) < 0 && (patientToSuspend == null || patientComparator.compare(patientToSuspend, existingPatient) < 0)) {
					patientToSuspend = existingPatient;
				}
			}
		}
		if (patientToSuspend != null) {
			return treatmentUnit.getDoctor(patientToSuspend);
		}
		return null;
	}
}
