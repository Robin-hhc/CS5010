package Q31;

import java.util.Date;

public class GeometricObject {
  private String color;

  private boolean filled;

  private java.util.Date dateCreated;

  public GeometricObject() {
    this.dateCreated = new java.util.Date();
  }

  public GeometricObject(String color, boolean filled) {
    this.color = color;
    this.filled = filled;
    this.dateCreated = new java.util.Date();
  }

  public String getColor() {
    return this.color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public boolean isFilled() {
    return this.filled;
  }

  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  public java.util.Date getDateCreated() {
    return this.dateCreated;
  }

  public String toString() {
    String ans;
    if (this.isFilled()) {
      ans = "GeometricObject is filled with color " + this.color + ", created on " + this.dateCreated;
    } else {
      ans = "GeometricObject is not filled with color " + this.color + ", created on " + this.dateCreated;
    }
    return ans;
  }
}
