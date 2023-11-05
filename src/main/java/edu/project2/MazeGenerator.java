package edu.project2;

import java.util.Arrays;
import java.util.Random;

public class MazeGenerator {
    private final int width;
    private final int height;
    private final int[][] maze;

    public MazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[height][width];
    }

    public int[][] generateMaze() {
        for (int i = 0; i < height; i++) {
            Arrays.fill(maze[i], 1); // Заполняем все ячейки стенами (1)
        }

        divide(0, 0, width, height);
        return maze;
    }

    private void divide(int x, int y, int width, int height) {
        if (width < 2 || height < 2) {
            return;
        }

        int horizontal = randomInt(width - 1);
        int vertical = randomInt(height - 1);

        for (int i = y; i < y + height; i++) {
            maze[i][x + horizontal] = 0; // Создаем проход в горизонтальной стене
        }
        for (int i = x; i < x + width; i++) {
            maze[y + vertical][i] = 0; // Создаем проход в вертикальной стене
        }

        int wx = randomInt(width);
        int wy = randomInt(height);

        maze[y + wy][x + wx] = 0; // Создаем проход внутри комнаты

        divide(x, y, horizontal + 1, vertical + 1);
        divide(x + horizontal + 1, y, width - horizontal - 1, vertical + 1);
        divide(x, y + vertical + 1, horizontal + 1, height - vertical - 1);
        divide(x + horizontal + 1, y + vertical + 1, width - horizontal - 1, height - vertical - 1);
    }

    private int randomInt(int max) {
        Random random = new Random();
        return random.nextInt(max + 1);
    }

    public static void printMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                if (cell == 0) {
                    System.out.print(" "); // Путь
                } else {
                    System.out.print("#"); // Стена
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // I need a week more to complete this project
        MazeGenerator generator = new MazeGenerator(20, 10);
        int[][] maze = generator.generateMaze();
        printMaze(maze);
    }
}
