import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a patient monitor. It monitor several blood pressure records,
 * specifically to see how many of them are going into hypertensive crisis.
 */
public class PatientMonitor implements Monitor{
  private List<BloodPressureRecord> bpRecordList;

  public PatientMonitor() {
    this.bpRecordList = new ArrayList<BloodPressureRecord>();
  }

  @Override
  public void add(BloodPressureRecord t) {
    bpRecordList.add(t);
  }

  @Override
  public void remove(BloodPressureRecord t) {
    bpRecordList.remove(t);
  }

  @Override
  public int getNumberOfRecords() {
    return bpRecordList.size();
  }

  @Override
  public boolean emergency() {
    int count = 0;
    for (BloodPressureRecord t: bpRecordList) {
      if ((t.getSystolicReading()>180) || (t.getDiastolicReading()>120)) {
        count +=1;
      }
    }
    return count>=1; // change the condition to includes 1
  }
}
