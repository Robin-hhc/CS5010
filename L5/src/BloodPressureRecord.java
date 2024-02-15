
/**
 * This interface represents a record of BloodPressure.
 */
public interface BloodPressureRecord {

  /**
   * A method to retrieve patient's unique ID
   * @return string ID of the patient.
   */
  String getID();

  /**
   * A method to get the systolic blood pressure.
   * @return systolic blood pressure number of the patient.
   */
  double getSystolicReading();

  /**
   * A method to get the diastolic blood pressure.
   * @return diastolic blood pressure number of the patient.
   */
  double getDiastolicReading();

  /**
   * A method that updates the systolic blood pressure.
   * @param sys input blood pressure number that the patient currently have.
   */
  void updateSystolicReading(double sys) throws IllegalArgumentException;

  /**
   * A method that updates the diastolic blood pressure.
   * @param dias input blood pressure number that the patient currently have.
   */
  void updateDiastolicReading(double dias) throws IllegalArgumentException;
}
