package sim;

// TODO ask TA about the Ball View
/**
 * This class implements a simple pool simulator with only simple type and friction type
 */
public class SimplePoolSimulator implements PoolSimulator{

  private int width;
  private int height;
  private String type;
  private double x;
  private double y;
  private double radius;
  private double dx;
  private double dy;
  private double speed;
  private String status;
  private final int COLLISION_FRICTION = 5;
  private final double FRICTION = 0.1;
  private final double GRAVITY = 0.1;


  /**
   * Constructor of SimplePoolSimulator.
   * @param width the width of the table.
   * @param height the width of the table.
   * @param type one of the "simple" and "friction" type of the table.
   * @throws IllegalArgumentException if the width and height are negative and if type is not
   * "simple" or "friction"
   */
  public SimplePoolSimulator(int width,int height,String type) throws IllegalArgumentException {
    if (width < 0) {
      throw new IllegalArgumentException("Width Invalid");
    } else if (height < 0) {
      throw new IllegalArgumentException("Height Invalid");
    } else if (!type.equals("simple") && !type.equals("friction")) {
      throw new IllegalArgumentException("Type Invalid");
    }

    this.width = width;
    this.height = height;
    this.type = type;
    this.x = Double.NEGATIVE_INFINITY;
    this.y = Double.NEGATIVE_INFINITY;
    this.radius = Double.NEGATIVE_INFINITY;
    this.dx = 0;
    this.dy = 0;
    this.speed = 0;
    this.status = "Ball not set up";
  }

  @Override
  public void start(int x, int y, int radius, int speed, double dx, double dy) throws IllegalArgumentException {
    if (radius < 0) {
      throw new IllegalArgumentException("radius invalid.");
    } else if (y-radius < 0 || y+radius > this.height) {
      throw new IllegalArgumentException("y invalid.");
    } else if (x-radius < 0 || x+radius > this.width) {
      throw new IllegalArgumentException("x invalid.");
    } else if (speed < 0) {
      throw new IllegalArgumentException("speed invalid.");
    } else if (dx == 0 && dy == 0) {
      throw new IllegalArgumentException("dx and dy can not be both zero.");
    }

    this.x = x;
    this.y = y;
    this.radius = radius;
    this.dx = dx / Math.sqrt(dx*dx + dy*dy);
    this.dy = dy / Math.sqrt(dx*dx + dy*dy);
    this.status = "Simulation started";
  }

