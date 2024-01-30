import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import sim.SimplePoolSimulator;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the SimpleBoxSet class.
 */
public class SimplePoolSimulatorTest {

  SimplePoolSimulator test;
  public void setUp() {
    test = new SimplePoolSimulator(400,400,"simple");
  }

  public void constructorTest() {

  }
}
