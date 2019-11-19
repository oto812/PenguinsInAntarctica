package pgdp.oop;

import java.awt.Toolkit;
import java.io.File;

public class LeopardSeal extends Animal {
  static String filename = "leopard.png";

  public LeopardSeal(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
  }

  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }
}
