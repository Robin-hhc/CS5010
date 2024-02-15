import java.util.Objects;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.round;

public class SingleBloodPressureRecord implements BloodPressureRecord{
  String id;
  double sys;
  double dias;

  public SingleBloodPressureRecord(String id, double sys, double dias) throws IllegalArgumentException{
    if (Objects.equals(id, "")) {
      throw new IllegalArgumentException("Invalid ID");
    }
    if (sys < 0 || dias < 0 || dias < sys) {
      throw new IllegalArgumentException("Invalid Blood Pressure");
    }
    this.id = id;
    this.sys = sys;
    this.dias = dias;
  }

  @Override
  public String getID() {
    return this.id;
  }

  @Override
  public double getSystolicReading() {
    return this.sys;
  }

  @Override
  public double getDiastolicReading() {
    return this.dias;
  }

  @Override
  public void updateSystolicReading(double sys) throws IllegalArgumentException {
    if (sys < 0 || sys > this.dias) {
      throw new IllegalArgumentException("Invalid Blood Pressure");
    }
    this.sys = sys;
  }

  @Override
  public void updateDiastolicReading(double dias) throws IllegalArgumentException {
    if (dias < 0 || dias < this.sys) {
      throw new IllegalArgumentException("Invalid Blood Pressure");
    }
    this.dias = dias;
  }

//  /**
//   * A method that compare two blood pressure records.
//   * @return true if they have the same ID (case-sensitive), and their respective readings are less
//   * than 1 apart. Otherwise, false.
//   */
//  public boolean equality(SingleBloodPressureRecord record) {
//    return Objects.equals(this.id, record.getID()) &&
//            abs(this.sys - record.getSystolicReading()) < 1 &&
//            abs(this.dias - record.getDiastolicReading()) < 1;
//  }

  /**
   * A method that compare two blood pressure records.
   * @return true  if they have the same ID (case-sensitive) and if their respective readings,
   * when rounded to the nearest whole number, are the same (e.g. 125.6 and 126.1)
   */
  public boolean equality(SingleBloodPressureRecord record) {
    return Objects.equals(this.id, record.getID()) &&
            floor(this.sys) == floor(record.getSystolicReading()) &&
            floor(this.dias) == floor(record.getDiastolicReading());
  }
}
