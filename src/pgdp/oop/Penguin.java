package pgdp.oop;

import java.awt.Toolkit;
import java.io.File;

public class Penguin extends Animal {
  static String filename = "tux.png";

  public Penguin(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
  }

  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }
}
