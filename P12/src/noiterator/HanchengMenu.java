package noiterator;

import java.util.ArrayList;

/**
 *
 * Iterator class to store the menu items with ArrayList.
 *
 */
public class HanchengMenu {
  ArrayList<MenuItem> menus = new ArrayList<MenuItem>();

  /**
   * Add a menu item to the list
   *
   * @param name given name of the item
   * @param description given description of the item
   * @param vegetarian given vegetarian of the item
   * @param price given price of the item
   */
  public void addItem(String name, String description, boolean vegetarian, double price) {
    menus.add(new MenuItem(name, description, vegetarian, price));
  }

  /**
   * Return the list contains all menu items
   *
   * @return ArrayList contains all menu items
   */
  public ArrayList<MenuItem> getMenuItems() {
    return menus;
  }
}
