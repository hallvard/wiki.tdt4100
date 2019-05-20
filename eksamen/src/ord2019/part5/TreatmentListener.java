package ord2019.part5;

/**
 * Listener interface for events in a TreatmentUnit
 */
public interface TreatmentListener {
	/**
	 * Called when a patient is allocated to a doctor, so treatment can begin.
	 * Can be called several times with different doctors for the same patient,
	 * since the patient's conditions may require treatment from several doctors.
	 * @param doctor the currently allocated doctor
	 * @param patient the patient for which treatment can begin
	 * @param treatmentUnit the managing treatment unit
	 */
	public void treatmentStarted(Doctor doctor, Patient patient, TreatmentUnit treatmentUnit);
	/**
	 * Called when a patient is fully treated, i.e. has been removed from the treatment unit.
	 * @param doctor the doctor that finished the treatment
	 * @param patient the fully treated patient
	 * @param treatmentUnit the managing treatment unit
	 */
	public void treatmentFinished(Doctor doctor, Patient patient, TreatmentUnit treatmentUnit);
}
