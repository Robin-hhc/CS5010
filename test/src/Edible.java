public interface Edible {
  public String howToEat();
}

public abstract class Fruit implements Edible {
  public abstract String howToEat();
}

public class Orange extends Fruit {
  public String howToEat() {
    return "Peel the shell and eat the inside part";
  }
}

  public class Apple extends Fruit {
    public String howToEat() {
      return "Wash and eat the whole part";
    }
  }

    public abstract class Animal {
      private double weight;

      public abstract String sound();

      public void setWeight(double wieght) {
        this.weight = weight;
      }

      public double getWeight() {
        return this.weight;
      }
    }

      public class Chicken implements Edible extends Animal {

        public Chicken(double weight) {
          super.setWeight(weight);
        }

        public String sound() {
          return "chicken sound";
        }

        public String howToEat() {
          return "Cook and eat the whole chicken. Do not eat bones;";
        }
      }

      public class Tiger extends Animal {
        public Chicken(double weight) {
          super.setWeight(weight);
        }

        public String sound() {
          return "chicken sound";
        }
      }