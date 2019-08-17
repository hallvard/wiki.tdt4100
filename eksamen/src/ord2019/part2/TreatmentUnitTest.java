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
	 *   tu -> "doctor1 : Doctor" as doc1: canTreat(patient)
	 *   doc1 -> "patient : Patient" as patient: iterator()
	 *   doc1 --> tu: 0.5
	 *   tu -> doc1: setPatient(patient)
	 *   tu --> tu: true
	 *   tut -> tu: getDoctor(patient)
	 *   tu --> tut: doctor1
	 *   tut -> doc1: treat()
	 *   doc1 -> patient: removeConditions("flu")
	 *   tut -> tu: treatmentFinished(doc1)
	 *   tu -> doc1: setPatient(null)
	 *   tu -> patient: requiresTreatment()
	 *   patient --> tu: true
	 *   tu -> tu: startTreatment(patient)
	 *   tu -> "doctor2 : Doctor" as doc2: canTreat(patient)
	 *   doc2 -> patient: iterator()
	 *   doc2 --> tu: 1.0
	 *   tu -> doc2: setPatient(patient)
	 *   tu --> tu: true
	 *   tu -> tu: startTreatment(doc1)
	 *   tu --> tu: false
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
		treatmentUnit.treatmentFinished(patientDoctor);
		// end sequence diagram
		assertTrue(patientDoctor.isAvailable());
		assertNotSame(patientDoctor, treatmentUnit.getDoctor(patient));
		patientDoctor = treatmentUnit.getDoctor(patient);
		patientDoctor.treat();
		treatmentUnit.treatmentFinished(patientDoctor);
		assertTrue(doctor1.isAvailable() && doctor2.isAvailable());
	}
}
