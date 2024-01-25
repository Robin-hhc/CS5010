public class SimpleMoney implements Money{
  //In SimpleMoney
 /*
 FIELDS
 ..this.dollars: int
 ..this.cents: int
 METHODS
 ..this.add(Money other): Money
 ..this.add(int dollars,int cents): Money
 METHODS OF FIELDS
 ..this.getDecimalValue(): double
 */

  private int dollar;
  private int cent;

  /**
   * Constructor of the class. Set initial dollars and cents as input. Throws exceptions when any of the two inputs negative
   * if (dollars < 0) throw IllegalArgumentException
   * if (cents < 0 || cents >= 100) throw IllegalArgumentException
   * set dollars = 0
   * set cents = 0
   * @param dollars  the amount of dollars
   * @param cents  the amount of cents
   */
  public SimpleMoney(int dollars, int cents) throws IllegalArgumentException{
    if (dollars < 0) {
      throw new IllegalArgumentException("Invalid dollars");
    } else if (cents < 0 || cents >= 100) {
      throw new IllegalArgumentException("Invalid cents");
    }

    this.dollar = dollars;
    this.cent = cents;
  }

  /**
   * A method to add two money amounts
   * otherAmount = other.getDecimalValue();
   * Set newDollar = this.dollar + other.dollar
   * Set newCent = this.dollar + other.cent
   * if (newCent >= 100) Move this one dollar to newDollar and keep cent < 100
   * @param other  the other Money object that will be added
   * @return the SimpleMoney object with amount equals sum of the input Money and this Money
   */
  @Override
  public Money add(Money other) {
    double otherAmount = other.getDecimalValue();
    int newDollar = this.dollar + (int) otherAmount;
    int newCent = this.cent + (int) otherAmount*100%100;
    if (newCent >= 100) {
      newCent = newCent - 100;
      newDollar++;
    }
    return new SimpleMoney(newDollar, newCent);
  }

  /**
   * A method to add a money amount with another given as a separate dollar and cent value
   * if (dollars < 0) throw IllegalArgumentException
   * if (cents < 0 || cents >= 100) throw IllegalArgumentException
   * Set newDollar = this.dollar + dollars
   * Set newCent = this.dollar + cents
   * if (newCent >= 100) Move this one dollar to newDollar and keep cent < 100
   * @param dollars  the amount of dollars need to be added
   * @param cents  the amount of cents need to be added
   * @return the SimpleMoney object with amount equals sum of input amount and this Money
   */
  @Override
  public Money add(int dollars, int cents) throws IllegalArgumentException {
    if (dollars < 0) {
      throw new IllegalArgumentException("Invalid dollars");
    } else if (cents < 0 || cents >= 100) {
      throw new IllegalArgumentException("Invalid cents");
    }

    int newDollar = this.dollar + dollars;
    int newCent = this.cent + cents;
    if (newCent >= 100) {
      newCent = newCent - 100;
      newDollar++;
    }
    return new SimpleMoney(newDollar, newCent);
  }

  /**
   * A method that returns the decimal value of the money
   * Return this.dollar + this.cent/100.00
   * @return double object represent the amount of Money in the format "xx.yy"
   */
  @Override
  public double getDecimalValue() {
    return this.dollar + this.cent/100.00;
  }

  /**
   * A method that returns the String representation of the Simple Money
   * Return Integer.toString(this.dollar) + Integer.toString(this.cent)
   * @return String object represent the amount of Money in the format "xx.yy"
   */
  @Override
  public String toString() {
    return Integer.toString(this.dollar) + Integer.toString(this.cent);
  }
}