  /**
   * Helper method for advance. Advance one collision in simple type
   */
  private void advanceSimple() {
    double tRight;
    double tLeft;
    double tTop;
    double tBottom;
    if (this.dx == 0 && this.dy > 0) { // dx = 0 and collision top edge
      this.y = (this.height-this.radius-this.y) / this.getBallVelocityY() * this.getBallVelocityY();
      this.dy = -this.dy;
      this.status = "Ball hit top edge";
    } else if (this.dx == 0 && this.dy < 0) { // dx = 0 and collision bottom edge
      this.y = (this.radius-this.y) / this.getBallVelocityY() * this.getBallVelocityY();
      this.dy = -this.dy;
      this.status = "Ball hit bottom edge";
    } else if (this.dx > 0 && this.dy == 0) { // dy = 0 and collision right edge
      this.x = (this.width-this.radius-this.x) / this.getBallVelocityX() * this.getBallVelocityX();
      this.dx = -this.dx;
      this.status = "Ball hit right edge";
    } else if (this.dx < 0 && this.dy == 0) { // dy = 0 and collision left edge
      this.x = (this.radius-this.x) / this.getBallVelocityX() * this.getBallVelocityX();
      this.dx = -this.dx;
      this.status = "Ball hit left edge";
    } else if (this.dx > 0 && this.dy > 0) { // going top right
      tRight = (this.width-this.radius-this.x) / this.getBallVelocityX();
      tTop = (this.height-this.radius-this.y) / this.getBallVelocityY();
      if (tRight == tTop) { // collision on right top corner TODO ask TA about status in corner cases
        this.x = tRight * this.getBallVelocityX();
        this.y = tRight * this.getBallVelocityY();
        this.dx = -this.dx;
        this.dy = -this.dy;
        this.status = "Ball hit right edge";
      } else if (tRight < tTop) { // collision on right edge
        this.x = tRight * this.getBallVelocityX();
        this.y = tRight * this.getBallVelocityY();
        this.dx = -this.dx;
        this.status = "Ball hit right edge";
      } else { // collision on top edge
        this.x = tTop * this.getBallVelocityX();
        this.y = tTop * this.getBallVelocityY();
        this.dy = -this.dy;
        this.status = "Ball hit top edge";
      }
    } else if (this.dx < 0 && this.dy > 0) { // going top left
      tLeft = (this.radius-this.x) / this.getBallVelocityX();
      tTop = (this.height-this.radius-this.y) / this.getBallVelocityY();
      if (tLeft == tTop) { // collision on left top corner
        this.x = tLeft * this.getBallVelocityX();
        this.y = tLeft * this.getBallVelocityY();
        this.dx = -this.dx;
        this.dy = -this.dy;
        this.status = "Ball hit left edge";
      } else if (tLeft < tTop) { // collision on left edge
        this.x = tLeft * this.getBallVelocityX();
        this.y = tLeft * this.getBallVelocityY();
        this.dx = -this.dx;
        this.status = "Ball hit left edge";
      } else { // collision on top edge
        this.x = tTop * this.getBallVelocityX();
        this.y = tTop * this.getBallVelocityY();
        this.dy = -this.dy;
        this.status = "Ball hit top edge";
      }
    } else if (this.dx > 0 && this.dy < 0) { // going bottom right
      tRight = (this.width-this.radius-this.x) / this.getBallVelocityX();
      tBottom = (this.radius-this.y) / this.getBallVelocityY();
      if (tRight == tBottom) { // collision on right bottom corner
        this.x = tRight * this.getBallVelocityX();
        this.y = tRight * this.getBallVelocityY();
        this.dx = -this.dx;
        this.dy = -this.dy;
        this.status = "Ball hit right edge";
      } else if (tRight < tBottom) { // collision on right edge
        this.x = tRight * this.getBallVelocityX();
        this.y = tRight * this.getBallVelocityY();
        this.dx = -this.dx;
        this.status = "Ball hit right edge";
      } else { // collision on bottom edge
        this.x = tBottom * this.getBallVelocityX();
        this.y = tBottom * this.getBallVelocityY();
        this.dy = -this.dy;
        this.status = "Ball hit bottom edge";
      }
    } else if (this.dx < 0 && this.dy < 0) { // going bottom left
      tLeft = (this.radius-this.x) / this.getBallVelocityX();
      tBottom = (this.radius-this.y) / this.getBallVelocityY();
      if (tLeft == tBottom) { // collision on left bottom corner
        this.x = tLeft * this.getBallVelocityX();
        this.y = tLeft * this.getBallVelocityY();
        this.dx = -this.dx;
        this.dy = -this.dy;
        this.status = "Ball hit left edge";
      } else if (tLeft < tBottom) { // collision on left edge
        this.x = tLeft * this.getBallVelocityX();
        this.y = tLeft * this.getBallVelocityY();
        this.dx = -this.dx;
        this.status = "Ball hit left edge";
      } else { // collision on bottom edge
        this.x = tBottom * this.getBallVelocityX();
        this.y = tBottom * this.getBallVelocityY();
        this.dy = -this.dy;
        this.status = "Ball hit bottom edge";
      }
    }
    this.speed -= COLLISION_FRICTION;
    if (this.speed <= 0) {
      this.speed = 0;
      this.dx = 0;
      this.dy = 0;
      this.status = "Ball is stationary";
    }
  }

