package Q33;

interface Edible {
  public String howToEat();
}

abstract class Fruit implements Edible {
}

class Orange extends Fruit {
  public String howToEat() {
    return "Peel the shell and eat the inside part";
  }
}

class Apple extends Fruit {
  public String howToEat() {
    return "Wash and eat the whole part";
  }
}

abstract class Animal {
  private double weight;

  public abstract String sound();

  public void setWeight(double wieght) { this.weight = weight;}

  public double getWeight() {return this.weight;}
}

class Chicken extends Animal implements Edible  {

  public String sound() {
    return "chicken sound";
  }

  public String howToEat() {
    return "Cook and eat the whole chicken. Do not eat bones;";
  }
}

class Tiger extends Animal {

  public String sound() {
    return "Tiger sound";
  }
}