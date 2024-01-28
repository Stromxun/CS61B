package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    Random rand;
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private final int maxRoomAmount = 20;
    private int[][] dot;
    private final int maxSizeRect = 6;
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        initFrame(finalWorldFrame);
        long seed = seedParse(input);
        rand = new Random(seed);
        rectangularGenerate(finalWorldFrame);
        generateRail(finalWorldFrame);
        connectRoom(finalWorldFrame);
        generateWall(finalWorldFrame);

        return finalWorldFrame;
    }

    private void initFrame(TETile[][] finalWorldFrame) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                finalWorldFrame[i][j] = Tileset.NOTHING;
            }
        }
    }

    private boolean isDigital(Character ch) {
        return ch >= '0' && ch <= '9';
    }
    private long seedParse(String input) {
        long seed = 0;
        for (Character ch : input.toCharArray()) {
            if (isDigital(ch)) {
                seed = seed * (2 * 5) + Long.parseLong(ch.toString());
            }
        }
        return seed;
    }

    private void fillRectangular(TETile[][] finalWorldFrame, int x, int y, int finalX, int finalY) {
        for (int i = x; i < finalX; i++) {
            for (int j = y; j < finalY; j++) {
                finalWorldFrame[i][j] = Tileset.FLOOR;
            }
        }
    }
    final int wallOrientation = 8;
    final int[] X = {1, -1, 0, 0, -1, -1, 1, 1};
    final int[] Y = {0, 0, 1, -1, 1, -1, -1, 1};

    final int aggregation = 2;

    private boolean isWall(TETile[][] finalWorldFrame, int x, int y) {
        for (int i = 0; i < wallOrientation; i++) {
            int nowX = x + X[i];
            int nowY = y + Y[i];
            if (nowX < 0 || nowX >= WIDTH || nowY < 0 || nowY >= HEIGHT) {
                continue;
            }
            if (finalWorldFrame[nowX][nowY].equals(Tileset.FLOOR)) {
                return true;
            }
        }
        return false;
    }


    private void generateWall(TETile[][] finalWorldFrame) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (finalWorldFrame[i][j].equals(Tileset.NOTHING) && isWall(finalWorldFrame, i, j)) {
                    finalWorldFrame[i][j] = Tileset.WALL;
                }
            }
        }
    }

    public void rectangularGenerate(TETile[][] finalWorldFrame) {
        dot = new int[maxRoomAmount][2];
        for (int i = 0; i < maxRoomAmount; i++) {
            int x = rand.nextInt(aggregation, WIDTH - aggregation);
            int y = rand.nextInt(aggregation, HEIGHT - aggregation);

            // random size of rect, but the low size is 2, maxsize is MaxSizeRect
            int rectX = rand.nextInt(2, maxSizeRect);
            int rectY = rand.nextInt(2, maxSizeRect);
            int finalX = Math.min(x + rectX, WIDTH);
            int finalY = Math.min(y + rectY, HEIGHT);
            dot[i][0] = rand.nextInt(x, finalX);
            dot[i][1] = rand.nextInt(y, finalY);

            fillRectangular(finalWorldFrame, x, y, finalX, finalY);
        }
    }

    private boolean isBorder(int x, int y) {
        return x == WIDTH - 1 || x == 0 || y == HEIGHT - 1 || y == 0;
    }

    private void connectRoom(TETile[][] finalWorldFrame) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (finalWorldFrame[i][j].equals(Tileset.NOTHING) && canConn(finalWorldFrame, i, j)) {
                    finalWorldFrame[i][j] = Tileset.FLOOR;
                }

                // fill not advisable floor
                if (finalWorldFrame[i][j].equals(Tileset.FLOOR) && isBorder(i, j)) {
                    finalWorldFrame[i][j] = Tileset.WALL;
                }
            }
        }
    }

    private boolean canConn(TETile[][] finalWorldFrame, int i, int j) {
        if (i - 1 < 0 || i + 1 > WIDTH || j - 1 < 0 || j + 1 > HEIGHT) {
            return false;
        }
        return (finalWorldFrame[i - 1][j].equals(Tileset.FLOOR) && finalWorldFrame[i + 1][j].equals(Tileset.FLOOR))
            ||
               (finalWorldFrame[i][j - 1].equals(Tileset.FLOOR) && finalWorldFrame[i][j + 1].equals(Tileset.FLOOR));
    }

    // generate rail connect all room
    private void generateRail(TETile[][] finalWorldFrame) {
        for (int i = 1; i < maxRoomAmount; i++) {
            algGenerateRail(finalWorldFrame, dot[i - 1][0], dot[i - 1][1], dot[i][0], dot[i][1]);
        }
    }

    private void algGenerateRail(TETile[][] finalWorldFrame, int sx, int sy, int ex, int ey) {
        while (sx != ex || sy != ey) {
            double rat = rand.nextDouble();
            if ((rat < 0.5 && sx != ex) || sy == ey) {
                sx = ((sx < ex) ? sx + 1 : sx - 1);
            } else {
                sy = ((sy < ey) ? sy + 1 : sy - 1);
            }
            finalWorldFrame[sx][sy] = Tileset.FLOOR;
        }
    }
}
