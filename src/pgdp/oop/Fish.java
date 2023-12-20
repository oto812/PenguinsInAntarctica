package pgdp.oop;

import java.awt.Toolkit;
import java.io.File;

public class Fish extends Animal {
  static String filename = "fish.png";

  public Fish(int x, int y) {
    super(x, y);

    f = new File(filename);
    image = Toolkit.getDefaultToolkit().getImage(f.getAbsolutePath());
  }

  @Override
  public boolean IsPenguin() {
    return false;
  }

  @Override
  public boolean IsLeopard() {
    return false;
  }

  @Override
  public void move() {
    if (alive) {
      int left = x - 1 >= 0 ? x - 1 : 40;
      int up = y - 1 >= 0 ? y - 1 : 40;
      int right = x + 1 <= 40 ? x + 1 : 0;
      int down = y + 1 <= 40 ? y + 1 : 0;
      if (antarktis[x][up] == null && canStay(up, x)) {
        antarktis[x][y] = null;
        antarktis[x][up] = this;
        y = up;
      } else if (antarktis[right][y] == null && canStay(y, right)) {
        antarktis[x][y] = null;
        antarktis[right][y] = this;
        x = right;
      } else if (antarktis[x][down] == null && canStay(down, x)) {
        antarktis[x][y] = null;
        antarktis[x][down] = this;
        y = down;
      } else if (antarktis[left][y] == null && canStay(y, left)) {
        antarktis[x][y] = null;
        antarktis[left][y] = this;
        x = left;
      }
    }
  }

  public boolean canEat(Animal animal) {
    return animal.eatenBy(this);
  }

  @Override
  protected boolean eatenBy(Penguin penguin) {
    return true;
  }

  @Override
  protected boolean eatenBy(PlayerPenguin playerPenguin) {
    return true;
  }

  @Override
  protected boolean eatenBy(Whale whale) {
    return false;
  }

  @Override
  protected boolean eatenBy(LeopardSeal leopardSeal) {
    return true;
  }

  @Override
  protected boolean eatenBy(Fish fish) {
    return false;
  }
}
