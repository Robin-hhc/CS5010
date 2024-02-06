public class Rectangle extends GeometricObject{

  private double width;

  private double height;

  public Rectangle() {
  }

  public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
  }

  public Rectangle(double width, double height, color:String, filled: boolean) throws NegativeValueException{

    if (width < 0 || height < 0) {

      throw new NegativeValueException("Invalid width and height");

    }
    this.width = width;
    this.height = height;
    super.setColor(color);
    super.setFilled(filled);
  }

  public double getWidth() { return this.width;}

  public void setWidth(width:double) {

    if (width < 0 || height < 0) {

      throw new NegativeValueException("Invalid width");

    }

    this.width = width;

  }

  public double getHeight() { return this.height;}

  public void setHeight(height:double) {

    if (width < 0 || height < 0) {

      throw new NegativeValueException("Invalid height");

    }

    this.height = height;

  }

  public double getArea() { return this.height*this.width;}

  public double getPerimeter() { return 2*(this.height+this.width);}
}




Public class RectangleTest {


  Rectangle test;

  @Test
  public void ConstructorTest() {
    String errorMessage1 = "";
    try {
      new Rectangle(-1.0, 1.0, "white", false);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    String errorMessage2 = "";
    try {
      new Rectangle(1.0, -1.0, "white", false);
    } catch (Exception e) {
      errorMessage2 = e.getMessage();
    }
    assertEquals("Invalid width and height", errorMessage1);
    assertEquals("Invalid width and height", errorMessage2);
  }

  @Test
  public void setHeightTest() {
    test = new Rectangle(1.0, 1.0, "white", false);
    String errorMessage1 = "";
    try {
      test.setHeight(-1.0);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    assertEquals("Invalid height", errorMessage1);
  }

  @Test
  public void setWidthTest() {
    test = new Rectangle(1.0, 1.0, "white", false);
    String errorMessage1 = "";
    try {
      test.setWidth(-1.0);
    } catch (Exception e) {
      errorMessage1 = e.getMessage();
    }
    assertEquals("Invalid width", errorMessage1);
  }
}