package pgdp.oop;

import java.awt.event.WindowEvent;

public class Antarktis extends Maze {
    private static int width, height;
    private static Penguin lostPenguin;
    private static Whale[] whales = new Whale[5];
    private static LeopardSeal[] leopardSeals = new LeopardSeal[20];
    private static Fish[] fishes = new Fish[500];
    private static PlayerPenguin playerPenguin;
    private static boolean end = false;

    public static void main(String[] args) {
        width = 41;
        height = 41;
        antarktis = generateMaze(width, height);

        // Close the opnend frame
        setupMaze();
        Animal.antarktis = antarktis;
        gameLoop();
        closeFrame();
    }

    private static void gameLoop() {
        while (true) {
            draw();
            while (currentEvent == NOTHING) {
                draw();
            }

            moveAll();
            if (end){
                end = false;
                return;
            }
            currentEvent = NOTHING;
        }
    }


    private static void moveAll() {

        switch (currentEvent) {
            case 1:
                if(playerPenguin.move((playerPenguin.x-1), playerPenguin.y)) {
                    end = true;
                    return;
                }
                break;
            case 2:
                if(playerPenguin.move(playerPenguin.x, (playerPenguin.y-1))) {
                    end = true;
                    return;
                }
                break;
            case 3:
                if(playerPenguin.move((playerPenguin.x+1), playerPenguin.y)) {
                    end = true;
                    return;
                }
                break;
            case 4:
                if(playerPenguin.move(playerPenguin.x, (playerPenguin.y + 1))){
                    end = true;
                    return;
                }
                break;
            case -1:
                currentEvent = NOTHING;
                break;
        }


        for (int i = 0; i < whales.length; i++) {
            whales[i].move();
            if(!lostPenguin.alive || !playerPenguin.alive){
                end = true;
                return;
            }
        }
        for (int i = 0; i < leopardSeals.length; i++) {
                leopardSeals[i].move();
        }
        lostPenguin.move();
        for (int i = 0; i < fishes.length; i++) {
                fishes[i].move();
        }

    }

    /**
     * Example Setup for easier Testing during development
     */
    private static void setupMaze() {
        int[] pos;
        playerPenguin = new PlayerPenguin(3, 3);
        antarktis[3][3] = playerPenguin;

        for (int i = 0; i < whales.length; i++) {
            pos = getRandomEmptyField();
            whales[i] = new Whale(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = whales[i];
        }

        for (int i = 0; i < leopardSeals.length; i++) {
            pos = getRandomEmptyField();
            leopardSeals[i] = new LeopardSeal(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = leopardSeals[i];
        }

        for (int i = 0; i < fishes.length; i++) {
            pos = getRandomEmptyField();
            fishes[i] = new Fish(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = fishes[i];
        }

        antarktis[20][20] = new Penguin(20, 20);
        lostPenguin = (Penguin) antarktis[20][20];
    }
}