  /**
   * Helper method for advance. Advance one collision in friction type
   */
  private void advanceFriction() {
    double solutionRight = (this.speed*this.dx*this.speed*this.dx) - (4*this.GRAVITY*this.FRICTION*this.dx/2 * (this.width-this.radius-this.x)); // Two negative turn to positive
    double solutionLeft = (this.speed*this.dx*this.speed*this.dx) - (4*this.GRAVITY*this.FRICTION*this.dx/2 * (this.radius-this.x));
    double solutionTop = (this.speed*this.dy*this.speed*this.dy) - (4*this.GRAVITY*this.FRICTION*this.dy/2 * (this.height-this.radius-this.y));
    double solutionBottom = (this.speed*this.dy*this.speed*this.dy) - (4*this.GRAVITY*this.FRICTION*this.dy/2 * (this.radius-this.y));
    double t;
    double t1;
    double t2;
    if (solutionRight < 0 && solutionBottom < 0 && solutionLeft < 0 && solutionTop < 0) { // no collision
      t = - (this.speed/this.GRAVITY*this.FRICTION*this.dx); // TODO ask TA to use dx, dy as a
      this.x = t * this.getBallVelocityX();
      this.y = t * this.getBallVelocityY();
      this.dx = 0;
      this.dy = 0;
      this.speed = 0;
      this.status = "Ball is stationary";
      return;
    }
    if (solutionBottom < 0 && solutionLeft < 0 && solutionTop < 0) { // can only collision in right edge
      t = this.smallestPositive(new double[] {
              (2 * (this.width-this.radius-this.x)) / ((this.speed*this.dx) + solutionRight),
              (2 * (this.width-this.radius-this.x)) / ((this.speed*this.dx) - solutionRight)});
      this.x = t * this.getBallVelocityX();
      this.y = t * this.getBallVelocityY();
      this.dx = -this.dx;
      this.speed -= this.GRAVITY*this.FRICTION*t; // TODO ask TA if it is the right formula
      this.status = "Ball hit right edge";
    } else if (solutionRight < 0 && solutionLeft < 0 && solutionTop < 0) { // can only collision in bottom edge
      t = this.smallestPositive(new double[] {
              (2 * (this.radius-this.y)) / ((this.speed*this.dy) + solutionBottom),
              (2 * (this.radius-this.y)) / ((this.speed*this.dy) - solutionBottom)});
      this.x = t * this.getBallVelocityX();
      this.y = t * this.getBallVelocityY();
      this.dy = -this.dy;
      this.speed -= this.GRAVITY*this.FRICTION*t;
      this.status = "Ball hit bottom edge";
    } else if (solutionRight < 0 && solutionBottom < 0 && solutionTop < 0) { // can only collision in left edge
      t = this.smallestPositive(new double[] {
              (2 * (this.radius-this.x)) / ((this.speed*this.dx) + solutionLeft),
              (2 * (this.radius-this.x)) / ((this.speed*this.dx) - solutionLeft)});
      this.x = t * this.getBallVelocityX();
      this.y = t * this.getBallVelocityY();
      this.dx = -this.dx;
      this.speed -= this.GRAVITY*this.FRICTION*t;
      this.status = "Ball hit left edge";
    } else if (solutionRight < 0 && solutionBottom < 0 && solutionLeft < 0) { // can only collision in top edge
      t = this.smallestPositive(new double[] {
              (2 * (this.height-this.radius-this.y)) / ((this.speed*this.dy) + solutionTop),
              (2 * (this.height-this.radius-this.y)) / ((this.speed*this.dy) - solutionTop)});
      this.x = t * this.getBallVelocityX();
      this.y = t * this.getBallVelocityY();
      this.dy = -this.dy;
      this.speed -= this.GRAVITY*this.FRICTION*t;
      this.status = "Ball hit top edge";
    } else if (solutionBottom < 0 && solutionLeft < 0) { // can collision in top and right edge
      t1 = this.smallestPositive(new double[] {
              (2 * (this.height-this.radius-this.y)) / ((this.speed*this.dy) + solutionTop),
              (2 * (this.height-this.radius-this.y)) / ((this.speed*this.dy) - solutionTop)});
      t2 = this.smallestPositive(new double[] {
              (2 * (this.width-this.radius-this.x)) / ((this.speed*this.dx) + solutionRight),
              (2 * (this.width-this.radius-this.x)) / ((this.speed*this.dx) - solutionRight)});
      if (t1 == t2) { // collision in top right corner
        this.x = t1 * this.getBallVelocityX();
        this.y = t1 * this.getBallVelocityY();
        this.dy = -this.dy;
        this.dx = -this.dx;
        this.speed -= this.GRAVITY*this.FRICTION*t1;
        this.status = "Ball hit top edge";
      } else if (t1 < t2) { // collision in top edge
        this.x = t1 * this.getBallVelocityX();
        this.y = t1 * this.getBallVelocityY();
        this.dy = -this.dy;
        this.speed -= this.GRAVITY*this.FRICTION*t1;
        this.status = "Ball hit top edge";
      } else if (t1 > t2) { // collision in right edge
        this.x = t2 * this.getBallVelocityX();
        this.y = t2 * this.getBallVelocityY();
        this.dx = -this.dx;
        this.speed -= this.GRAVITY*this.FRICTION*t2;
        this.status = "Ball hit right edge";
      }
    } else if (solutionBottom < 0 && solutionRight < 0) { // can collision in top and left edge
      t1 = this.smallestPositive(new double[] {
              (2 * (this.height-this.radius-this.y)) / ((this.speed*this.dy) + solutionTop),
              (2 * (this.height-this.radius-this.y)) / ((this.speed*this.dy) - solutionTop)});
      t2 = this.smallestPositive(new double[] {
              (2 * (this.radius-this.x)) / ((this.speed*this.dx) + solutionLeft),
              (2 * (this.radius-this.x)) / ((this.speed*this.dx) - solutionLeft)});
      if (t1 == t2) { // collision in top left corner
        this.x = t1 * this.getBallVelocityX();
        this.y = t1 * this.getBallVelocityY();
        this.dy = -this.dy;
        this.dx = -this.dx;
        this.speed -= this.GRAVITY*this.FRICTION*t1;
        this.status = "Ball hit top edge";
      } else if (t1 < t2) { // collision in top edge
        this.x = t1 * this.getBallVelocityX();
        this.y = t1 * this.getBallVelocityY();
        this.dy = -this.dy;
        this.speed -= this.GRAVITY*this.FRICTION*t1;
        this.status = "Ball hit top edge";
      } else if (t1 > t2) { // collision in left edge
        this.x = t2 * this.getBallVelocityX();
        this.y = t2 * this.getBallVelocityY();
        this.dx = -this.dx;
        this.speed -= this.GRAVITY*this.FRICTION*t2;
        this.status = "Ball hit left edge";
      }
    } else if (solutionTop < 0 && solutionRight < 0) { // can collision in bottom and left edge
      t1 = this.smallestPositive(new double[] {
              (2 * (this.radius-this.y)) / ((this.speed*this.dy) + solutionBottom),
              (2 * (this.radius-this.y)) / ((this.speed*this.dy) - solutionBottom)});
      t2 = this.smallestPositive(new double[] {
              (2 * (this.radius-this.x)) / ((this.speed*this.dx) + solutionLeft),
              (2 * (this.radius-this.x)) / ((this.speed*this.dx) - solutionLeft)});
      if (t1 == t2) { // collision in bottom left corner
        this.x = t1 * this.getBallVelocityX();
        this.y = t1 * this.getBallVelocityY();
        this.dy = -this.dy;
        this.dx = -this.dx;
        this.speed -= this.GRAVITY*this.FRICTION*t1;
        this.status = "Ball hit bottom edge";
      } else if (t1 < t2) { // collision in bottom edge
        this.x = t1 * this.getBallVelocityX();
        this.y = t1 * this.getBallVelocityY();
        this.dy = -this.dy;
        this.speed -= this.GRAVITY*this.FRICTION*t1;
        this.status = "Ball hit bottom edge";
      } else if (t1 > t2) { // collision in left edge
        this.x = t2 * this.getBallVelocityX();
        this.y = t2 * this.getBallVelocityY();
        this.dx = -this.dx;
        this.speed -= this.GRAVITY*this.FRICTION*t2;
        this.status = "Ball hit left edge";
      }
    } else if (solutionTop < 0 && solutionLeft < 0) { // can collision in bottom and right edge
      t1 = this.smallestPositive(new double[] {
              (2 * (this.radius-this.y)) / ((this.speed*this.dy) + solutionBottom),
              (2 * (this.radius-this.y)) / ((this.speed*this.dy) - solutionBottom)});
      t2 = this.smallestPositive(new double[] {
              (2 * (this.radius-this.x)) / ((this.speed*this.dx) + solutionRight),
              (2 * (this.radius-this.x)) / ((this.speed*this.dx) - solutionRight)});
      if (t1 == t2) { // collision in bottom right corner
        this.x = t1 * this.getBallVelocityX();
        this.y = t1 * this.getBallVelocityY();
        this.dy = -this.dy;
        this.dx = -this.dx;
        this.speed -= this.GRAVITY*this.FRICTION*t1;
        this.status = "Ball hit bottom edge";
      } else if (t1 < t2) { // collision in bottom edge
        this.x = t1 * this.getBallVelocityX();
        this.y = t1 * this.getBallVelocityY();
        this.dy = -this.dy;
        this.speed -= this.GRAVITY*this.FRICTION*t1;
        this.status = "Ball hit bottom edge";
      } else if (t1 > t2) { // collision in right edge
        this.x = t2 * this.getBallVelocityX();
        this.y = t2 * this.getBallVelocityY();
        this.dx = -this.dx;
        this.speed -= this.GRAVITY*this.FRICTION*t2;
        this.status = "Ball hit right edge";
      }
    }
    this.speed -= this.COLLISION_FRICTION;
    if (this.speed <= 0) {
      this.speed = 0;
      this.dx = 0;
      this.dy = 0;
      this.status = "Ball is stationary";
    }
  }

