/**
 * This interface represents a general monitor. It monitor several blood pressure records, and
 * provides general methods to manage the records.
 */
public interface Monitor {

  /**
   * Add a new record to the records.
   * @param t the new input record.
   */
  public void add(BloodPressureRecord t);

  /**
   * remove a new record to the records.
   * @param t the input record need to be removed.
   */
  public void remove(BloodPressureRecord t);

  /**
   * Count the number of records.
   * @return the number of records it contains.
   */
  public int getNumberOfRecords();

  /**
   * Check if it is in a emergency condition.
   * @return true if there exist any record with Systolic reading > 180 or Diastolic reading > 120.
   */
  public boolean emergency();
}
