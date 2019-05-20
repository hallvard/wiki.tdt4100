package ord2019.part4;

import java.util.Comparator;

/**
 * Logic for linking doctors and patients that take a priority into account.
 * The priority is implemented by a patient Comparator.
 */
public class PriorityDoctorAllocator extends DefaultDoctorAllocator {

	protected Comparator<Patient> patientComparator;

	public void setPatientComparator(final Comparator<Patient> patientComparator) {
		this.patientComparator = patientComparator;
	}

	@Override
	public Patient allocatePatient(final Doctor doctor, final TreatmentUnit treatmentUnit) {
		Patient patient = null;
		for (final Patient patient2 : treatmentUnit.getWaitingPatients()) {
			if (doctor.canTreat(patient2) > 0.0 && (patient == null || patientComparator.compare(patient, patient2) < 0)) {
				patient = patient2;
			}
		}
		return patient;
	}
}
