public interface Money {
  /**
   * A method to add two money amounts
   *
   * @param other  the other Money object that will be added
   *
   * @return the Money object with amount equals sum of the input Money and this Money
   */
  public Money add(Money other);

  /**
   * A method to add a money amount with another given as a separate dollar and cent value
   *
   * @param dollars  the amount of dollars need to be added
   * @param cents  the amount of cents need to be added
   *
   * @return the Money object with amount equals sum of input amount and this Money
   */
  public Money add(int dollars, int cents) throws IllegalArgumentException;

  /**
   * A method that returns the decimal value of the money
   *
   * @return double object repersent the amount of Money in the format "xx.yy"
   */
  public double getDecimalValue();
}
