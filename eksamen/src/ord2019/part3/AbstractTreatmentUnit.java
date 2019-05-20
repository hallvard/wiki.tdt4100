package ord2019.part3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public abstract class AbstractTreatmentUnit {

	private final Collection<Doctor> doctors = new ArrayList<>();
	private final Collection<Patient> patients = new ArrayList<>();

	public void addDoctor(final Doctor doctor) {
		doctors.add(doctor);
		startTreatment(doctor);
	}

	public Collection<Doctor> getAllDoctors() {
		return new ArrayList<>(doctors);
	}

	public Collection<Doctor> getAvailableDoctors() {
		final Collection<Doctor> result = new ArrayList<>();
		for (final Doctor doctor : doctors) {
			if (doctor.isAvailable()) {
				result.add(doctor);
			}
		}
		return result;
		//		return doctors.stream().filter(Doctor::isAvailable).collect(Collectors.toList());
	}

	public void addPatient(final Patient patient) {
		patients.add(patient);
		startTreatment(patient);
	}

	public Doctor getDoctor(final Predicate<Doctor> pred) {
		for (final Doctor doctor : doctors) {
			if (pred.test(doctor)) {
				return doctor;
			}
		}
		return null;
	}

	public Doctor getDoctor(final Patient patient) {
		return getDoctor(doctor -> doctor.getPatient() == patient);
	}

	public Collection<Patient> getAllPatients() {
		return new ArrayList<>(patients);
	}

	public Collection<Patient> getWaitingPatients() {
		final Collection<Patient> result = new ArrayList<>();
		for (final Patient patient : patients) {
			if (getDoctor(patient) == null) {
				result.add(patient);
			}
		}
		return result;
	}

	protected abstract boolean startTreatment(final Doctor doctor);

	protected abstract boolean startTreatment(final Patient patient);

	public void treatmentFinished(final Doctor doctor) {
		if (doctor.getPatient() == null) {
			throw new IllegalStateException(doctor + " has no patient!");
		}
		final Patient patient = doctor.getPatient();
		doctor.setPatient(null);
		if (patient.requiresTreatment()) {
			startTreatment(patient);
		} else {
			patients.remove(patient);
		}
		startTreatment(doctor);
	}
}
