package ord2019.part4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public abstract class TreatmentUnit {

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

	private DoctorAllocator doctorAllocator = new DefaultDoctorAllocator();

	public void setDoctorAllocator(final DoctorAllocator doctorAllocator) {
		this.doctorAllocator = doctorAllocator;
	}

	protected boolean startTreatment(final Doctor doctor) {
		final Patient patient = doctorAllocator.allocatePatient(doctor, this);
		if (patient != null) {
			doctor.setPatient(patient);
			return true;
		}
		return false;
	}

	protected boolean startTreatment(final Patient patient) {
		final Doctor doctor = doctorAllocator.allocateDoctor(patient, this);
		if (patient != null) {
			final Patient oldPatient = doctor.getPatient();
			doctor.setPatient(patient);
			if (oldPatient != null) {
				startTreatment(oldPatient);
			}
			return true;
		}
		return false;
	}

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
