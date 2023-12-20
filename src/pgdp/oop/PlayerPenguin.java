package pgdp.oop;

public class PlayerPenguin extends Penguin {


    public PlayerPenguin(int x, int y) {
        super(x, y);
    }

    public boolean canEat(Animal animal) {
        return animal.eatenBy(this);
    }

    public boolean move(int newX, int newY) {
        antarktis[x][y] = null;
        if (newX > 40){
            x = 0;
            y = newY;
        }else if (newY > 40){
            x = newX;
            y = 0;
        }else if (newX < 0){
            x = 40;
            y = newY;
        }else if (newY < 0){
            x = newX;
            y = 40;
        }else{
            x = newX;
            y = newY;
        }

        if (!IsPenguinAlive || !this.alive){
            return true;
        }
        if (antarktis[x][y] != null) {
            if (antarktis[x][y].canEat(this) || antarktis[x][y].IsLeopard()) {
                this.alive = false;
                return true;
            }else if (antarktis[x][y].IsPenguin()){
                antarktis[x][y] = this;
                return true;
            }
        }
        if (antarktis[x][y] != null){
            if (this.canEat(antarktis[x][y])){
                antarktis[x][y].alive = false;
            }
        }
        antarktis[x][y] = this;
        return false;
    }
}
