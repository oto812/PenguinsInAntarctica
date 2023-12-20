package pgdp.oop;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;

public abstract class Animal {
  protected int x, y;
  static String filename;
  protected File f;
  protected Image image;
  protected boolean alive = true;
  protected boolean IsPenguinAlive = true;


  protected static Animal[][] antarktis;

  public Animal(int x, int y) {
    this.x = x;
    this.y = y;
  }
  public abstract boolean IsPenguin();
  public abstract boolean IsLeopard();





  public boolean canStay(int y, int x) {
    int left = x - 1 >= 0 ? x - 1 : 40;
    int up = y - 1 >= 0 ? y - 1 : 40;
    int right = x + 1 <= 40 ? x + 1 : 0;
    int down = y + 1 <= 40 ? y + 1 : 0;

    if (antarktis[left][y] != null){
      if (antarktis[left][y].canEat(this)){
        return false;
      }
    }else if (antarktis[right][y] != null){
      if (antarktis[right][y].canEat(this)){
        return false;
      }
    }else if (antarktis[x][up] != null){
      if (antarktis[x][up].canEat(this)){
        return false;
      }
    }else if (antarktis[x][down] != null){
      if (antarktis[x][down].canEat(this)){
        return false;
      }
    }
    return true;
  }

  public void move() {
    if (alive) {
      int left = x - 1 >= 0 ? x - 1 : 40;
      int up = y - 1 >= 0 ? y - 1 : 40;
      int right = x + 1 <= 40 ? x + 1 : 0;
      int down = y + 1 <= 40 ? y + 1 : 0;
      if(antarktis[left][y] != null) {
        if (this.canEat(antarktis[left][y]) && canStay(y, left)) {
          antarktis[x][y] = null;
          if (antarktis[left][y].IsPenguin()) {
            IsPenguinAlive = false;
          }
          antarktis[left][y].alive = false;
          antarktis[left][y] = this;
          x = left;
          return;
        }
      }
      if (antarktis[x][up] != null) {
        if (this.canEat(antarktis[x][up]) && canStay(x, up)) {
          antarktis[x][y] = null;
          if (antarktis[x][up].IsPenguin()) {
            IsPenguinAlive = false;
          }
          antarktis[x][up].alive = false;
          antarktis[x][up] = this;
          y = up;
          return;
        }
      }
      if (antarktis[right][y] != null) {
        if (this.canEat(antarktis[right][y]) && canStay(y, right)) {
          antarktis[x][y] = null;
          if (antarktis[right][y].IsPenguin()) {
            IsPenguinAlive = false;
          }
          antarktis[right][y].alive = false;
          antarktis[right][y] = this;
          x = right;
          return;
        }
      }
      if (antarktis[x][down] != null) {
        if (this.canEat(antarktis[x][down]) && canStay(down, x)) {
          antarktis[x][y] = null;
          if (antarktis[x][down].IsPenguin()) {
            IsPenguinAlive = false;
          }
          antarktis[x][down].alive = false;
          antarktis[x][down] = this;

          y = down;
          return;
        }
      }
      if (antarktis[left][y] == null && canStay(y, left)) {
        antarktis[left][y] = this;
        antarktis[x][y] = null;
        x = left;
      }else if (antarktis[x][up] == null && canStay(up, x)) {
        antarktis[x][up] = this;
        antarktis[x][y] = null;
        y = up;
      }else if (antarktis[right][y] == null && canStay(y, right)) {
        antarktis[right][y] = this;
        antarktis[x][y] = null;
        x = right;
      }else if (antarktis[x][down] == null && canStay(down, x)) {
        antarktis[x][down] = this;
        antarktis[x][y] = null;
        y = down;
      }
    }
  }
  public abstract boolean canEat(Animal animal);

  protected abstract boolean eatenBy(Penguin penguin);
  protected abstract boolean eatenBy(PlayerPenguin playerPenguin);
  protected abstract boolean eatenBy(Whale whale);
  protected abstract boolean eatenBy(LeopardSeal leopardSeal);
  protected abstract boolean eatenBy(Fish fish);



  public static void setAntarktis(Animal[][] antarktis) {
    Animal.antarktis = antarktis;
  }
  // Graphics Stuff - You don't have to do anything here

  private void paintSymbol(Graphics g, Color c, int height, int width) {
    GradientPaint gradient = new GradientPaint(15, 0, c, width, 0, Color.LIGHT_GRAY);
    ((Graphics2D) g).setPaint(gradient);
    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.fillOval((int) (width * 0.3), (int) (height * 0.3), (int) (width * 0.5),
        (int) (height * 0.5));
  }

  public void draw(Graphics g, int height, int width) {
    if (image == null) {
      paintSymbol(g, Color.YELLOW, height, width);
      return;
    }
    ((Graphics2D) g).drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(null),
        image.getHeight(null), null);
  }
}
