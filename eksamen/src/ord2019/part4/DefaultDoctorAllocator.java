package ord2019.part4;

/**
 * Default logic for linking doctors and patients.
 */
public class DefaultDoctorAllocator implements DoctorAllocator {

	@Override
	public Patient allocatePatient(final Doctor doctor, final TreatmentUnit treatmentUnit) {
		for (final Patient patient : treatmentUnit.getWaitingPatients()) {
			if (doctor.canTreat(patient) > 0.0) {
				return patient;
			}
		}
		return null;
	}

	@Override
	public Doctor allocateDoctor(final Patient patient, final TreatmentUnit treatmentUnit) {
		for (final Doctor doctor : treatmentUnit.getAvailableDoctors()) {
			if (doctor.canTreat(patient) > 0.0) {
				return doctor;
			}
		}
		return null;
	}
}
