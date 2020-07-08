package com.texnedo;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class RobotRoomCleaner {
    public static void main(String[] args) {
        SpaceController controller = new SpaceController(10, 20);
        controller.run();
    }

    interface Robot {
        // returns true if next cell is open and robot moves into the cell.
        // returns false if next cell is obstacle and robot stays on the current cell.
        boolean move();

        // Robot will stay on the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        void turnLeft();
        void turnRight();

        // Clean the current cell.
        void clean();
    }

    interface Printable {
        void print();
    }

    private static class Space {
        private static final char DIRTY = '*';
        private static final char CLEAN = '.';
        private static final char OBSTACLE = '#';
    }

    private static class Direction {
        private static final int UP = 1;
        private static final int RIGHT = 2;
        private static final int DOWN = 3;
        private static final int LEFT = 4;

        static int turnRight(int direction) {
            if (direction == LEFT) {
                return UP;
            }
            return direction + 1;
        }

        static int turnLeft(int direction) {
            if (direction == UP) {
                return LEFT;
            }
            return direction - 1;
        }

        static char toChar(int direction) {
            switch (direction) {
                case Direction.UP: {
                    return '▲';
                }
                case Direction.RIGHT: {
                    return '►';
                }
                case Direction.LEFT: {
                    return '◄';
                }
                case Direction.DOWN: {
                    return '▼';
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    public static class RobotImpl implements Robot, Printable {
        private final char[][] space;
        private int iCurrent;
        private int jCurrent;
        private int direction;

        public RobotImpl(char[][] space, int iStart, int jStart) {
            this.direction = Direction.UP;
            this.space = space;
            this.iCurrent = iStart;
            this.jCurrent = jStart;
        }

        @Override
        public boolean move() {
            switch (direction) {
                case Direction.UP: {
                    if (iCurrent == 0 || space[iCurrent - 1][jCurrent] == Space.OBSTACLE) {
                        return false;
                    }
                    iCurrent--;
                    return true;
                }
                case Direction.RIGHT: {
                    if (jCurrent == space[iCurrent].length - 1
                            || space[iCurrent][jCurrent + 1] == Space.OBSTACLE) {
                        return false;
                    }
                    jCurrent++;
                    return true;
                }
                case Direction.LEFT: {
                    if (jCurrent == 0 || space[iCurrent][jCurrent - 1] == Space.OBSTACLE) {
                        return false;
                    }
                    jCurrent--;
                    return true;
                }
                case Direction.DOWN: {
                    if (iCurrent == space.length - 1 ||
                            space[iCurrent + 1][jCurrent] == Space.OBSTACLE) {
                        return false;
                    }
                    iCurrent++;
                    return true;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }

        @Override
        public void turnLeft() {
            direction = Direction.turnLeft(direction);
        }

        @Override
        public void turnRight() {
            direction = Direction.turnRight(direction);
        }

        @Override
        public void clean() {
            if (space[iCurrent][jCurrent] == Space.OBSTACLE) {
                throw new IllegalStateException();
            }
            space[iCurrent][jCurrent] = Space.CLEAN;
        }

        @Override
        public void print() {
            //TODO - refactor this as robot guts should not know anything about printing the space
            System.out.print("\033[H\033[2J");
            for (int i = 0; i < space.length; i++) {
                for (int j = 0; j < space[i].length; j++) {
                    if (i == iCurrent && j == jCurrent) {
                        System.out.print(Direction.toChar(direction));
                    } else {
                        System.out.print(space[i][j]);
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static class RobotController {
        private final Robot robot;
        private int xCurrent = 0;
        private int yCurrent = 0;
        private int direction = Direction.UP;

        public RobotController(Robot robot) {
            this.robot = robot;
        }

        public void makeAction() {
            robot.clean();
            if (!move()) {
                turnRight();
                if (!move()) {
                    turnLeft();
                }
            }
        }

        private void turnRight() {
            direction = Direction.turnRight(direction);
            robot.turnRight();
        }

        private void turnLeft() {
            direction = Direction.turnLeft(direction);
            robot.turnLeft();
        }

        private boolean move() {
            if (robot.move()) {
                switch (direction) {
                    case Direction.UP: {
                        yCurrent--;
                        break;
                    }
                    case Direction.RIGHT: {
                        xCurrent++;
                        break;
                    }
                    case Direction.LEFT: {
                        xCurrent--;
                        break;
                    }
                    case Direction.DOWN: {
                        yCurrent++;
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException();
                    }
                }
                return true;
            }
            return false;
        }

        public void print() {
            System.out.println(
                    String.format(Locale.US, "Current x = %d, y = %d", xCurrent, yCurrent)
            );
        }
    }

    public static class SpaceController {
        private final char[][] space;
        private final Random random = new Random();

        public SpaceController(int height, int width) {
            space = new char[height][width];
            initializeSpace();
        }

        private void initializeSpace() {
            //TODO - think how to generate always connected map of spaces
            for (int i = 0; i < space.length; i++) {
                for (int j = 0; j < space[i].length; j++) {
                    space[i][j] = Space.DIRTY;
                }
            }
        }

        public void run() {
            RobotImpl impl = new RobotImpl(
                    space,
                    random.nextInt(space.length),
                    random.nextInt(space[0].length)
            );
            RobotController controller = new RobotController(impl);
            while (true) {
                impl.print();
                controller.makeAction();
                impl.print();
                controller.print();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
