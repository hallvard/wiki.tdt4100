package ord2019.part5;

import java.util.Arrays;
import java.util.Collection;

/**
 * A doctor has the capacity to treat one patient at a time.
 * The doctor as a list of competencies (of type String) that
 * indicates what conditions s/he can treat.
 */
public class Doctor {

	private final String name;
	private final Collection<String> competencies;

	/**
	 * Initialise this doctor with a name and a set of competencies
	 * @param name
	 * @param competencies
	 */
	public Doctor(final String name, final String... competencies) {
		this.name = name;
		this.competencies = Arrays.asList(competencies);
	}

	public String getName() {
		return name;
	}

	private Patient patient;

	/**
	 * @return the patient this doctor is treating, or null if s/he isn't currently treating any patient.
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @return true if this doctor is currently treating a patient, otherwise false.
	 */
	public boolean isAvailable() {
		return getPatient() == null;
	}

	/**
	 * Sets the patient that this doctor is treating, use null to indicate s/he isn't currently treating any patient.
	 * @param patient
	 */
	public void setPatient(final Patient patient) {
		this.patient = patient;
	}

	/**
	 * Indicates to what extent this doctor can treat the provided patient.
	 * The value is the number of the patient's conditions this doctor can treat
	 * divided by the number of conditions the patient has
	 * Conditions and competencies are matches using simple String comparison
	 * @param patient
	 * @return the ratio of the patient's conditions that this doctor can treat
	 */
	public double canTreat(final Patient patient) {
		int count = 0, total = 0;
		for (final String item : patient) {
			total++;
			if (competencies.contains(item)) {
				count++;
			}
		}
		return ((double) count) / total;
	}

	/**
	 * "Treats" the patient by removing all the patient's conditions that this doctor can treat.
	 */
	public void treat() {
		patient.removeConditions(competencies);
	}
}
