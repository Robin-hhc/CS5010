package sim;

/**
 * This interface represents a simulator that simulate the pool game
 */
public interface PoolSimulator {
  /**
   * Start the simulation with a ball at the given position, with the given radius and velocity.
   * @param x the x-coordinate of the center of the ball
   * @param y the y-coordinate of the center of the ball
   * @param radius the radius of the ball
   * @param speed the current speed of the ball
   * @param dx the direction in x-coordinate of the ball
   * @param dy the direction in y-coordinate of the ball
   * @throws IllegalArgumentException if the parameters are invalid
   */
  void start(int x,int y,int radius,int speed,double dx,double dy) throws IllegalArgumentException;

  /**
   * advances the simulation by one discrete step (to the next bounce, or the ball stopping).
   */
  void advance();

  /**
   * Find the width of the table for this simulation.
   * @return the width of the table for this simulation
   */
  int getTableWidth();

  /**
   * Find the height of the table for this simulation.
   * @return the height of the table for this simulation
   */
  int getTableHeight();

  /**
   * Find the x coordinate of the current position of the ball.
   * @return the x coordinate of the current position of the ball
   */
  double getBallPositionX();

  /**
   * Find the y coordinate of the current position of the ball.
   * @return the y coordinate of the current position of the ball
   */
  double getBallPositionY();

  /**
   * Find the radius of the ball.
   * @return the radius of the ball
   */
  double getBallRadius();

  /**
   * Find the x component of the current velocity of the ball.
   * @return the x component of the current velocity of the ball.
   */
  double getBallVelocityX();

  /**
   * Find the y component of the current velocity of the ball.
   * @return the y component of the current velocity of the ball.
   */
  double getBallVelocityY();

  /**
   * Find the status of the simulation at the current step.
   * @return a string in format of "Status: xxx"
   */
  String getStatus();
}
