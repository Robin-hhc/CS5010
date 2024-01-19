public class Vector3D {
  private double x;
  private double y;
  private double z;

  /**
   * Construct a Vector3D object that has 3 vector degree x, y, z.
   *
   * @param x  the degree in x-axis
   * @param y  the degree in y-axis
   * @param z  the degree in z-axis
   */
  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Return degree of x-axis
   *
   * @return  x  the degree in x-axis
   */
  public double getX() {
    return this.x;
  }

  /**
   * Return degree of y-axis
   *
   * @return  y  the degree in y-axis
   */
  public double getY() {
    return this.y;
  }

  /**
   * Return degree of z-axis
   *
   * @return  z  the degree in z-axis
   */
  public double getZ() {
    return this.z;
  }

  /**
   * Override the toString in format of (x, y, z)
   *
   * @return  string in format of (x, y, z)
   */
  public String toString() {
    return String.format("(%f, %f, %f)", x, y, z);
  }

  /**
   * Calculate the magnitude of the vector
   *
   * @return  the magnitude value calculated
   */
  public double getMagnitude() {
    return Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
  }

  /**
   * Normalize the vector
   *
   * @return  the new normalize vector3D object
   */
  public Vector3D normalize() throws IllegalStateException{
    double mag = this.getMagnitude();
    if (mag == 0) {
      throw new IllegalStateException("Magnitude can not be Zero");
    } else {
      return new Vector3D(this.x/mag, this.y/mag, this.z/mag);
    }
  }
}