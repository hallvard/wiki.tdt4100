package ord2019.part3;

import java.util.Arrays;
import java.util.Collection;

public class Doctor {

	private final Collection<String> competencies;

	public Doctor(final String... competencies) {
		this.competencies = Arrays.asList(competencies);
	}

	private Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public boolean isAvailable() {
		return getPatient() == null;
	}

	public void setPatient(final Patient patient) {
		this.patient = patient;
	}

	public double canTreat(final Patient patient) {
		int count = 0, total = 0;
		for (final String item : patient) {
			total++;
			if (competencies.contains(item)) {
				count++;
			}
		}
		return ((double) total) / count;
	}

	public void treat() {
		patient.removeConditions(competencies);
	}
}
