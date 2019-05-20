package ord2019.part4;

public interface DoctorAllocator {
	public Patient allocatePatient(Doctor doctor, TreatmentUnit treatmentUnit);
	public Doctor allocateDoctor(Patient patient, TreatmentUnit treatmentUnit);
}
