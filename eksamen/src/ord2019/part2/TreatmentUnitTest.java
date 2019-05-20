package ord2019.part2;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TreatmentUnitTest {

	private TreatmentUnit treatmentUnit;

	@Before
	public void setUp() {
		treatmentUnit = new TreatmentUnit();
	}

	@Test
	public void testAddDoctorPatient() {
		final Doctor doctor = new Doctor("flu", "noseblead");
		treatmentUnit.addDoctor(doctor);
		assertTrue(doctor.isAvailable());
		assertNull(doctor.getPatient());
		final Patient patient = new Patient();
		patient.addConditions("flu");
		treatmentUnit.addPatient(patient);
		assertTrue(! doctor.isAvailable());
		assertSame(patient, doctor.getPatient());
		doctor.treat();
		treatmentUnit.treatmentFinished(doctor);
		assertTrue(doctor.isAvailable());
		assertNull(doctor.getPatient());
	}

	@Test
	public void testAddPatientDoctor() {
		final Patient patient = new Patient();
		patient.addConditions("flu");
		treatmentUnit.addPatient(patient);
		final Doctor doctor = new Doctor("flu", "noseblead");
		treatmentUnit.addDoctor(doctor);
		assertTrue(! doctor.isAvailable());
		assertSame(patient, doctor.getPatient());
		doctor.treat();
		treatmentUnit.treatmentFinished(doctor);
		assertTrue(doctor.isAvailable());
		assertNull(doctor.getPatient());
	}

	/**
	 * @startuml
	 *   "tut : TreatmentUnitTest" as tut -> "tu : TreatmentUnit" as tu: addPatient(patient)
	 *   tu -> tu: startTreatment(patient)
	 *   tu -> "doctor : Doctor" as doc: canTreat(patient)
	 *   doc -> "patient : Patient" as patient: iterator()
	 *   doc --> tu: 0.5
	 *   tu -> doc: setPatient(patient)
	 *   tut -> tu: getDoctor(patient)
	 *   tu --> tut: doctor
	 *   tut -> doc: treat()
	 *   doc -> patient: removeConditions("flu")
	 * @enduml
	 */
	@Test
	public void testAddDoctorsPatient() {
		final Doctor doctor1 = new Doctor("flu");
		final Doctor doctor2 = new Doctor("noseblead");
		treatmentUnit.addDoctor(doctor1);
		treatmentUnit.addDoctor(doctor2);
		assertTrue(doctor1.isAvailable() && doctor2.isAvailable());
		final Patient patient = new Patient();
		patient.addConditions("flu", "noseblead");
		// start sequence diagram
		treatmentUnit.addPatient(patient);
		assertTrue(! (doctor1.isAvailable() && doctor2.isAvailable()));
		Doctor patientDoctor = treatmentUnit.getDoctor(patient);
		patientDoctor.treat();
		// end sequence diagram
		treatmentUnit.treatmentFinished(patientDoctor);
		assertTrue(patientDoctor.isAvailable());
		assertNotSame(patientDoctor, treatmentUnit.getDoctor(patient));
		patientDoctor = treatmentUnit.getDoctor(patient);
		patientDoctor.treat();
		treatmentUnit.treatmentFinished(patientDoctor);
		assertTrue(doctor1.isAvailable() && doctor2.isAvailable());
	}
}
