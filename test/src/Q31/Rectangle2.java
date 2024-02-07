package Q31;
import java.util.*;

public class Rectangle2 extends GeometricObject{

  private double width;

  private double height;

  public Rectangle2() {
  }

  public Rectangle2(double width, double height) throws NegativeValueException {
    if (width < 0 || height < 0) {
      throw new NegativeValueException("Invalid width and height");
    }
    this.width = width;
    this.height = height;
  }

  public Rectangle2(double width, double height, String color, boolean filled) throws NegativeValueException{
    if (width < 0 || height < 0) {
      throw new NegativeValueException("Invalid width and height");
    }
    this.width = width;
    this.height = height;
    super.setColor(color);
    super.setFilled(filled);
  }

  public double getWidth() { return this.width;}

  public void setWidth(double width) throws NegativeValueException {
    if (width < 0 || height < 0) {
      throw new NegativeValueException("Invalid width");
    }
    this.width = width;
  }

  public double getHeight() { return this.height;}

  public void setHeight(double height) throws NegativeValueException {
    if (width < 0 || height < 0) {
      throw new NegativeValueException("Invalid height");
    }
    this.height = height;
  }

  public double getArea() { return this.height*this.width;}

  public double getPerimeter() { return 2*(this.height+this.width);}
}

