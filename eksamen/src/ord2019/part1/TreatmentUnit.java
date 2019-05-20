package ord2019.part1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * A class for managing a set of doctors and the patients they're treating.
 * When doctors or patients arrive, it is made sure that patients are treated as soon as possible.
 */
public class TreatmentUnit {

	private final Collection<Doctor> doctors = new ArrayList<>();
	private final Collection<Patient> patients = new ArrayList<>();

	/**
	 * Adds a doctor and makes sure s/he starts treating a patient, if one is waiting.
	 * @param doctor
	 */
	public void addDoctor(final Doctor doctor) {
		doctors.add(doctor);
		startTreatment(doctor);
	}

	/**
	 * @return the currently available doctors
	 */
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

	/**
	 * Adds a patient to this treatment unit, and makes sure treatment starts if any doctor is available.
	 * Otherwise the patient is queued for treatment when a doctor becomes available.
	 * @param patient
	 */
	public void addPatient(final Patient patient) {
		patients.add(patient);
		startTreatment(patient);
	}

	/**
	 * @param pred the predicate that the doctor must satisfy
	 * @return some doctor satisfying the predicate
	 */
	public Doctor getDoctor(final Predicate<Doctor> pred) {
		for (final Doctor doctor : doctors) {
			if (pred.test(doctor)) {
				return doctor;
			}
		}
		return null;
	}

	/**
	 * Find the doctor, if any, that treats the provided patient.
	 * @param patient
	 * @return the doctor treating the provided patient, or null, of the patient isn't currently being treated
	 */
	public Doctor getDoctor(final Patient patient) {
		return getDoctor(doctor -> doctor.getPatient() == patient);
	}

	/**
	 * Find all patients that are not currently being treated
	 * @return the patients not currently being treated
	 */
	public Collection<Patient> getWaitingPatients() {
		final Collection<Patient> result = new ArrayList<>();
		for (final Patient patient : patients) {
			if (getDoctor(patient) == null) {
				result.add(patient);
			}
		}
		return result;
	}

	/**
	 * Finds a waiting patient and sets him/her as the provided doctor's patient
	 * @param doctor the doctor for which a patient to treat should be found
	 * @return true if a patient for the provided doctor was found, false otherwise
	 */
	private boolean startTreatment(final Doctor doctor) {
		final Collection<Patient> waitingPatients = getWaitingPatients();
		if (waitingPatients.isEmpty()) {
			return false;
		}
		doctor.setPatient(waitingPatients.iterator().next());
		return true;
	}

	/**
	 * Finds an available doctor for the provided patient, and sets that doctor to treat the patient
	 * @param patient the patient for which a treating doctor should be found
	 * @return true if a doctor for the provided patient was found, false otherwise
	 */
	private boolean startTreatment(final Patient patient) {
		final Collection<Doctor> availableDoctors = getAvailableDoctors();
		if (availableDoctors.isEmpty()) {
			return false;
		}
		final Doctor doctor = availableDoctors.iterator().next();
		doctor.setPatient(patient);
		return true;
	}

	/**
	 * Removes the link between doctor and patient, after treatment is finished.
	 * Since the patient is fully treated, s/he is removed from this treatment unit.
	 * Also ensure the doctor starts treating another patient.
	 * @param doctor the doctor that has finished treating his/her patient
	 */
	public void treatmentFinished(final Doctor doctor) {
		if (doctor.getPatient() == null) {
			throw new IllegalStateException(doctor + " has no patient!");
		}
		final Patient patient = doctor.getPatient();
		doctor.setPatient(null);
		patients.remove(patient);
		startTreatment(doctor);
	}
}