  /**
   * Helper method for advanceFriction. Find the smallest positive t
   * @param times an array store 2 or 4 double object represent time
   * @return the smallest positive t
   */
  private double smallestPositive(double[] times) {
    double ans = 0;
    for (double time: times) {
      if ((time > 0 && ans == 0) || (time > 0 && time < ans)) {
        ans = time;
      }
    }
    return ans;
  }

  @Override
  public void advance() {
    if (this.type.equals("simple")) {
      this.advanceSimple();
    } else {
      this.advanceFriction();
    }
  }

  @Override
  public int getTableWidth() { return this.width;}

  @Override
  public int getTableHeight() { return this.height;}

  @Override
  public double getBallPositionX() { return this.x;}

  @Override
  public double getBallPositionY() { return this.y;}

  @Override
  public double getBallRadius() { return this.radius;}

  @Override
  public double getBallVelocityX() { return this.speed * dx;}

  @Override
  public double getBallVelocityY() { return this.speed * dy;}

  /**
   * Find the status of the simulation at the current step.
   * @return a string "Status: SSS": where SSS is one of "Ball not set up", "Simulation started",
   * "Ball hit bottom edge", "Ball hit top edge", "Ball hit left edge", "Ball hit right edge",
   * "Ball is stationary".
   */
  @Override
  public String getStatus() { return "Status: " + this.status;}
}
