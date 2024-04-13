package noiterator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class HanchengMenuTest {

  private HanchengMenu menu;

  @Before
  public void setUp() {
    menu = new HanchengMenu();
    menu.addItem("Vegetable Bun", "Steamed bun filled with mixed vegetables", true, 1.99);
    menu.addItem("Pork Dumpling", "Dumplings filled with seasoned pork", false, 3.99);
  }

  @Test
  public void testAddItem() {
    assertEquals(2, menu.getMenuItems().size());
    menu.addItem("Beef Noodle", "Noodles with braised beef", false, 7.50);
    ArrayList<MenuItem> items = menu.getMenuItems();
    assertEquals(3, items.size());
    MenuItem newItem = items.get(items.size() - 1);
    assertEquals("Beef Noodle", newItem.getName());
    assertEquals("Noodles with braised beef", newItem.getDescription());
    assertFalse(newItem.isVegetarian());
    assertEquals(7.50, newItem.getPrice(), 0.01);
  }

  @Test
  public void testGetMenuItems() {
    ArrayList<MenuItem> items = menu.getMenuItems();
    assertNotNull(items);
    assertEquals(2, items.size());
    assertEquals("Vegetable Bun", items.get(0).getName());
    assertEquals("Pork Dumpling", items.get(1).getName());
  }
}
